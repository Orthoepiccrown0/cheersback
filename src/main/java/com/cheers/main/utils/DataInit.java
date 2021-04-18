package com.cheers.main.utils;

import com.cheers.main.model.account.User;
import com.cheers.main.model.enums.Gender;
import com.cheers.main.model.events.City;
import com.cheers.main.model.events.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class DataInit implements CommandLineRunner {

    private DBManager dbManager;

    @Autowired
    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @Override
    public void run(String... args) throws Exception {
        if (dbManager.getLoginService().getAll().size() == 0) {
            createUsers();
            createEvents();
        }
    }

    private void createEvents() {
        Event event = new Event();
        event.setId(UUID.randomUUID().toString());
        event.setDescription("descri");
        event.setEventDay(new Date());
        event.setGuests(2);
        event.setMedia(null);
        event.setMaxGuests(10);
        event.setMinGuests(0);
        event.setPromoted(false);
        event.setStartSubscription(new Date());
        event.setTitle("festa");
        event.setViews(233);
        event.setCity(null);
        event.setCommercialCreator(null);
        event.setPrivateCreator(null);
        event.setAddress("via milano");
        event.setLat("32");
        event.setLon("dsa");

        dbManager.getEventsService().createNewEvent(event);
    }

    private void createUsers() {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName("Diego");
        user.setSurname("Concetti");
        user.setEmail("d@m");
        user.setGender(Gender.Male);
        user.setPassword("password");
        user.setBirthday(new Date());
        user.setAvatar(null);
        dbManager.getLoginService().saveUser(user);
    }
}
