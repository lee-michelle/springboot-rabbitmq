package com.mq.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "oneToMany")
public class HelloReceiver3 {

	@RabbitHandler
	public void process(String hello) {
		System.out.println("Receiver3  : " + hello);
	}
}