package com.cheers.main.service;

import com.cheers.main.model.messaging.Chat;
import com.cheers.main.model.messaging.Message;

import java.util.List;

public interface IMessaging {

    List<Message> getMessages(String chatId);

    void writeMessage(Message message, Chat chat);


}
