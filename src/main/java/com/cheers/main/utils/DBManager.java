package com.cheers.main.utils;

import com.cheers.main.service.impl.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DBManager {

    private LoginService loginService;

    public LoginService getLoginService() {
        return loginService;
    }

    @Autowired
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }
}
