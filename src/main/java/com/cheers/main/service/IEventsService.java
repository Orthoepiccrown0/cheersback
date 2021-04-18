package com.cheers.main.service;

import com.cheers.main.model.account.User;
import com.cheers.main.model.events.City;
import com.cheers.main.model.events.Event;

import java.util.Date;
import java.util.List;

public interface IEventsService {

    List<Event> getEventsByCity(City city);

    List<Event> getEventsByCreatorId(String id);

    List<Event> getEventsByDate(Date date);

    List<Event> getEventsByTitle(String title);

    void createNewEvent(Event event);

    void deleteEvent(Event event);

    void subscribeToEvent(Event event, User user);

    void unsubscribeFromEvent(Event event, User user);

    Event findEventById(String id);
}
