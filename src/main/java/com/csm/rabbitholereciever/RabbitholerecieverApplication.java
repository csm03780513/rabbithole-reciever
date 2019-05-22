package com.csm.rabbitholereciever;

import com.csm.rabbitholereciever.controllers.Reciever;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;

import java.util.concurrent.TimeUnit;


@SpringBootApplication
public class RabbitholerecieverApplication {


    @Value("${rabbithole.rabbitmq.queue}")
    String queueName;

    @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
        return new MappingJackson2MessageConverter();
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(listenerAdapter);
        //System.out.println("COnnectionFactusername "+connectionFactory.getUsername());
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(Reciever receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }


    public static void main(String[] args) {
        SpringApplication.run(RabbitholerecieverApplication.class, args);
    }

}
