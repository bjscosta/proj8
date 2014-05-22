/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proj8.jms;

import java.nio.ByteBuffer;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Topic;

/**
 *
 * @author brunocosta
 */
@Stateless
public class JMSSender {
    

    
    @Resource (lookup = "jms/connFactory")
    private ConnectionFactory connectionFactory;
    
    @Resource(lookup = "jms/firstTopic")
    private  Topic topic;
    
    public void sendMessage(ByteBuffer message) {
        
        byte[] bytes = new byte[message.capacity()];
        message.get(bytes, 0, bytes.length);
        
       
        try (JMSContext context = connectionFactory.createContext();) {
            
            context.createProducer().send(topic, bytes);

            
        }
       
    }
}
