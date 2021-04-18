package com.cheers.main.controller;


import com.cheers.main.model.Media;
import com.cheers.main.model.account.Company;
import com.cheers.main.model.account.User;
import com.cheers.main.model.enums.Gender;
import com.cheers.main.utils.DBManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

@RestController
public class Signup {

    private DBManager dbManager;

    @Autowired
    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @PostMapping("/account/user/signup")
    public User signUp(@RequestParam String name,
                       @RequestParam String surname,
                       @RequestParam String birthday,
                       @RequestParam String email,
                       @RequestParam String gender,
                       @RequestParam String password,
                       @RequestParam(required = false) Media avatar) throws ParseException {
        User user = new User();
        user.setBio("");
        user.setId(UUID.randomUUID().toString());
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setPassword(password);
        user.setBirthday(new SimpleDateFormat("dd/MM/yyyy").parse(birthday)); //format: dd/MM/yyyy
        user.setGender(Gender.valueOf(gender));
        if (avatar != null)
            user.setAvatar(avatar);

        dbManager.getLoginService().saveUser(user);
        return user;
    }

    @PostMapping("/account/commercial/signup")
    public Company signUpCommercial(@RequestParam String name,
                                    @RequestParam String email,
                                    @RequestParam String pIva,
                                    @RequestParam String password,
                                    Media avatar) throws ParseException {
        Company company = new Company();
        company.setBio("");
        company.setId(UUID.randomUUID().toString());
        company.setName(name);
        company.setEmail(email);
        company.setpIva(pIva);
        company.setPassword(password);
        if (avatar != null)
            company.setAvatar(avatar);

        dbManager.getLoginService().saveCompany(company);
        return company;
    }

}
