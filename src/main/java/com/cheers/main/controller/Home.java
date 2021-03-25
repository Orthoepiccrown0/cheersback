package com.cheers.main.controller;

import com.cheers.main.model.events.Event;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Home {

    @GetMapping("events/")
    public List<Event> getEvents(@RequestParam String city){
        return null;
    }

}
