package com.mq.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 由以上结果可知：sender1发送的消息,routing_key是“topic.message”，
 * 所以exchange里面的绑定的binding_key是“topic.message”，topic.＃都符合路由规则;
 * 所以sender1发送的消息，两个队列都能接收到； sender2发送的消息，routing_key是“topic.messages”，
 * 所以exchange里面的绑定的binding_key只有topic.＃都符合路由规则;
 * 所以sender2发送的消息只有队列topic.messages能收到。
 * 
 * @author Administrator
 *
 */
@Component
public class TopicMessageReceiver {

	@RabbitListener(queues = "topic.message")
	public void process(String msg) {
		System.out.println("topicMessageReceiver  : " + msg);
	}

	@RabbitListener(queues = "topic.messages")
	public void process2(String msg) {
		System.out.println("topicMessageReceiver2  : " + msg);
	}
}
