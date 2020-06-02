package com.mq.sender;

import java.util.Date;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelloSender {
	@Autowired
	private AmqpTemplate rabbitTemplate;

	/**
	 * worl 消息生产者，消息的名称为hello,在Application启动类中有定义
	 */
	public void send() {
		String sendMsg = "hello1 " + new Date();
		System.out.println("Sender1 : " + sendMsg);
		this.rabbitTemplate.convertAndSend("hello", sendMsg);
	}

	/**
	 * 单生产者-多消费者
	 * 
	 * @param msg
	 */
	public void send(String msg) {
		String sendMsg = msg + new Date();
		System.out.println("Sender1 : " + sendMsg);
		this.rabbitTemplate.convertAndSend("oneToMany", sendMsg);
	}
}
