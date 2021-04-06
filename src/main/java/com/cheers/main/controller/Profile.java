package com.cheers.main.controller;

import com.cheers.main.model.account.Account;
import com.cheers.main.model.account.Company;
import com.cheers.main.model.account.User;
import com.cheers.main.utils.DBManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
