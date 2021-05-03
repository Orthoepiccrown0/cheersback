package com.cheers.main.controller;

import com.cheers.main.model.account.Company;
import com.cheers.main.model.account.User;
import com.cheers.main.model.events.Event;
import com.cheers.main.model.events.Tag;
import com.cheers.main.utils.DBManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.bytebuddy.description.method.MethodDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
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

    @PostMapping("event/new")
    public String registerNewEvent(String title,
                                   String description,
                                   String startDate,
                                   String eventDate,
                                   Integer maxSubscribers,
                                   String lat,
                                   String lon,
                                   String address,
                                   String userid,
                                   String tags
    ) throws ParseException {
        Company commercialCreator = dbManager.getLoginService().findCompanyById(userid);
        User privateCreator = dbManager.getLoginService().findUserById(userid);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Event event = new Event();
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

        if (tags != null) {
            List<Tag> tagsList = new Gson().fromJson(tags, new TypeToken<List<Tag>>() {
            }.getType());
            event.setTags(tagsList);
        }


        if (commercialCreator != null)
            event.setCommercialCreator(commercialCreator);
        else if (privateCreator != null)
            event.setPrivateCreator(privateCreator);

        dbManager.getEventsService().createNewEvent(event);
        return "ok";
    }

}
