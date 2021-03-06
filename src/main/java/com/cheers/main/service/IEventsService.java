package com.cheers.main.service;

import com.cheers.main.container.SubscribedEventResponse;
import com.cheers.main.model.account.User;
import com.cheers.main.model.events.CommercialEvent;
import com.cheers.main.model.events.Event;
import com.cheers.main.model.events.PrivateEvent;
import com.cheers.main.model.events.SubscribeRequest;

import java.util.Date;
import java.util.List;

public interface IEventsService {

    List<PrivateEvent> getPrivateEventsByCreatorId(String id);

    List<CommercialEvent> getCommercialEventsByCreatorId(String id);

    List<PrivateEvent> getPrivateEventsByDate(Date date);

    List<CommercialEvent> getCommercialEventsByDate(Date date);

    List<PrivateEvent> getPrivateEventsByTitle(String title);

    List<CommercialEvent> getCommercialEventsByTitle(String title);

    List<SubscribedEventResponse> getSubscribedEvents(User user);

    List<PrivateEvent> getPrivateEvents();

    List<CommercialEvent> getCommercialEvents();

    void savePrivateEvent(PrivateEvent event);

    void saveCommercialEvent(CommercialEvent event);

    void subscribeToPrivateEvent(PrivateEvent event, User user);

    List<SubscribeRequest> findAllSubRequestsByEvent(PrivateEvent event);

    void saveSubscribeRequest(SubscribeRequest subRequest);

    void cancelSubscribeRequest(SubscribeRequest subRequest);

    void subscribeToCommercialEvent(CommercialEvent event, User user);

    void unsubscribeFromPrivateEvent(PrivateEvent event, User user);

    void unsubscribeFromCommercialEvent(CommercialEvent event, User user);

    boolean deletePrivateEvent(PrivateEvent event);

    boolean deleteCommercialEvent(CommercialEvent event);

    PrivateEvent findPrivateEventById(String id);

    CommercialEvent findCommercialEventById(String id);

    SubscribeRequest findSubscribeRequestById(String id);
}
