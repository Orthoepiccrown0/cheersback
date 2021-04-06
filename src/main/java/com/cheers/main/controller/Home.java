package com.cheers.main.controller;

import com.cheers.main.model.events.Event;
import com.cheers.main.utils.DBManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class Home {

    private DBManager dbManager;
    Calendar currentCalendar = Calendar.getInstance(TimeZone.getDefault());

    @Autowired
    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @GetMapping("events/")
    public List<Event> getEvents(@RequestParam String city) {
        return null;
    }

    @GetMapping("event/get")
    public List<Event> getEventsByTitle(@RequestParam String title) {
        return dbManager.getEventsService().getEventsByTitle(title);
    }

    private List<Event> getAllEvents() {
        return dbManager.getEventsService().getAllEvents();
    }

    @GetMapping("event/get/today")
    public List<Event> getEventsByToday() {
        List<Event> futureEvents = new ArrayList<>();
        int today = currentCalendar.get(Calendar.DATE);

        for (Event e : getAllEvents()) {
            Calendar cal = Calendar.getInstance(TimeZone.getDefault());
            cal.setTime(e.getEventDay());
            int eventDay = cal.get(Calendar.DATE);
            if (today == eventDay) {
                futureEvents.add(e);
            }
        }
        return futureEvents;
    }

    @GetMapping("event/get/week")
    public List<Event> getEventsByWeek() {
        List<Event> futureEvents = new ArrayList<>();
        int weekOfMonth = currentCalendar.get(Calendar.WEEK_OF_MONTH);

        for (Event e : getAllEvents()) {
            Calendar cal = Calendar.getInstance(TimeZone.getDefault());
            cal.setTime(e.getEventDay());
            int eventWeek = cal.get(Calendar.WEEK_OF_MONTH);
            if (weekOfMonth == eventWeek) {
                futureEvents.add(e);
            }
        }
        return futureEvents;
    }

    @GetMapping("event/get/month")
    public List<Event> getEventsByMonths() {
        List<Event> futureEvents = new ArrayList<>();
        int month = currentCalendar.get(Calendar.MONTH);

        for (Event e : getAllEvents()) {
            Calendar cal = Calendar.getInstance(TimeZone.getDefault());
            cal.setTime(e.getEventDay());
            int eventMonth = cal.get(Calendar.MONTH);
            if (month == eventMonth) {
                futureEvents.add(e);
            }
        }
        return futureEvents;
    }
}
