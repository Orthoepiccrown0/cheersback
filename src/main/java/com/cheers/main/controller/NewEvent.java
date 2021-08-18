package com.cheers.main.controller;

import com.cheers.main.model.Media;
import com.cheers.main.model.Tag;
import com.cheers.main.model.account.Company;
import com.cheers.main.model.account.User;
import com.cheers.main.model.events.CommercialEvent;
import com.cheers.main.model.events.PrivateEvent;
import com.cheers.main.model.messaging.Chat;
import com.cheers.main.utils.DBManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
public class NewEvent {

    private DBManager dbManager;

    @Autowired
    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @PostMapping("event/private/new")
    public String registerNewEvent(String title,
                                   String description,
                                   String startDate,
                                   String eventDate,
                                   Integer maxSubscribers,
                                   String lat,
                                   String lon,
                                   String address,
                                   String city,
                                   String userid,
                                   String tags,
                                   Boolean isPrivate,
                                   Media media
    ) throws ParseException {
        User privateCreator = dbManager.getLoginService().findUserById(userid);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        PrivateEvent event = new PrivateEvent();
        event.setId(UUID.randomUUID().toString());
        event.setTitle(title);
        event.setDescription(description);
        event.setStartSubscription(dateFormat.parse(startDate));
        event.setEventDay(dateFormat.parse(eventDate));
        event.setMaxGuests(maxSubscribers);
        event.setLat(lat);
        event.setLon(lon);
        event.setCreatedDate(new Date());
        event.setAddress(address);
        event.setCity(city);
        event.setPrivate(isPrivate);

        if (media != null)
            if (media.getId() != null)
                event.setMedia(media);

        if (tags != null) {
            List<Tag> tagsList = new Gson().fromJson(tags, new TypeToken<List<Tag>>() {
            }.getType());
            event.setTags(tagsList);
        }

        event.setCreator(privateCreator);

        Chat chat = new Chat();
        chat.setId(UUID.randomUUID().toString());
        chat.setCreated(new Date());
        dbManager.getRoomsService().saveChat(chat);
        event.setChat(chat);
        dbManager.getEventsService().savePrivateEvent(event);
        return "ok";
    }

    @PostMapping("event/commercial/new")
    public String registerNewEvent(String title,
                                   String description,
                                   String startDate,
                                   String eventDate,
                                   Integer maxSubscribers,
                                   Integer maxRooms,
                                   String lat,
                                   String lon,
                                   String address,
                                   String city,
                                   String userid,
                                   String tags,
                                   Media media
    ) throws ParseException {
        Company company = dbManager.getLoginService().findCompanyById(userid);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        CommercialEvent event = new CommercialEvent();
        event.setId(UUID.randomUUID().toString());
        event.setTitle(title);
        event.setDescription(description);
        event.setStartSubscription(dateFormat.parse(startDate));
        event.setEventDay(dateFormat.parse(eventDate));
        event.setMaxGuests(maxSubscribers);
        event.setMaxRooms(maxRooms);
        event.setLat(lat);
        event.setLon(lon);
        event.setCreatedDate(new Date());
        event.setAddress(address);
        event.setCity(city);

        if (media != null) {
            event.setMedia(media);
        }

        if (tags != null) {
            List<Tag> tagsList = new Gson().fromJson(tags, new TypeToken<List<Tag>>() {
            }.getType());
            event.setTags(tagsList);
        }

        event.setCreator(company);

        Chat chat = new Chat();
        chat.setId(UUID.randomUUID().toString());
        chat.setCreated(new Date());
        dbManager.getRoomsService().saveChat(chat);
//        event.setChat(chat);
        dbManager.getEventsService().saveCommercialEvent(event);
        return "ok";
    }

}
