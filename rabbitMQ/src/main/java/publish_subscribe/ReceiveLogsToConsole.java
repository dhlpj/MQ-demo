package publish_subscribe;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Jack
 * @create 2018-11-20 14:51
 */
public class ReceiveLogsToConsole {
    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("47.106.144.102");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        //声明转发器
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");
        //随机获得一个队列名，该队列具有不可持久化，排他，与消费者断开连接时自动删除的特性
        String queueName = channel.queueDeclare().getQueue();
        //绑定
        channel.queueBind(queueName,EXCHANGE_NAME,"");
        System.out.println("wait for message");

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("Received " + message);
            }
        };
        //自动回复队列
        channel.basicConsume(queueName, true, consumer);
    }
}
