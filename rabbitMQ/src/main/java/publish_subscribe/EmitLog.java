package publish_subscribe;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeoutException;

/**
 * @author Jack
 * @create 2018-11-20 14:35
 */
public class EmitLog {
    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("47.106.144.102");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        //声明转发器
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");
        String message = new Date().toString()+":log something";
        //向转发器上发送消息
        channel.basicPublish(EXCHANGE_NAME,"",null,message.getBytes());
        System.out.println("sent message");
        channel.close();
        connection.close();
    }
}
