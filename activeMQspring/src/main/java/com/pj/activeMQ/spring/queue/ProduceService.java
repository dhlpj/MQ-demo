package com.pj.activeMQ.spring.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.*;

/**
 * @author Jack
 * @create 2018-11-16 10:56
 */
public class ProduceService {
    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private Destination destination;

    public void sendMessage(final String message) {
        jmsTemplate.send(destination, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage(message);
                System.out.println("发送消息："+message);
                return textMessage;
            }
        });
    }
}
