package com.pj.activeMQ.spring.queue;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Jack
 * @create 2018-11-16 11:33
 */
public class AppConsumer {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("queue/consumer.xml");
    }
}
