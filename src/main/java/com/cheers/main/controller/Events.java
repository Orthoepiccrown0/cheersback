package com.cheers.main.controller;

import com.cheers.main.model.account.User;
import com.cheers.main.model.events.Event;
import com.cheers.main.utils.DBManager;
import com.cheers.main.utils.Helpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class Events {

    private DBManager dbManager;
    Calendar currentCalendar = Calendar.getInstance(TimeZone.getDefault());

    @Autowired
    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @GetMapping("event/get")
    public List<Event> getEventsByTitle(@RequestParam String title) {
        return dbManager.getEventsService().getEventsByTitle(title);
    }

    @PostMapping("event/subscribe")
    public Event subscribeToEvent(@RequestParam String eventId,
                                  @RequestParam String userId) {

        Event event = dbManager.getEventsService().findEventById(eventId);
        User user = dbManager.getLoginService().findUserById(userId);

        if (!event.getSubscribers().contains(user)) {
            dbManager.getEventsService().subscribeToEvent(event, user);
        }
        return event;
    }

    @PostMapping("event/unsubscribe")
    public void unsubscribeFromEvent(@RequestParam String eventId,
                                     @RequestParam String userId) {

        Event event = dbManager.getEventsService().findEventById(eventId);
        User user = dbManager.getLoginService().findUserById(userId);

        if (event.getSubscribers().contains(user)) {
            dbManager.getEventsService().unsubscribeFromEvent(event, user);
        }
        //return event;
    }



}
