package com.example.mqdemo;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {


    @Autowired
    private ConsumeMessageListener _messageCousumer;

    @Autowired
    private JsonMessageConverter jsonMessageConverter;

    @Bean
     public ConnectionFactory connectionFactory()
     {
         CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
         cachingConnectionFactory.setHost("");
         cachingConnectionFactory.setUsername("");
         cachingConnectionFactory.setPassword("");
         cachingConnectionFactory.setPort(5672);
         cachingConnectionFactory.setVirtualHost("");
         return cachingConnectionFactory;
     }

     @Bean
     public RabbitTemplate rabbitTemplate()
     {
         RabbitTemplate template = new RabbitTemplate(connectionFactory());
         template.setMessageConverter(jsonMessageConverter);
         return template;
     }

     @Bean
     public RabbitAdmin rabbitAdmin()
     {
         return new RabbitAdmin(connectionFactory());
     }

    @Bean
    public SimpleMessageListenerContainer messageListenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory());
        container.setQueues(new Queue(""));
        //container.setMessageListener(_messageCousumer);

        MessageListenerAdapter adapter = new MessageListenerAdapter(new MessageHandler());
        //指定消息转换器
        adapter.setMessageConverter(jsonMessageConverter);
        //设置处理器的消费消息的默认方法
        adapter.setDefaultListenerMethod("onMessage");
        container.setMessageListener(adapter);

        return container;
    }

}
