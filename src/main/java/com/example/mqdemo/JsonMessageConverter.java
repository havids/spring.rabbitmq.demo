package com.example.mqdemo;

import com.google.gson.Gson;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

/**
 *  自定义 MessageConverter ----  jsonConverter
 */

@Component
public class JsonMessageConverter implements MessageConverter {

    @Override
    public Message toMessage(Object o, MessageProperties messageProperties) throws MessageConversionException
    {

        Gson gson = new Gson();
        String result = gson.toJson(o);

        messageProperties.setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT);
        messageProperties.setHeader("name","havids");
        messageProperties.setContentEncoding("utf-8");

        Message message = new Message(result.getBytes(),messageProperties);
        return message;
    }

    @Override
    public Object fromMessage(Message message) throws MessageConversionException {
        String s = new String(message.getBody());
        return "fromMessage:"+s;
    }

}
