/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proj8.jms;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Topic;
import proj8.pojos.Counters;

/**
 *
 * @author brunocosta
 */
public class CountersSender {
    
    @Resource (lookup = "jms/connFactory")
    private ConnectionFactory connectionFactory;
    
    @Resource(lookup = "jms/usersLoggedTopic")
    private  Topic topic;
    
    public void sendMessage(Counters c) {
        
        try (JMSContext context = connectionFactory.createContext();) {
            
            context.createProducer().send(topic, c);

        }
       
    }
}
