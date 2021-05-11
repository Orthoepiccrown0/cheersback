package com.cheers.main.controller;

import com.cheers.main.model.account.User;
import com.cheers.main.model.messaging.Chat;
import com.cheers.main.model.messaging.Message;
import com.cheers.main.service.impl.MessagingService;
import com.cheers.main.utils.DBManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
public class Messaging {

    @Qualifier
    private DBManager dbManager;

    @Autowired
    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @GetMapping("/messaging/chat")
    public List<Message> getMessages(@RequestParam Chat chat) {
        List<Message> messages = chat.getMessages();
        Collections.sort(messages);
        return messages;
    }

    @PostMapping("/messaging/chat/send/private/message")
    public String sendMessage(@RequestParam Chat chat,
                              @RequestParam String message,
                              @RequestParam User sender) {
        Message msg = new Message();
        msg.setId(UUID.randomUUID().toString());
        msg.setMessage(message);
        msg.setCreated(new Date());
        msg.setPrivateSender(sender);
        dbManager.getMessagingService().writeMessage(msg, chat);
        return "ok";
    }

}
