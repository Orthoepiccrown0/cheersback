package com.cheers.main.controller;

import com.cheers.main.model.account.User;
import com.cheers.main.model.events.CommercialEvent;
import com.cheers.main.model.events.Event;
import com.cheers.main.model.events.PrivateEvent;
import com.cheers.main.utils.DBManager;
import com.cheers.main.utils.Helpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class CommercialEvents {

    private DBManager dbManager;
    Calendar currentCalendar = Calendar.getInstance(TimeZone.getDefault());

    @Autowired
    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @GetMapping("event/commercial/get")
    public List<CommercialEvent> getEventsByTitle(@RequestParam String title) {
        return dbManager.getEventsService().getCommercialEventsByTitle(title);
    }

    @PostMapping("event/commercial/subscribe")
    public CommercialEvent subscribeToEvent(@RequestParam String eventId,
                                            @RequestParam String userId) {

        CommercialEvent event = dbManager.getEventsService().findCommercialEventById(eventId);
        User user = dbManager.getLoginService().findUserById(userId);

        if (!event.getSubscribers().contains(user)) {
            dbManager.getEventsService().subscribeToCommercialEvent(event, user);
        }
        return event;
    }

    @PostMapping("event/commercial/unsubscribe")
    public void unsubscribeFromEvent(@RequestParam String eventId,
                                     @RequestParam String userId) {

        CommercialEvent event = dbManager.getEventsService().findCommercialEventById(eventId);
        User user = dbManager.getLoginService().findUserById(userId);

        if (event.getSubscribers().contains(user)) {
            dbManager.getEventsService().unsubscribeFromCommercialEvent(event, user);
        }
    }

    @GetMapping("event/commercial/get/today")
    public List<CommercialEvent> getEventsByToday() {
        List<CommercialEvent> futureEvents = new ArrayList<>();
        int today = currentCalendar.get(Calendar.DATE);
        Date now = new Date();
        for (CommercialEvent e : getAllEvents()) {
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

    @GetMapping("event/commercial/get/today/position")
    public List<CommercialEvent> getEventsByToday(@RequestParam String lat,
                                                  @RequestParam String lon,
                                                  @RequestParam Integer distanceInM) {
        List<CommercialEvent> futureEvents = new ArrayList<>();
        int today = currentCalendar.get(Calendar.DATE);

        for (CommercialEvent e : getAllEvents()) {
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

    @GetMapping("event/commercial/get/week")
    public List<CommercialEvent> getEventsByWeek() {
        List<CommercialEvent> futureEvents = new ArrayList<>();
        int weekOfMonth = currentCalendar.get(Calendar.WEEK_OF_MONTH);
        Date now = new Date();
        for (CommercialEvent e : getAllEvents()) {
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

    @GetMapping("event/commercial/get/week/position")
    public List<CommercialEvent> getEventsByWeek(@RequestParam String lat,
                                                 @RequestParam String lon,
                                                 @RequestParam Integer distanceInM) {
        List<CommercialEvent> futureEvents = new ArrayList<>();
        int weekOfMonth = currentCalendar.get(Calendar.WEEK_OF_MONTH);

        for (CommercialEvent e : getAllEvents()) {
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

    @GetMapping("event/commercial/get/month")
    public List<CommercialEvent> getEventsByMonths() {
        List<CommercialEvent> futureEvents = new ArrayList<>();
        int month = currentCalendar.get(Calendar.MONTH);
        Date now = new Date();
        for (CommercialEvent e : getAllEvents()) {
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

    @GetMapping("event/commercial/get/month/position")
    public List<CommercialEvent> getEventsByMonths(@RequestParam String lat,
                                                   @RequestParam String lon,
                                                   @RequestParam Integer distanceInM) {
        List<CommercialEvent> futureEvents = new ArrayList<>();
        int month = currentCalendar.get(Calendar.MONTH);

        for (CommercialEvent e : getAllEvents()) {
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

    private List<CommercialEvent> getCloseEvents(List<CommercialEvent> eventsInput,
                                                 double lat, double lon,
                                                 Integer distanceInMeters) {
        List<CommercialEvent> closestEvents = new ArrayList<>();

        for (CommercialEvent event : eventsInput) {
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

    @GetMapping("/event/commercial/all")
    private List<CommercialEvent> getAllEvents() {
        return dbManager.getEventsService().getCommercialEvents();
    }

}
