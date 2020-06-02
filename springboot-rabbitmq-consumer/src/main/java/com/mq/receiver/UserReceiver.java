package com.mq.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.mq.bean.User;

@Component
@RabbitListener(queues = "user")
public class UserReceiver {

	@RabbitHandler
	public void process(User user) {
		System.out.println("user receive  : " + user.getName() + "/" + user.getPass());
	}

}