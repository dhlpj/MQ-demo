package com.pj.activeMQ.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author Jack
 * @create 2018-11-15 15:49
 */
public class AppProducer {
    public static final String url = "tcp://47.106.144.102:61616";
    public static final String queueName = "queue-test";

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
        Destination destination = session.createQueue(queueName);

        //6、创建生产者
        MessageProducer producer = session.createProducer(destination);

        for(int i=1;i<=100;i++){
            //7、创建消息
            TextMessage message = session.createTextMessage("test"+i);
            //8、发送消息
            producer.send(message);
            System.out.println("发送消息"+i);
        }
        //9、关闭连接
        connection.close();
    }
}
