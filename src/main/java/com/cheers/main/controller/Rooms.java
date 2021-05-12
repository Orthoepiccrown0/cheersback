package com.cheers.main.controller;

import com.cheers.main.model.Media;
import com.cheers.main.model.account.User;
import com.cheers.main.model.events.CommercialEvent;
import com.cheers.main.model.messaging.Chat;
import com.cheers.main.model.messaging.Room;
import com.cheers.main.utils.DBManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
public class Rooms {

    private DBManager dbManager;

    @Autowired
    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @GetMapping("/event/commercial/rooms/get")
    public List<Room> getRooms(@RequestParam String eventId) {
        return dbManager.getRoomsService().getRooms(eventId);
    }

    @GetMapping("/event/commercial/rooms/delete")
    public String deleteRoom(@RequestParam Room room) {
        List<CommercialEvent> events = dbManager.getEventsService().getCommercialEvents();
        for (CommercialEvent e : events) {
            if (e.getRooms().remove(room))
                dbManager.getEventsService().saveCommercialEvent(e);
        }

        dbManager.getRoomsService().deleteRoom(room);
        return "ok";
    }

    @GetMapping("/event/commercial/rooms/leave")
    public String leaveRoom(@RequestParam Room room,
                            @RequestParam User user) {
        room.getMembers().remove(user);
        dbManager.getRoomsService().saveRoom(room);
        return "ok";
    }

    @PostMapping("/event/commercial/rooms/new")
    public String createRoom(
            @RequestParam String eventId,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam Integer maxMembers,
            @RequestParam User creator,
            Media media
    ) {
        CommercialEvent event = dbManager.getEventsService().findCommercialEventById(eventId);
        if (event.getRooms().size() >= event.getMaxRooms())
            return "nok";
        Room room = new Room();
        room.setId(UUID.randomUUID().toString());
        room.setName(name);
        room.setDescription(description);
        room.setCreated(new Date());
        room.setHost(event.getCreator());
        room.setCreator(creator);
        room.setMaxMembers(maxMembers);
        room.addMember(creator);
        if (media != null) {
            if ((media.getId() != null))
                room.setImage(media);
        }

        Chat chat = new Chat();
        chat.setId(UUID.randomUUID().toString());
        chat.setCreated(new Date());
        dbManager.getRoomsService().saveChat(chat);
        room.setChat(chat);
        dbManager.getRoomsService().saveRoom(room);
        event.getRooms().add(room);
        dbManager.getEventsService().saveCommercialEvent(event);

        return "ok";
    }

    @GetMapping("/event/commercial/rooms/enter")
    public String enterRoom(@RequestParam User user,
                            @RequestParam Room room) {
        room.getMembers().add(user);
        dbManager.getRoomsService().saveRoom(room);
        return "ok";
    }

}
