package work_queue;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Jack
 * @create 2018-11-19 16:21
 */
public class Recv2 {
    public static final String QUEUE_NAME="hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("47.106.144.102");
        Connection connection = connectionFactory.newConnection();
        final Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        System.out.println("customer wait for message");
        channel.basicQos(1);//同一时间只接收一个消息，直到向服务端返回ack
        //监听queue，当queue中存在数据则触发该回调函数
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body,"UTF-8");
                System.out.println("customer received message:"+message);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };
        //手动回复队列，如果不向服务端回复ack，那么服务端队列就会重新加入这个消息
        channel.basicConsume(QUEUE_NAME,false,consumer);
    }
}
