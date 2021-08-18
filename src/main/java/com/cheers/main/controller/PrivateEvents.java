package com.cheers.main.controller;

import com.cheers.main.model.account.User;
import com.cheers.main.model.events.PrivateEvent;
import com.cheers.main.model.events.SubscribeRequest;
import com.cheers.main.utils.DBManager;
import com.cheers.main.utils.Helpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("event/private/delete")
    public String deleteEvent(@RequestParam PrivateEvent event) {
        if (dbManager.getEventsService().deletePrivateEvent(event))
            return "ok";
        else
            return "nok";
    }

    @GetMapping("event/private/get")
    public PrivateEvent getPrivateEventById(@RequestParam String eventId) {
        return dbManager.getEventsService().findPrivateEventById(eventId);
    }

    @GetMapping("event/private/get/title")
    public List<PrivateEvent> getPrivateEventsByTitle(@RequestParam String title) {
        return dbManager.getEventsService().getPrivateEventsByTitle(title);
    }

    @PostMapping("event/private/subscribe")
    public PrivateEvent subscribeToEvent(@RequestParam String eventId,
                                         @RequestParam String userId) {

        PrivateEvent event = dbManager.getEventsService().findPrivateEventById(eventId);
        User user = dbManager.getLoginService().findUserById(userId);

        if (event.getPrivate()) {
            SubscribeRequest s = new SubscribeRequest();
            s.setId(UUID.randomUUID().toString());
            s.setAccepted(false);
            s.setPrivateEvent(event);
            s.setUser(user);
            dbManager.getEventsService().saveSubscribeRequest(s);
            return event;
        } else if (!event.getSubscribers().contains(user)) {
            dbManager.getEventsService().subscribeToPrivateEvent(event, user);
        }

        return event;
    }

    @PostMapping("event/private/subrequest/accept")
    public String AcceptSubscribeRequest(
            @RequestParam String subRequestId,
            @RequestParam Boolean isAccepted) {

        SubscribeRequest subRequest = dbManager.getEventsService().findSubscribeRequestById(subRequestId);
        PrivateEvent event = subRequest.getPrivateEvent();
        User user = subRequest.getUser();

        if (isAccepted) {
            if (!event.getSubscribers().contains(user)) {
                subRequest.setAccepted(true);
                dbManager.getEventsService().subscribeToPrivateEvent(event, user);
                return "ok";
            }
        } else {
            dbManager.getEventsService().cancelSubscribeRequest(subRequest);
            return "declined";
        }

        return null;
    }

    @GetMapping("event/private/subrequests")
    public List<SubscribeRequest> getSubscribeRequests(String userId) {
        User user = dbManager.getLoginService().findUserById(userId);
        List<SubscribeRequest> subscribeRequestList = new ArrayList<>();

        if (user != null) {
            List<PrivateEvent> privateEvents = dbManager.getEventsService().getPrivateEventsByCreatorId(userId);
            for (PrivateEvent event : privateEvents) {
                subscribeRequestList.addAll(dbManager.getEventsService().findAllSubRequestsByEvent(event));
            }
        }
        //filtr
        return subscribeRequestList;
    }

    @PostMapping("event/private/unsubscribe")
    public PrivateEvent unsubscribeFromEvent(@RequestParam String eventId,
                                             @RequestParam String userId) {

        PrivateEvent event = dbManager.getEventsService().findPrivateEventById(eventId);
        User user = dbManager.getLoginService().findUserById(userId);

        if (event.getSubscribers().contains(user)) {
            dbManager.getEventsService().unsubscribeFromPrivateEvent(event, user);
        }
        return event;
    }

    @GetMapping("event/private/get/today")
    public List<PrivateEvent> getEventsByToday() {
        List<PrivateEvent> futureEvents = new ArrayList<>();
        int today = currentCalendar.get(Calendar.DATE);
        Date now = new Date();
        for (PrivateEvent e : getAllEvents()) {
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
    public List<PrivateEvent> getEventsByToday(@RequestParam String lat,
                                               @RequestParam String lon,
                                               @RequestParam Integer distanceInM) {
        List<PrivateEvent> futureEvents = new ArrayList<>();
        int today = currentCalendar.get(Calendar.DATE);

        for (PrivateEvent e : getAllEvents()) {
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
    public List<PrivateEvent> getEventsByWeek() {
        List<PrivateEvent> futureEvents = new ArrayList<>();
        int weekOfMonth = currentCalendar.get(Calendar.WEEK_OF_MONTH);
        Date now = new Date();
        for (PrivateEvent e : getAllEvents()) {
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
    public List<PrivateEvent> getEventsByWeek(@RequestParam String lat,
                                              @RequestParam String lon,
                                              @RequestParam Integer distanceInM) {
        List<PrivateEvent> futureEvents = new ArrayList<>();
        int weekOfMonth = currentCalendar.get(Calendar.WEEK_OF_MONTH);

        for (PrivateEvent e : getAllEvents()) {
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
    public List<PrivateEvent> getEventsByMonths() {
        List<PrivateEvent> futureEvents = new ArrayList<>();
        int month = currentCalendar.get(Calendar.MONTH);
        Date now = new Date();
        for (PrivateEvent e : getAllEvents()) {
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
    public List<PrivateEvent> getEventsByMonths(@RequestParam String lat,
                                                @RequestParam String lon,
                                                @RequestParam Integer distanceInM) {
        List<PrivateEvent> futureEvents = new ArrayList<>();
        int month = currentCalendar.get(Calendar.MONTH);

        for (PrivateEvent e : getAllEvents()) {
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

    private List<PrivateEvent> getCloseEvents(List<PrivateEvent> eventsInput,
                                              double lat, double lon,
                                              Integer distanceInMeters) {
        List<PrivateEvent> closestEvents = new ArrayList<>();

        for (PrivateEvent event : eventsInput) {
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
    private List<PrivateEvent> getAllEvents(@RequestParam String lat,
                                            @RequestParam String lon,
                                            @RequestParam Integer distanceInM) {
        return getCloseEvents(dbManager.getEventsService().getPrivateEvents(),
                Double.parseDouble(lat),
                Double.parseDouble(lon),
                distanceInM);
    }

    @GetMapping("/event/private/all")
    private List<PrivateEvent> getAllEvents() {
        return dbManager.getEventsService().getPrivateEvents();
    }

}
