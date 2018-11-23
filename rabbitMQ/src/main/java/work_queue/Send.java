package work_queue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Jack
 * @create 2018-11-19 15:58
 */
public class Send {
    public static final String QUEUE_NAME="hello";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("47.106.144.102");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        //当队列不存在的时候才会创建队列(队列名称，是否持久化，是否独占队列(受限于这个连接)，消费者与队列断开连接是否自动删除队列，队列构造参数）
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //向队列中发送数据（""代表默认的转发器）
        for (int i=1;i<=100;i++){
            String message = "test"+i;
            channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
            System.out.println("send "+message);
            //Thread.sleep(10*i);
        }
        channel.close();
        connection.close();
    }
}
