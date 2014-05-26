/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proj8.jms;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import proj8.webSocket.MyWhiteboard;

/**
 *
 * @author brunocosta
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/firstTopic"),
    @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "jms/firstTopic")
})
public class ImageReciever implements MessageListener {
    
    @Inject
    private MyWhiteboard mw;
    
    public ImageReciever() {
    }
    
    @Override
    public void onMessage(Message message) {
        
        try {
           
            ByteBuffer bytebuffer = null;
            byte[] bytes = null;
        
            bytes = message.getBody(byte[].class);
            bytebuffer = ByteBuffer.wrap(bytes);
            
            mw.sendImage(bytebuffer);
            
        } catch (JMSException ex) {
            Logger.getLogger(MyWhiteboard.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ImageReciever.class.getName()).log(Level.SEVERE, null, ex);
        }
   
    }
    
    
}
