package com.cheers.main.controller;

import com.cheers.main.container.LoginResponse;
import com.cheers.main.model.account.Company;
import com.cheers.main.model.account.User;
import com.cheers.main.utils.DBManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Signin {

    private DBManager dbManager;

    @Autowired
    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @PostMapping("account/in")
    public LoginResponse login(@RequestParam String email,
                               @RequestParam String password) {
        User user = dbManager.getLoginService().findUserByEmailAndPassword(email, password);
        Company company = dbManager.getLoginService().findCompanyByEmailAndPassword(email, password);
        if (user != null)
            return new LoginResponse(user);
        if (company != null)
            return new LoginResponse(company);

        return null;
    }

}
