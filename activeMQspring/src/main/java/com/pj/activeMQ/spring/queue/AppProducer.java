package com.pj.activeMQ.spring.queue;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Jack
 * @create 2018-11-16 11:08
 */
public class AppProducer {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("queue/producer.xml");
        ProduceService produceService = ctx.getBean(ProduceService.class);
        for (int i=1;i<=100;i++){
            produceService.sendMessage("activeMQ spring"+i);
        }
    }
}
