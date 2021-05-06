package com.cheers.main.controller;

import com.cheers.main.model.messaging.Room;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Rooms {

    @GetMapping("/rooms/get")
    public List<Room> getRooms(@RequestParam String eventId) {
        return null;
    }

}
