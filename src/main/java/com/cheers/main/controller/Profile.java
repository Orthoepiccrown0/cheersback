package com.cheers.main.controller;

import com.cheers.main.model.Media;
import com.cheers.main.model.account.Account;
import com.cheers.main.model.account.Company;
import com.cheers.main.model.account.User;
import com.cheers.main.model.events.Event;
import com.cheers.main.utils.DBManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Profile {
    private DBManager dbManager;

    @Autowired
    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @GetMapping("user/get")
    public User getUser(String id) {
        return dbManager.getLoginService().findUserById(id);
    }

    @GetMapping("company/get")
    public Company getCompany(String id) {
        return dbManager.getLoginService().findCompanyById(id);
    }

    @PostMapping("user/profile/edit")
    public String editUser(@RequestParam String id,
                           @RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam String bio) {
        User user = dbManager.getLoginService().findUserById(id);
        user.setName(name);
        user.setSurname(surname);
        user.setBio(bio);
        dbManager.getLoginService().saveUser(user);
        return "success";
    }

    @PostMapping("company/profile/edit")
    public String editCompany(@RequestParam String id,
                              @RequestParam String name,
                              @RequestParam String bio) {
        Company company = dbManager.getLoginService().findCompanyById(id);
        company.setName(name);
        company.setBio(bio);
        dbManager.getLoginService().saveCompany(company);
        return "success";
    }

    @PostMapping("user/m/set")
    public String editAvatar(@RequestParam Media media,
                             @RequestParam String user_id) {
        User user = dbManager.getLoginService().findUserById(user_id);
        Company company = dbManager.getLoginService().findCompanyById(user_id);
        if (user != null) {
            user.setAvatar(media);
            dbManager.getLoginService().saveUser(user);
        } else if (company != null) {
            company.setAvatar(media);
            dbManager.getLoginService().saveCompany(company);
        } else {
            return "nope";
        }
        return "success";
    }

    @GetMapping("event/get/user")
    public List<Event> getEventsByUser(@RequestParam String id) {
        return dbManager.getEventsService().getEventsByCreatorId(id);
    }

    @GetMapping("event/get/user/subscribed")
    public List<Event> getSubscribedEvents(@RequestParam String id) {
        User user = dbManager.getLoginService().findUserById(id);
        List<Event> events = dbManager.getEventsService().getAllEvents();
        List<Event> userSubscriptions = new ArrayList<>();
        for (Event event : events) {
            if (event.getSubscribers().contains(user))
                userSubscriptions.add(event);
        }
        return userSubscriptions;
    }


}
