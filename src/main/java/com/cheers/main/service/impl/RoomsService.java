package com.cheers.main.service.impl;

import com.cheers.main.model.events.CommercialEvent;
import com.cheers.main.model.messaging.Chat;
import com.cheers.main.model.messaging.Message;
import com.cheers.main.model.messaging.Room;
import com.cheers.main.repository.ChatRepository;
import com.cheers.main.repository.MessageRepository;
import com.cheers.main.repository.RoomRepository;
import com.cheers.main.service.IRoomsService;
import com.cheers.main.utils.DBManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomsService implements IRoomsService {

    @Qualifier
    private ChatRepository chatRepository;

    @Qualifier
    private RoomRepository roomRepository;

    @Qualifier
    private MessageRepository messageRepository;

    @Qualifier
    private DBManager dbManager;

    @Autowired
    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

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

    @Override
    public List<Room> getRooms(String eventId) {
        CommercialEvent event = dbManager.getEventsService().findCommercialEventById(eventId);
        return event.getRooms();
    }

    @Override
    public Room getRoom(String id) {
        return roomRepository.findById(id).orElse(null);
    }

    @Override
    public Chat getChat(String id) {
        return chatRepository.findById(id).orElse(null);
    }

    @Override
    public void saveRoom(Room room) {
        roomRepository.save(room);
    }

    @Override
    public void saveChat(Chat chat) {
        chatRepository.save(chat);
    }

    @Override
    public void saveMessage(Message message) {
        messageRepository.save(message);
    }
}
