package com.mq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootRabbitmqApplication {
	final static String queueName = "hello";

	// work模型
	@Bean
	public Queue helloQueue() {
		return new Queue("hello");
	}

	/**
	 * 单生产者-多消费者（从以上结果可知，生产者发送的10条消息，分别被两个消费者接收了）
	 */
	@Bean
	public Queue oneToManyQueue() {
		return new Queue("oneToMany");
	}

	/**
	 * 多生产者-多消费者
	 */
	@Bean
	public Queue manyToManyQueue() {
		return new Queue("manyToManyQueue");
	}

	/**
	 * 实体类传输测试
	 */
	@Bean
	public Queue userQueue() {
		return new Queue("user");
	}

	// ===============以下是验证topic Exchange的队列==========
	@Bean
	public Queue queueMessage() {
		return new Queue("topic.message");
	}

	@Bean
	public Queue queueMessages() {
		return new Queue("topic.messages");
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange("exchange");
	}

	/**
	 * 将队列topic.message与exchange绑定，binding_key为topic.message,就是完全匹配
	 * 
	 * @param queueMessage
	 * @param exchange
	 * @return
	 */
	@Bean
	Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange) {
		return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
	}

	/**
	 * 将队列topic.messages与exchange绑定，binding_key为topic.#,模糊匹配
	 * 
	 * @param queueMessage
	 * @param exchange
	 * @return
	 */
	@Bean
	Binding bindingExchangeMessages(Queue queueMessages, TopicExchange exchange) {
		return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");
	}
	// ===============以上是验证topic Exchange的队列==========

	// ===============以下是验证Fanout Exchange的队列==========
	@Bean
	public Queue AMessage() {
		return new Queue("fanout.A");
	}

	@Bean
	public Queue BMessage() {
		return new Queue("fanout.B");
	}

	@Bean
	public Queue CMessage() {
		return new Queue("fanout.C");
	}
	// ===============以上是验证Fanout Exchange的队列==========

	@Bean
	FanoutExchange fanoutExchange() {
		return new FanoutExchange("fanoutExchange");
	}

	@Bean
	Binding bindingExchangeA(Queue AMessage, FanoutExchange fanoutExchange) {
		return BindingBuilder.bind(AMessage).to(fanoutExchange);
	}

	@Bean
	Binding bindingExchangeB(Queue BMessage, FanoutExchange fanoutExchange) {
		return BindingBuilder.bind(BMessage).to(fanoutExchange);
	}

	@Bean
	Binding bindingExchangeC(Queue CMessage, FanoutExchange fanoutExchange) {
		return BindingBuilder.bind(CMessage).to(fanoutExchange);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRabbitmqApplication.class, args);
	}

}
