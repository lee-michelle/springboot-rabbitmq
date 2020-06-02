package com.mq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mq.sender.CallBackSender;
import com.mq.sender.FanoutSender;
import com.mq.sender.HelloSender;
import com.mq.sender.HelloSender2;
import com.mq.sender.HelloSender3;
import com.mq.sender.TopicSender;
import com.mq.sender.UserSender;

@RestController
@RequestMapping("/rabbit")
public class RabbitController {
	@Autowired
	private HelloSender helloSender;

	@Autowired
	private HelloSender2 helloSender2;

	@Autowired
	private HelloSender3 helloSender3;

	@Autowired
	private UserSender userSender;

	@Autowired
	private TopicSender topicSender;

	@Autowired
	private FanoutSender fanoutSender;

	@Autowired
	private CallBackSender callBackSender;

	/**
	 * work
	 */
	@RequestMapping("/hello")
	public void hello() {
		helloSender.send();
	}

	/**
	 * 单生产者-多消费者（从以上结果可知，生产者发送的10条消息，分别被两个消费者接收了）
	 */
	@RequestMapping("/oneToMany")
	public void oneToMany() {
		for (int i = 0; i < 10; i++) {
			helloSender.send("oneToMany:" + i);
		}

	}

	/**
	 * 多生产者-多消费者
	 */
	@RequestMapping("/manyToMany")
	public void manyToMany() {
		for (int i = 0; i < 10; i++) {
			helloSender2.send("manyToManyQueue:" + i);
			helloSender3.send("manyToManyQueue:" + i);
		}
	}

	/**
	 * 实体类传输测试
	 */
	@RequestMapping("/userTest")
	public void userTest() {
		userSender.send();
	}

	/**
	 * topic exchange类型rabbitmq测试
	 */
	@RequestMapping("/topicTest")
	public void topicTest() {
		topicSender.send();
	}

	/**
	 * fanout exchange类型rabbitmq测试
	 */
	@RequestMapping("/fanoutTest")
	public void fanoutTest() {
		fanoutSender.send();
	}

	@RequestMapping("/callback")
	public void callbak() {
		callBackSender.send();
	}
}
