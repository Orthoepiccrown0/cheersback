package com.cheers.main.utils;

import com.cheers.main.model.account.User;
import com.cheers.main.model.enums.Gender;
import com.cheers.main.model.events.Event;
import com.cheers.main.model.events.Questions;
import com.cheers.main.model.events.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.*;

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
            createTestUnits();

        }
    }

    private void createTestUnits() {
        User user = createUser("diego", "concetti");
        User user2 = createUser("bibbo", "falso");


        createEvent(user, "Titolo dell'evento", "descrizione", true);
        createEvent(user2, "Evento di " + user2.getName(), "descrizione 2", false);
    }

    private User createUser(String name, String surname) {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName(name);
        user.setSurname(surname);
        user.setEmail("d@gmail.com");
        user.setGender(Gender.Male);
        user.setPassword("123");
        user.setBirthday(new Date());
        user.setAvatar(null);
        dbManager.getLoginService().saveUser(user);
        return user;
    }

    private Event createEvent(User user, String title, String description, boolean putQuestion) {
        Event event = new Event();
        event.setId(UUID.randomUUID().toString());
        event.setDescription(description);
        event.setEventDay(new Date());
        event.setGuests(2);
        event.setMedia(null);
        event.setMaxGuests(10);
        event.setMinGuests(0);
        event.setPromoted(false);
        event.setStartSubscription(new Date());
        event.setTitle(title);
        event.setViews(233);
        event.setCommercialCreator(null);
        event.setPrivateCreator(user);
        event.setAddress("via milano");
        event.setLat("43.145266");
        event.setLon("13.098231");

        Tag tag = new Tag();
        tag.setName("tag");
        dbManager.getEventsService().saveTag(tag);

        event.setTags(Collections.singletonList(tag));
        dbManager.getEventsService().createNewEvent(event);

        if (putQuestion) {
            Questions questions = new Questions();
            questions.setId(UUID.randomUUID().toString());
            questions.setEvent(event);
            questions.setQuestion("Ma che famo?");
            questions.setAnswer("Droga e puttane");
            dbManager.getQuestionsService().saveQuestion(questions);
        }

        return event;
    }

}
