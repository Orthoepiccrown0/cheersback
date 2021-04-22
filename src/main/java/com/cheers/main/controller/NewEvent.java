package com.cheers.main.controller;

import com.cheers.main.model.account.Company;
import com.cheers.main.model.account.User;
import com.cheers.main.model.events.Event;
import com.cheers.main.utils.DBManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
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
                                   Date startDate,
                                   Date eventDate,
                                   Integer maxSubscribers,
                                   String lat,
                                   String lon,
                                   String address,
                                   String userid
    ) {
        Company commercialCreator = dbManager.getLoginService().findCompanyById(userid);
        User privateCreator = dbManager.getLoginService().findUserById(userid);

        //TODO: tags stuff and city parsing

        Event event = new Event();
        event.setId(UUID.randomUUID().toString());
        event.setTitle(title);
        event.setDescription(description);
        event.setStartSubscription(startDate);
        event.setEventDay(eventDate);
        event.setMaxGuests(maxSubscribers);
        event.setLat(lat);
        event.setLon(lon);
        event.setAddress(address);

        if (commercialCreator != null)
            event.setCommercialCreator(commercialCreator);
        else if (privateCreator != null)
            event.setPrivateCreator(privateCreator);

        dbManager.getEventsService().createNewEvent(event);
        return "";
    }

}
