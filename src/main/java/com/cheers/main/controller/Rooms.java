package com.cheers.main.controller;

import com.cheers.main.model.Media;
import com.cheers.main.model.events.CommercialEvent;
import com.cheers.main.model.messaging.Room;
import com.cheers.main.utils.DBManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class Rooms {

    private DBManager dbManager;

    @Autowired
    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @GetMapping("/rooms/get")
    public List<Room> getRooms(@RequestParam String eventId) {
        return dbManager.getRoomsService().getRooms(eventId);
    }

    @PostMapping("/rooms/new")
    public String createRoom(
            @RequestParam String eventId,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam Integer maxMembers,
            Media media
    ) {
        CommercialEvent event = dbManager.getEventsService().findCommercialEventById(eventId);
        if (event.getRooms().size() >= event.getMaxRooms())
            return "nok";
        Room room = new Room();
        room.setId(UUID.randomUUID().toString());
        room.setName(name);
        room.setDescription(description);
        room.setMaxMembers(maxMembers);

        if (media != null) {
            room.setImage(media);
        }

        dbManager.getRoomsService().saveRoom(room);
        event.getRooms().add(room);
        dbManager.getEventsService().saveCommercialEvent(event);

        return "ok";
    }

}
