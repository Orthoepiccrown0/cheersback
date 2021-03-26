package com.cheers.main.utils;

import com.cheers.main.service.impl.EventsService;
import com.cheers.main.service.impl.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DBManager {

    private LoginService loginService;

    private EventsService eventsService;

    public LoginService getLoginService() {
        return loginService;
    }

    @Autowired
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    public EventsService getEventsService() {
        return eventsService;
    }

    @Autowired
    public void setEventsService(EventsService eventsService) {
        this.eventsService = eventsService;
    }
}
