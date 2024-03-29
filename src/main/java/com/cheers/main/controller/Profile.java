package com.cheers.main.controller;

import com.cheers.main.container.LoginResponse;
import com.cheers.main.container.SubscribedEventResponse;
import com.cheers.main.model.Achievement;
import com.cheers.main.model.Media;
import com.cheers.main.model.account.Company;
import com.cheers.main.model.account.User;
import com.cheers.main.model.enums.AchievementType;
import com.cheers.main.model.events.CommercialEvent;
import com.cheers.main.model.events.PrivateEvent;
import com.cheers.main.utils.DBManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

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

    @GetMapping("account/get")
    public LoginResponse getAccount(String id) {
        User user = dbManager.getLoginService().findUserById(id);
        Company company = dbManager.getLoginService().findCompanyById(id);
        if (user != null)
            return new LoginResponse(user);
        if (company != null)
            return new LoginResponse(company);
        return null;
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
    public List<PrivateEvent> getEventsByUser(@RequestParam String id) {
        return dbManager.getEventsService().getPrivateEventsByCreatorId(id);
    }

    @GetMapping("event/get/company")
    public List<CommercialEvent> getEventsByCompany(@RequestParam String id) {
        return dbManager.getEventsService().getCommercialEventsByCreatorId(id);
    }

    @GetMapping("event/get/user/subscribed")
    public List<SubscribedEventResponse> getSubscribedEvents(@RequestParam String id) {
        User user = dbManager.getLoginService().findUserById(id);
        return dbManager.getEventsService().getSubscribedEvents(user);
    }

    @GetMapping("user/get/achivements")
    public List<Achievement> getUserAchivements(@RequestParam String userId) {
        User user = dbManager.getLoginService().findUserById(userId);
        int numOfSubscriptions = getSubscribedEvents(user.getId()).size();
        Date now = new Date();

        dbManager.getAchievementService().unlockAchievement(AchievementType.IBRER);

        if (user.getNumOfEvents() >= 20)
            dbManager.getAchievementService().unlockAchievement(AchievementType.Creatore);

        if (numOfSubscriptions >= 20)
            dbManager.getAchievementService().unlockAchievement(AchievementType.Avventurierio);

        if ((now.getYear() - user.getSignUpDate().getYear()) == 1)
            dbManager.getAchievementService().unlockAchievement(AchievementType.Veterano);


        return dbManager.getAchievementService().getAllAchievements();
    }


}
