/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proj8.jms;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;
import proj8.webSocket.MyWhiteboard;

/**
 *
 * @author brunocosta
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/usersLoggedTopic"),
    @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "jms/usersLoggedTopic")
})
public class CountersReciever implements MessageListener {
    
    @Inject
    private MyWhiteboard mw;
    
    public CountersReciever() {
    }
    
    @Override
    public void onMessage(Message message) {
        
        
            
       
        
        
    }
    
}
