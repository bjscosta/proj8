/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proj8.pojos;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import proj8.jms.JMSSender;
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
    
    @Inject
    private JMSSender sender;
    
    
    
    

    @OnMessage
    public void broadcastFigure(Figure figure, Session session) throws IOException, EncodeException {
        
       
        
        for (Session peer : peers) {
            if (!peer.equals(session)) {
                peer.getBasicRemote().sendObject(figure);
            }

        }
    }

    @OnMessage
    public void broadcastSnapshot(ByteBuffer data, Session session) throws IOException {
        
        bb = data;
        
        sender.sendMessage(data);
        
        for (Session peer : peers) {
            if (!peer.equals(session)) {
                peer.getBasicRemote().sendBinary(data);
            }

        }
        
    }

    @OnOpen
    public void onOpen(Session peer) throws IOException {

        peers.add(peer);
        
        peer.getBasicRemote().sendBinary(bb);
        

    }

    @OnClose
    public void onClose(Session peer) {
        peers.remove(peer);

    }

    

}
