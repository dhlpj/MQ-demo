package simple;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Jack
 * @create 2018-11-19 16:21
 */
public class Recv {
    public static final String QUEUE_NAME="hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("47.106.144.102");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        System.out.println("customer wait for message");
        //监听queue，当queue中存在数据则触发该回调函数
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body,"UTF-8");
                System.out.println("customer received message:"+message);
            }
        };
        //自动回复队列应答 -- RabbitMQ中的消息确认机制（无论消费者是否成功处理了消息，服务端都认为是成功的）
        channel.basicConsume(QUEUE_NAME,true,consumer);
    }
}
