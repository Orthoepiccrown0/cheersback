package com.cheers.main.service.impl;

import com.cheers.main.container.SubscribedEventResponse;
import com.cheers.main.model.account.Company;
import com.cheers.main.model.account.User;
import com.cheers.main.model.events.CommercialEvent;
import com.cheers.main.model.events.Event;
import com.cheers.main.model.events.PrivateEvent;
import com.cheers.main.repository.CommercialEventRepository;
import com.cheers.main.repository.PrivateEventRepository;
import com.cheers.main.service.IEventsService;
import com.cheers.main.utils.DBManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EventsService implements IEventsService {

    private DBManager dbManager;

    @Qualifier
    private PrivateEventRepository privateEvents;

    @Qualifier
    private CommercialEventRepository commercialEvents;

    @Autowired
    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @Autowired
    public void setPrivateEvents(PrivateEventRepository privateEvents) {
        this.privateEvents = privateEvents;
    }

    @Autowired
    public void setCommercialEvents(CommercialEventRepository commercialEvents) {
        this.commercialEvents = commercialEvents;
    }

    @Override
    public List<PrivateEvent> getPrivateEventsByCreatorId(String id) {
        User user = dbManager.getLoginService().findUserById(id);
        return privateEvents.findAllByCreator(user);
    }

    @Override
    public List<CommercialEvent> getCommercialEventsByCreatorId(String id) {
        Company company = dbManager.getLoginService().findCompanyById(id);
        return commercialEvents.findAllByCreator(company);
    }

    @Override
    public List<PrivateEvent> getPrivateEventsByDate(Date date) {
        return privateEvents.findAllByEventDay(date);
    }

    @Override
    public List<CommercialEvent> getCommercialEventsByDate(Date date) {
        return commercialEvents.findAllByEventDay(date);
    }

    @Override
    public List<PrivateEvent> getPrivateEventsByTitle(String title) {
        return privateEvents.findAllByTitleStartsWith(title);
    }

    @Override
    public List<CommercialEvent> getCommercialEventsByTitle(String title) {
        return commercialEvents.findAllByTitleStartsWith(title);
    }

    @Override
    public List<SubscribedEventResponse> getSubscribedEvents(User user) {
        List<SubscribedEventResponse> events = new ArrayList<>();

        List<PrivateEvent> privateEvents = getPrivateEvents();
        List<CommercialEvent> commercialEvents = getCommercialEvents();

        for (PrivateEvent event : privateEvents) {
            if (event.getSubscribers().contains(user))
                events.add(new SubscribedEventResponse(event));
        }

        for (CommercialEvent event : commercialEvents) {
            if (event.getSubscribers().contains(user))
                events.add(new SubscribedEventResponse(event));
        }

        return events;
    }

    @Override
    public List<PrivateEvent> getPrivateEvents() {
        return privateEvents.findAll(Sort.by(Sort.Direction.DESC, "eventDay"));
    }

    @Override
    public List<CommercialEvent> getCommercialEvents() {
        return commercialEvents.findAll(Sort.by(Sort.Direction.DESC, "eventDay"));
    }

    @Override
    public void savePrivateEvent(PrivateEvent event) {
        privateEvents.save(event);
    }

    @Override
    public void saveCommercialEvent(CommercialEvent event) {
        commercialEvents.save(event);
    }

    @Override
    public void deleteEvent(Event event) {

    }

    @Override
    public void subscribeToPrivateEvent(PrivateEvent event, User user) {
        event.getSubscribers().add(user);
        event.setGuests(event.getGuests() + 1);
        privateEvents.save(event);
    }

    @Override
    public void subscribeToCommercialEvent(CommercialEvent event, User user) {
        event.getSubscribers().add(user);
        event.setGuests(event.getGuests() + 1);
        commercialEvents.save(event);
    }

    @Override
    public void unsubscribeFromPrivateEvent(PrivateEvent event, User user) {

    }

    @Override
    public void unsubscribeFromCommercialEvent(CommercialEvent event, User user) {

    }

    @Override
    public PrivateEvent findPrivateEventById(String id) {
        return privateEvents.findById(id).orElse(null);
    }

    @Override
    public CommercialEvent findCommercialEventById(String id) {
        return commercialEvents.findById(id).orElse(null);
    }
}
