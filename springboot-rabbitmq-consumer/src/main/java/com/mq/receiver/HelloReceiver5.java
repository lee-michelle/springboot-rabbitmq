package com.mq.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "manyToManyQueue")
public class HelloReceiver5 {

	@RabbitHandler
	public void process(String hello) {
		System.out.println("Receiver1  : " + hello);
	}
}