package com.cheers.main.utils;

import com.cheers.main.model.account.User;
import com.cheers.main.model.enums.Gender;
import com.cheers.main.model.events.Event;
import com.cheers.main.model.events.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
            saveUsers();

        }
    }

    private void createEvents(User user, ArrayList<Tag> tagList) {
        Event event = new Event();
        event.setId(UUID.randomUUID().toString());
        event.setDescription("descri");
        event.setEventDay(new Date());
        event.setGuests(1);
        event.setMedia(null);
        event.setMaxGuests(10);
        event.setMinGuests(0);
        event.setPromoted(false);
        event.setStartSubscription(new Date());
        event.setTitle("festa");
        event.setViews(233);
        event.setCity(null);
        event.setCommercialCreator(null);
        event.setPrivateCreator(user);
        event.setAddress("via milano");
        event.setLat("32");
        event.setLon("dsa");

        ArrayList<User> userList = new ArrayList<>();
        userList.add(user);
        event.setSubscribers(userList);
        event.setTags(tagList);

        createEvent(user,tagList);
        dbManager.getEventsService().createNewEvent(event);
    }

    private void saveUsers() {
        User user = createUser();
        ArrayList<Tag> tagList = new ArrayList<>();
        Tag t = new Tag();
        t.setId(Long.parseLong("1"));
        t.setName("Tag1");
        Tag t2 = new Tag();
        t2.setId(Long.parseLong("2"));
        t2.setName("Tag2");
        tagList.add(t);
        tagList.add(t2);

        dbManager.getEventsService().saveTag(t);
        dbManager.getEventsService().saveTag(t2);

        dbManager.getLoginService().saveUser(user);
        createEvents(user, tagList);
    }

    private User createUser() {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName("Diego");
        user.setSurname("Concetti");
        user.setEmail("d@gmail.com");
        user.setGender(Gender.Male);
        user.setPassword("123");
        user.setBirthday(new Date());
        user.setAvatar(null);
        return user;
    }

    private void createEvent(User user, ArrayList<Tag> tagList){
        Event event = new Event();
        event.setId(UUID.randomUUID().toString());
        event.setDescription("descridsadasdsadasdsadsa");
        event.setEventDay(new Date());
        event.setGuests(2);
        event.setMedia(null);
        event.setMaxGuests(10);
        event.setMinGuests(0);
        event.setPromoted(false);
        event.setStartSubscription(new Date());
        event.setTitle("festa2");
        event.setViews(233);
        event.setCity(null);
        event.setCommercialCreator(null);
        event.setPrivateCreator(user);
        event.setAddress("via milano");
        event.setLat("32");
        event.setLon("dsa");

        dbManager.getEventsService().createNewEvent(event);
    }

}
