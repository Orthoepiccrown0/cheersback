package com.cheers.main.utils;

import com.cheers.main.model.account.User;
import com.cheers.main.model.enums.Sex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
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
        }
    }

    private void createUsers() {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName("Diego");
        user.setSurname("Concetti");
        user.setEmail("diegoco@gmail.com");
        user.setSex(Sex.Male);
        user.setPassword("password");
        user.setBirthday(new Date());
        user.setAvatar("avatar");
        dbManager.getLoginService().saveUser(user);
    }
}
