package com.example.BankServiceDemo.RabbitMqConfig;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class Config {
    private String exchange_name1 = "userId_exchange1";
    private String queue_name1 = "userId_queue1";
    private String queue_name2 ="userId_queue2";
    @Bean
    public DirectExchange directExchange1()
    {
        return new DirectExchange(exchange_name1);
    }
    @Bean
    public Queue registerQueue1()
    {
        return new Queue(queue_name1);
    }
    @Bean
    public Queue registerQueue2()
    {
        return new Queue(queue_name2);
    }

    @Bean
    public Binding bindExcWithQueue1()
    {
        return BindingBuilder.bind(registerQueue1()).to(directExchange1()).with("userId_route1");
    }
    @Bean
    public Binding bindExcWithQueue2()
    {
        return BindingBuilder.bind(registerQueue2()).to(directExchange1()).with("userId_route2");
    }
    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory)
    {
        RabbitTemplate rt = new RabbitTemplate((org.springframework.amqp.rabbit.connection.ConnectionFactory) connectionFactory);
        rt.setMessageConverter(producerJackson2JsonMessageConv());
        return rt;
    }
    @Bean
    public Jackson2JsonMessageConverter producerJackson2JsonMessageConv()
    {
        return new Jackson2JsonMessageConverter();
    }
}
