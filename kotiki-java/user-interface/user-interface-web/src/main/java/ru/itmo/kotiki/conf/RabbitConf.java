package ru.itmo.kotiki.conf;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConf {
    @Autowired
    ConnectionFactory connectionFactory;

    @Bean
    public RabbitAdmin ampqAdmin() {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory);
    }

    @Bean
    public Queue catQueue() {
        return new Queue("catQueue", false);
    }

    @Bean
    public Queue ownerQueue() {
        return new Queue("ownerQueue", false);
    }
}
