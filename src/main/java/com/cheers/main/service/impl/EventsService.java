package com.cheers.main.service.impl;

import com.cheers.main.model.account.Company;
import com.cheers.main.model.account.User;
import com.cheers.main.model.events.Event;
import com.cheers.main.model.events.Tag;
import com.cheers.main.repository.EventRepository;
import com.cheers.main.repository.TagRepository;
import com.cheers.main.service.IEventsService;
import com.cheers.main.utils.DBManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EventsService implements IEventsService {

    private DBManager dbManager;

    private EventRepository eventRepository;

    @Autowired
    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @Autowired
    public void setEventRepository(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Event> getEventsByCreatorId(String id) {
        User user = dbManager.getLoginService().findUserById(id);
        Company company = dbManager.getLoginService().findCompanyById(id);

        List<Event> userEvents = eventRepository.findAllByPrivateCreatorIsNotNullAndPrivateCreator(user);
        List<Event> commercialEvents = eventRepository.findAllByCommercialCreatorIsNotNullAndCommercialCreator(company);
        if (userEvents.isEmpty() && commercialEvents.isEmpty())
            return new ArrayList<>();

        if (userEvents.isEmpty())
            return commercialEvents;
        else
            return userEvents;
    }

    @Override
    public List<Event> getEventsByDate(Date date) {
        return eventRepository.findAllByEventDay(date);
    }

    @Override
    public List<Event> getEventsByTitle(String title) {
        return eventRepository.findAllByTitleStartsWith(title);
    }

    @Override
    public List<Event> getPrivateEventsByTitle(String title) {
        return eventRepository.findAllByTitleStartsWithAndPrivateCreatorIsNotNull(title);
    }

    @Override
    public List<Event> getCommercialEventsByTitle(String title) {
        return eventRepository.findAllByTitleStartsWithAndCommercialCreatorIsNotNull(title);
    }

    @Override
    public List<Event> getSubscribedEvents(User user) {
        return eventRepository.findAllBySubscribers(user);
    }

    @Override
    public List<Event> getPrivateEvents() {
        return eventRepository.findAllByPrivateCreatorIsNotNull();
    }

    @Override
    public List<Event> getCommercialEvents() {
        return eventRepository.findAllByCommercialCreatorIsNotNull();
    }

    @Override
    public Event findEventById(String id) {
        return eventRepository.findById(id).orElseThrow();
    }

    @Override
    public void createNewEvent(Event event) {
        eventRepository.save(event);
    }

    @Override
    public void deleteEvent(Event event) {

    }

    @Override
    public void subscribeToEvent(Event event, User user) {
        event.getSubscribers().add(user);
        event.setGuests(event.getGuests() + 1);
        eventRepository.save(event);
    }

    @Override
    public void unsubscribeFromEvent(Event event, User user) {
        event.getSubscribers().remove(user);
        event.setGuests(event.getGuests() - 1);
        eventRepository.save(event);
    }


    public List<Event> getAllEvents() {
        return eventRepository.findAllByOrderByEventDayDesc();
    }


}
