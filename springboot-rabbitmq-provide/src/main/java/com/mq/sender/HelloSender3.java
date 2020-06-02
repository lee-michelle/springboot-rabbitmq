package com.mq.sender;

import java.util.Date;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelloSender3 {
	@Autowired
	private AmqpTemplate rabbitTemplate;

	public void send(String msg) {
		String sendMsg = msg + new Date();
		System.out.println("Sender3 : " + sendMsg);
		this.rabbitTemplate.convertAndSend("manyToManyQueue", sendMsg);
	}
}
