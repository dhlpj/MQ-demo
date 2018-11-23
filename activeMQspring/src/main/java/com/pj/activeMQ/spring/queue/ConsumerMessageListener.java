package com.pj.activeMQ.spring.queue;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @author Jack
 * @create 2018-11-16 11:21
 */
public class ConsumerMessageListener implements MessageListener {
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            String text = textMessage.getText();
            System.out.println("消费消息："+text);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
