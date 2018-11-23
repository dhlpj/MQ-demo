package com.pj.activeMQ.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author Jack
 * @create 2018-11-15 16:13
 */
public class AppSubscriber {
    public static final String url = "tcp://47.106.144.102:61616";
    public static final String topicName = "topic-test";

    public static void main(String[] args) throws JMSException {
        //1、创建ConnectionFactory
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);

        //2、创建connection
        Connection connection = connectionFactory.createConnection();

        //3、打开connection
        connection.start();

        //4、创建session
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);

        //5、创建一个目标
        Destination destination = session.createTopic(topicName);

        //6、创建消费者
        MessageConsumer consumer = session.createConsumer(destination);

        //7、创建监听器
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    String text = textMessage.getText();
                    System.out.println("接收消息:"+text);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

        //9、关闭连接
        //connection.close();
    }

}
