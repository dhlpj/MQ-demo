package com.pj.activeMQ.spring.topic;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Jack
 * @create 2018-11-16 11:33
 */
public class AppSubscriber {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("topic/subscriber.xml");
    }
}
