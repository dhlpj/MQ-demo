package com.pj.activeMQ.spring.topic;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Jack
 * @create 2018-11-16 11:08
 */
public class AppPublisher {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("topic/publisher.xml");
        PublisherService publisherService = ctx.getBean(PublisherService.class);
        for (int i=1;i<=100;i++){
            publisherService.sendMessage("activeMQ spring"+i);
        }
    }
}
