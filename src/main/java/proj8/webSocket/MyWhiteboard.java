/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proj8.webSocket;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import proj8.entities.Whiteboard;
import proj8.facades.UsersFacade;
import proj8.facades.WhiteboardFacade;
import proj8.jms.CountersSender;
import proj8.jms.ImageReciever;
import proj8.jms.ImageSender;
import proj8.pojos.Counters;
import proj8.tools.FigureDecoder;
import proj8.tools.FigureEncoder;

/**
 *
 * @author brunocosta
 */
@Stateless
@ServerEndpoint(value = "/whiteboardendpoint", encoders = {FigureEncoder.class}, decoders = {FigureDecoder.class})
public class MyWhiteboard {

    private static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());
    private static ByteBuffer bb = ByteBuffer.allocate(1000000);
    private static Counters counters = new Counters();

    public Counters getCounters() {
        return counters;
    }

    public void setCounters(Counters counters) {
        this.counters = counters;
    }

    @Inject
    private ImageSender imageSender;
    
    @Inject
    private ImageReciever imageReciever;

    @Inject
    private CountersSender counterSender;

    @Inject
    private UsersFacade usersFacade;
    
    @Inject
    private WhiteboardFacade whiteboardFacade; 
    
    
    @OnMessage
    public void broadcastFigure(String name, Session session) throws IOException, EncodeException {
        
        
                Whiteboard w = new Whiteboard();
        
                w.setImage(bb.array());         
                w.setSaveDate(new Date());
                w.setName(name);
                w.setUsername(session.getUserPrincipal().toString());
                
                whiteboardFacade.create(w);
                
     
    }

    @OnMessage
    public void broadcastSnapshot(ByteBuffer data, Session session) throws IOException {

        bb = data;

        imageSender.sendMessage(data);
        counterSender.sendMessage(counters);

        for (Session peer : peers) {
            if (!peer.equals(session)) {
                peer.getBasicRemote().sendBinary(data);
            }

        }
        
        
    }

    @OnOpen
    public void onOpen(Session peer) throws IOException, EncodeException {

        peers.add(peer);

        peer.getBasicRemote().sendBinary(bb);

        if (peer.getUserPrincipal() != null) {

            counters.getEditingUsers().add(usersFacade.find(peer.getUserPrincipal().getName()));
            counterSender.sendMessage(counters);
            
        }
        
    }

    @OnClose
    public void onClose(Session peer) {
        if (peer.getUserPrincipal() != null) {
            counters.getEditingUsers().remove(usersFacade.find(peer.getUserPrincipal().getName()));
        }
        peers.remove(peer);
    }

    public void sendImage(ByteBuffer data) throws IOException {
        
        
        bb = data;
        
        for (Session peer : peers) {

            peer.getBasicRemote().sendBinary(bb);

        }

    }

    public void updateCounters(Counters c) throws IOException, JMSException {

        counters = c;

        for (Session p : peers) {
            p.getBasicRemote().sendText("{\"editing\" : " + c.getEditingUsers().size() + "}");
            p.getBasicRemote().sendText("{\"aborting\" : " + c.getAbortingUsers().size() + "}");

            if (c.getEditingUsers().size() == 0) {
                p.getBasicRemote().sendText("{\"abort\" : " + true + "}");

            } else {
                p.getBasicRemote().sendText("{\"abort\" : " + false + "}");
            }
        }

    }

    public void recieveChanges() {

        counterSender.sendMessage(counters);
    }

   
}
