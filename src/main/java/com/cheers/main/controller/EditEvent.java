package com.cheers.main.controller;

import com.cheers.main.model.Media;
import com.cheers.main.model.events.CommercialEvent;
import com.cheers.main.model.events.PrivateEvent;
import com.cheers.main.utils.DBManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@RestController
public class EditEvent {
    private DBManager dbManager;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    @Autowired
    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }


    //standardEvent
    @PostMapping("event/private/update")
    public String updatePrivateEvent(String description,
                                     String startDate,
                                     String eventDate,
                                     Integer maxSubscribers,
                                     String lat,
                                     String lon,
                                     String address,
                                     String city,
                                     Boolean isPrivate,
                                     String eventId,
                                     Media media
    ) throws ParseException {
        PrivateEvent event = dbManager.getEventsService().findPrivateEventById(eventId);

        event.setDescription(description);
        event.setStartSubscription(dateFormat.parse(startDate));
        event.setEventDay(dateFormat.parse(eventDate));
        event.setMaxGuests(maxSubscribers);
        event.setLat(lat);
        event.setLon(lon);
        event.setAddress(address);
        event.setCity(city);
        event.setPrivate(isPrivate);

        if (media != null && media.getUrl() != null) {
            event.setMedia(media);
        }

        dbManager.getEventsService().savePrivateEvent(event);
        return "ok";
    }

    @PostMapping("event/commercial/update")
    public String updateCommercialEvent(String description,
                                        String startDate,
                                        String eventDate,
                                        Integer maxSubscribers,
                                        Integer maxRooms,
                                        String lat,
                                        String lon,
                                        String address,
                                        String city,
                                        String eventId,
                                        Media media
    ) throws ParseException {
        CommercialEvent event = dbManager.getEventsService().findCommercialEventById(eventId);

        event.setDescription(description);
        event.setStartSubscription(dateFormat.parse(startDate));
        event.setEventDay(dateFormat.parse(eventDate));
        event.setMaxGuests(maxSubscribers);
        event.setMaxRooms(maxRooms);
        event.setLat(lat);
        event.setLon(lon);
        event.setAddress(address);
        event.setCity(city);

        if (media != null && media.getUrl() != null) {
            event.setMedia(media);
        }

        dbManager.getEventsService().saveCommercialEvent(event);
        return "ok";
    }
}
