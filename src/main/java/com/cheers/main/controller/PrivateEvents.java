package com.cheers.main.controller;

import com.cheers.main.model.events.Event;
import com.cheers.main.utils.DBManager;
import com.cheers.main.utils.Helpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class PrivateEvents {

    private DBManager dbManager;
    Calendar currentCalendar = Calendar.getInstance(TimeZone.getDefault());

    @Autowired
    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @GetMapping("event/private/get/today")
    public List<Event> getEventsByToday() {
        List<Event> futureEvents = new ArrayList<>();
        int today = currentCalendar.get(Calendar.DATE);
        Date now = new Date();
        for (Event e : getAllEvents()) {
            if (e.getEventDay().after(now)) {
                Calendar cal = Calendar.getInstance(TimeZone.getDefault());
                cal.setTime(e.getEventDay());
                int eventDay = cal.get(Calendar.DATE);
                if (today == eventDay) {
                    futureEvents.add(e);
                }
            }
        }
        return futureEvents;
    }

    @GetMapping("event/private/get/today/position")
    public List<Event> getEventsByToday(@RequestParam String lat,
                                        @RequestParam String lon,
                                        @RequestParam Integer distanceInM) {
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
        futureEvents = getCloseEvents(futureEvents,
                Double.parseDouble(lat), Double.parseDouble(lon),
                distanceInM);
        return futureEvents;
    }

    @GetMapping("event/private/get/week")
    public List<Event> getEventsByWeek() {
        List<Event> futureEvents = new ArrayList<>();
        int weekOfMonth = currentCalendar.get(Calendar.WEEK_OF_MONTH);
        Date now = new Date();
        for (Event e : getAllEvents()) {
            if (e.getEventDay().after(now)) {
                Calendar cal = Calendar.getInstance(TimeZone.getDefault());
                cal.setTime(e.getEventDay());
                int eventWeek = cal.get(Calendar.WEEK_OF_MONTH);
                if (weekOfMonth == eventWeek) {
                    futureEvents.add(e);
                }
            }
        }
        return futureEvents;
    }

    @GetMapping("event/private/get/week/position")
    public List<Event> getEventsByWeek(@RequestParam String lat,
                                       @RequestParam String lon,
                                       @RequestParam Integer distanceInM) {
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
        futureEvents = getCloseEvents(futureEvents,
                Double.parseDouble(lat), Double.parseDouble(lon),
                distanceInM);
        return futureEvents;
    }

    @GetMapping("event/private/get/month")
    public List<Event> getEventsByMonths() {
        List<Event> futureEvents = new ArrayList<>();
        int month = currentCalendar.get(Calendar.MONTH);
        Date now = new Date();
        for (Event e : getAllEvents()) {
            if (e.getEventDay().after(now)) {
                Calendar cal = Calendar.getInstance(TimeZone.getDefault());
                cal.setTime(e.getEventDay());
                int eventMonth = cal.get(Calendar.MONTH);
                if (month == eventMonth) {
                    futureEvents.add(e);
                }
            }
        }
        return futureEvents;
    }

    @GetMapping("event/private/get/month/position")
    public List<Event> getEventsByMonths(@RequestParam String lat,
                                         @RequestParam String lon,
                                         @RequestParam Integer distanceInM) {
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
        futureEvents = getCloseEvents(futureEvents,
                Double.parseDouble(lat), Double.parseDouble(lon),
                distanceInM);
        return futureEvents;
    }

    private List<Event> getCloseEvents(List<Event> eventsInput,
                                       double lat, double lon,
                                       Integer distanceInMeters) {
        List<Event> closestEvents = new ArrayList<>();

        for (Event event : eventsInput) {
            double currDistance = Helpers.distance(lat,
                    Double.parseDouble(event.getLat()),
                    lon,
                    Double.parseDouble(event.getLon()));
            if (currDistance <= distanceInMeters) {
                closestEvents.add(event);
            }
        }
        return closestEvents;
    }

    @GetMapping("/event/private/all/position")
    private List<Event> getAllEvents(@RequestParam String lat,
                                     @RequestParam String lon,
                                     @RequestParam Integer distanceInM) {
        return getCloseEvents(dbManager.getEventsService().getPrivateEvents(),
                Double.parseDouble(lat),
                Double.parseDouble(lon),
                distanceInM);
    }

    @GetMapping("/event/private/all")
    private List<Event> getAllEvents() {
        return dbManager.getEventsService().getPrivateEvents();
    }

}
