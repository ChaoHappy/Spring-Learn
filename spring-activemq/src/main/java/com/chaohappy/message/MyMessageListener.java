package com.chaohappy.message;

import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@Component
public class MyMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        if(message!=null && message instanceof TextMessage){
            TextMessage textMessage =(TextMessage)message;
            try {
                String text = textMessage.getText();
                System.out.println("****监听器收到消息："+text);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
