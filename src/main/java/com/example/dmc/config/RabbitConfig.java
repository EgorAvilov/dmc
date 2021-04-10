package com.example.dmc.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

//http://localhost:8080/emit
@Configuration
public class RabbitConfig {
    public static final String CONNECTION_NAME = "192.168.0.101";
    public static final String QUEUE_NAME = "sample_queue";
    private static final String INTERCEPTOR_MSG = "received from queue : ";
    public static List<String> keys = new ArrayList<>();
    //public static List<DataDto> values = new ArrayList<>();
//TODO подумать надо форматом данных

    /**
     * Bean for connection with RabbitMQ.
     *
     * @return new connection.
     */
    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory(CONNECTION_NAME);
    }

    /**
     * Bean for register/delete queue.
     *
     * @return new admin.
     */
    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    /**
     * producer
     */
    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    // When we can real test rabbit mq please remove

    @Bean
    public FanoutExchange fanoutExchangeA() {
        return new FanoutExchange("exchange-example-3");
    }

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME);
    }

    @Bean
    public Queue testQueue1() {
        return new Queue("testQueue1");
    }

    @Bean
    public Queue testQueue2() {
        return new Queue("testQueue2");
    }

    @Bean
    public Binding binding1() {
        return BindingBuilder.bind(testQueue1())
                             .to(fanoutExchangeA());
    }

    @Bean
    public Binding binding2() {
        return BindingBuilder.bind(testQueue2())
                             .to(fanoutExchangeA());
    }
}