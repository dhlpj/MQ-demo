package topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Jack
 * @create 2018-11-20 17:31
 */
public class Sender {
    public static final String EXCHANGE_NAME="testTopic";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("47.106.144.102");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME,"topic");
        String[] routing_keys = new String[] { "kernal.info", "cron.warning",
                "auth.info" };
        for(String routingKey : routing_keys){
            String temp = "key:"+routingKey+" message sent";
            channel.basicPublish(EXCHANGE_NAME,routingKey,null,temp.getBytes());
            System.out.println(temp);
        }
        channel.close();
        connection.close();
    }
}
