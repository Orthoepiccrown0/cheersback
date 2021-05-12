package com.cheers.main.service;

import com.cheers.main.model.messaging.Chat;
import com.cheers.main.model.messaging.Message;
import com.cheers.main.model.messaging.Room;

import java.util.List;

public interface IRoomsService {

    List<Room> getRooms(String eventId);

    Room getRoom(String id);

    Chat getChat(String id);

    void saveRoom(Room room);

    void saveChat(Chat chat);

    void saveMessage(Message message);

    void deleteRoom(Room room);
}
