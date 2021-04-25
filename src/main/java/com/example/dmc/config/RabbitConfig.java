package com.example.dmc.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public static final String QUEUE_NAME = "queue";
    public static final String TOPIC_EXCHANGE_NAME = "exchange";
    public static final String ROUTING_KEY_PUT_TASK = "rpc.request.balancer.put_task";
    public static final String ROUTING_KEY_GET_STATISTICS = "rpc.request.balancer.get_statistics";
    // private final Logger logger = Logger.getLogger(RabbitConfig.class);
    private static final String CONNECTION_NAME = "localhost";

    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory(CONNECTION_NAME);
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE_NAME);
    }

   /* @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME);
    }*/
/*
    @Bean
    public Binding binding1() {
        return bind(queue()).to(topicExchange()).with("block.task");
    }*/
}