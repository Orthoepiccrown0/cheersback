package com.cheers.main.controller;


import com.cheers.main.model.Achievement;
import com.cheers.main.model.account.Company;
import com.cheers.main.model.account.User;
import com.cheers.main.model.enums.AchievementType;
import com.cheers.main.model.enums.Gender;
import com.cheers.main.utils.DBManager;
import com.google.gson.Gson;
import org.hibernate.hql.spi.id.cte.AbstractCteValuesListBulkIdHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@RestController
public class Signup {

    private DBManager dbManager;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @Autowired
    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @PostMapping("/account/user/signup")
    public String signUp(@RequestParam String name,
                         @RequestParam String surname,
                         @RequestParam String birthday,
                         @RequestParam String email,
                         @RequestParam String gender,
                         @RequestParam String password) throws ParseException {
        if (isEmailUsed(email))
            return "email occupata";

        String now = sdf.format(new Date());

        User user = new User();
        user.setBio("");
        user.setId(UUID.randomUUID().toString());
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setPassword(password);
        user.setBirthday(sdf.parse(birthday)); //format: dd/MM/yyyy
        user.setGender(Gender.valueOf(gender));
        user.setSignUpDate(sdf.parse(now));
        user.setNumOfEvents(0);
        dbManager.getLoginService().saveUser(user);
        return new Gson().toJson(user);
    }

    private boolean isEmailUsed(String email) {
        return dbManager.getLoginService().isEmailUsed(email);
    }

    @PostMapping("/account/commercial/signup")
    public String signUpCommercial(@RequestParam String name,
                                   @RequestParam String email,
                                   @RequestParam String pIva,
                                   @RequestParam String password) throws ParseException {
        if (isEmailUsed(email))
            return "email occupata";

        String now = sdf.format(new Date());


        Company company = new Company();
        company.setBio("");
        company.setId(UUID.randomUUID().toString());
        company.setName(name);
        company.setEmail(email);
        company.setpIva(pIva);
        company.setPassword(password);
        company.setNumOfEvents(0);
        company.setSignUpDate(sdf.parse(now));

        dbManager.getLoginService().saveCompany(company);
        return new Gson().toJson(company);
    }

}
