package com.cheers.main.controller;

import com.cheers.main.model.account.User;
import com.cheers.main.model.events.Event;
import com.cheers.main.utils.DBManager;
import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.LatLng;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("event/test")
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

    @GetMapping("event/get/today/position")
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

    @GetMapping("event/get/week/position")
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

    @GetMapping("event/get/month/position")
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

    private List<Event> getCloseEvents(List<Event> eventsInput,
                                       double lat, double lon,
                                       Integer distanceInMeters) {
        List<Event> closestEvents = new ArrayList<>();

        for (Event event : eventsInput) {
            double currDistance = distance(lat,
                    Double.parseDouble(event.getLat()),
                    lon,
                    Double.parseDouble(event.getLon()));
            if (currDistance <= distanceInMeters) {
                closestEvents.add(event);
            }
        }
        return closestEvents;
    }

    /**
     * Calculate distance between two points in latitude and longitude taking
     * into account height difference.
     * <p>
     * lat1, lon1 Start point lat2, lon2 End point
     *
     * @return Distance in Meters
     */
    public double distance(double lat1,
                           double lat2,
                           double lon1,
                           double lon2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        distance = Math.pow(distance, 2);

        return Math.sqrt(distance);
    }
}
