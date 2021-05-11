package com.cheers.main.service.impl;

import com.cheers.main.model.messaging.Chat;
import com.cheers.main.model.messaging.Message;
import com.cheers.main.repository.ChatRepository;
import com.cheers.main.repository.MessageRepository;
import com.cheers.main.repository.RoomRepository;
import com.cheers.main.service.IMessaging;
import com.cheers.main.utils.DBManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessagingService implements IMessaging {

    private DBManager dbManager;

    private MessageRepository messageRepository;

    private ChatRepository chatRepository;

    private RoomRepository roomRepository;

    @Autowired
    public void setChatRepository(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @Autowired
    public void setRoomRepository(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Autowired
    public void setMessageRepository(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Autowired
    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @Override
    public List<Message> getMessages(String chatId) {
        return dbManager.getRoomsService().getChat(chatId).getMessages();
    }

    @Override
    public void writeMessage(Message message, Chat chat) {
        messageRepository.save(message);
        chat.getMessages().add(message);
        chatRepository.save(chat);
    }
}
