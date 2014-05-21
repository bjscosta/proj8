/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proj8.jms;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 *
 * @author brunocosta
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/firstTopic"),
    @ActivationConfigProperty(propertyName = "clientId", propertyValue = "jms/firstTopic"),
    @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "jms/firstTopic")
})
public class JMSReciever implements MessageListener {
    
    public JMSReciever() {
    }
    
    @Override
    public void onMessage(Message message) {
        
        try {
            
            System.out.println("cenas"+message.getBody(byte[].class));
        } catch (JMSException ex) {
            Logger.getLogger(JMSReciever.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
