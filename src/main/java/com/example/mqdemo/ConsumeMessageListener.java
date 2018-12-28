package com.example.mqdemo;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumeMessageListener implements MessageListener {

    @Override
    public void onMessage(Message message)
    {
        //User user = (User)SerializationUtils.deserialize(message.getBody());
        String result = new String(message.getBody());
        System.out.println(result);
    }

}
