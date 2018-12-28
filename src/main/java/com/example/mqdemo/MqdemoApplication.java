package com.example.mqdemo;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MqdemoApplication implements CommandLineRunner {


    @Autowired
    RabbitTemplate rabbitTemplate;

    public static void main(String[] args) {
        SpringApplication.run(MqdemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception
    {
        User user = User.builder()
                        .userId(1)
                        .userName("taolesi")
                        .address("address")
                        .build();

        //发送消息 convert and send
        //系统默认调用 SimpleMessageConvert
        //如果object 为对象 则 SerializationUtils 进行 序列化
        //String 则 String

        rabbitTemplate.convertAndSend("ex_car_leads","leadsdistributecrm",user);

        //send 方法, 默认是一样的
        //但是 send 可以 传递 Message

        //实例化一个 Message
//        Gson gson = new Gson();
//        String result = gson.toJson(user);
//
//        MessageProperties messageProperties = new MessageProperties();
//        messageProperties.setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT);
//        messageProperties.setHeader("name","havids");
//        messageProperties.setContentEncoding("utf-8");
//        Message message = new Message(result.getBytes(),messageProperties);

        //rabbitTemplate.send("ex_car_leads","leadsdistributecrm",message);
        //System.out.println();
    }

}

