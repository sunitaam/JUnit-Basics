package com.example.rmqconsumer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.rmqconsumer.listener.MessageListener;



@Configuration
public class RmqConfig {

	public static final String ROUTING_KEY = "my.queue.key";
	
	@Bean
	Queue queue() {
		return new Queue(ROUTING_KEY, true);
		//true means durable - persistance 
	}
	
	@Bean
	TopicExchange exchange() {
		return new TopicExchange("my_queue_exchange");
	}
	
	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
	}

	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter adapter) {
		SimpleMessageListenerContainer messageListenerContainer = new SimpleMessageListenerContainer();
		messageListenerContainer.setConnectionFactory(connectionFactory);
		messageListenerContainer.setQueueNames(ROUTING_KEY);
		messageListenerContainer.setMessageListener(adapter);
		return messageListenerContainer;
	}
	
	@Bean
	MessageListenerAdapter myQueueListener(MessageListener listener) {
		return new MessageListenerAdapter();
	}
}
