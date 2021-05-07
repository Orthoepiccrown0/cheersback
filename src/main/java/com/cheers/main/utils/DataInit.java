package com.cheers.main.utils;

import com.cheers.main.model.account.Company;
import com.cheers.main.model.account.User;
import com.cheers.main.model.enums.Gender;
import com.cheers.main.model.events.CommercialEvent;
import com.cheers.main.model.events.Event;
import com.cheers.main.model.Question;
import com.cheers.main.model.Tag;
import com.cheers.main.model.events.PrivateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
        User user2 = createUser("luca", "lu");

        Company company = createCompany("IBRI SRL", "123456");


        generateTags();

        createEvent(user, "Titolo dell'evento", "descrizione", true);
        createEvent(user2, "Evento di " + user2.getName(), "descrizione 2", false);

        createCommercialEvent(company, "Titolo commerciale", "evento commerciale", false);
    }

    private void generateTags() {
        String[] tags = {"Pop", "Sport", "Musica", "Rap", "Rock", "Nerds", "PC", "Fotografia", "Discoteca", "Alcol",
                "Arte", "Crossfit", "Ciclismo", "Politica", "Gaming", "Cinema", "Yoga", "Vegan", "Volontariato", "Calcio",
                "Basket", "Cibo", "Festa", "Auto", "Moto", "Working out", "Festivale"};
        for (String tag : tags) {
            Tag t = new Tag();
            t.setName(tag);
            dbManager.getTagsService().saveTag(t);
        }
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

    private Company createCompany(String name, String pIva) {
        Company company = new Company();
        company.setId(UUID.randomUUID().toString());
        company.setName(name);
        company.setEmail("comm@gmail.com");
        company.setPassword("123");
        company.setBio("descrizione della company");
        company.setAvatar(null);
        company.setpIva(pIva);
        dbManager.getLoginService().saveCompany(company);
        return company;
    }

    private Event createEvent(User user, String title, String description, boolean putQuestion) {
        PrivateEvent event = new PrivateEvent();
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
        event.setCreatedDate(new Date());
        event.setCreator(user);
        event.setAddress("via milano");
        event.setLat("43.145266");
        event.setLon("13.098231");

        dbManager.getEventsService().savePrivateEvent(event);

        if (putQuestion) {
            Question question = new Question();
            question.setId(UUID.randomUUID().toString());
            question.setPrivateEvent(event);
            question.setResponseDate(new Date());
            question.setQuestion("Esempio domanda");
            question.setAnswer("Esempio risposta");
            dbManager.getQuestionsService().saveQuestion(question);
        }

        return event;
    }


    private CommercialEvent createCommercialEvent(Company company, String title, String description, boolean putQuestion) {
        CommercialEvent event = new CommercialEvent();
        event.setId(UUID.randomUUID().toString());
        event.setDescription(description);
        event.setEventDay(new Date());
        event.setGuests(4);
        event.setMedia(null);
        event.setMaxGuests(14);
        event.setMinGuests(0);
        event.setPromoted(false);
        event.setStartSubscription(new Date());
        event.setTitle(title);
        event.setViews(233);
        event.setCreatedDate(new Date());
        event.setCreator(company);
        event.setMaxRooms(4);
        event.setAddress("via torino");
        event.setLat("43.145266");
        event.setLon("13.098231");

        dbManager.getEventsService().saveCommercialEvent(event);

        if (putQuestion) {
            Question question = new Question();
            question.setId(UUID.randomUUID().toString());
            question.setCommercialEvent(event);
            question.setResponseDate(new Date());
            question.setQuestion("Esempio domanda");
            question.setAnswer("Esempio risposta");
            dbManager.getQuestionsService().saveQuestion(question);
        }

        return event;
    }

}
