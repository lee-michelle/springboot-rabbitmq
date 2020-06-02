package com.mq.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/*@Component
@RabbitListener(queues = "hello")
public class HelloReceiver {
	@RabbitHandler
	public void process(String hello) {
		System.out.println("Receiver1  : " + hello);
	}
}*/
/**
 * 消息消费者（work queues模式）， 一个生产者生产的消息会被多个消费者尝试接受，但是最终只能有一个消费者能获取
 * 
 * @author Administrator
 *
 */
@Component
public class HelloReceiver {
	/**
	 * hello是队列的名称
	 */
	@RabbitListener(queues = "hello")
	public void getMessage1(String hello) {
		System.out.println("Receiver1  : " + hello);
	}

	@RabbitListener(queues = "hello")
	public void getMessage2(String hello) {
		System.out.println("Receiver2  : " + hello);
	}
}