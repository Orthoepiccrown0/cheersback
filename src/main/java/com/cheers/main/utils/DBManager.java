package com.cheers.main.utils;

import com.cheers.main.model.Media;
import com.cheers.main.service.impl.EventsService;
import com.cheers.main.service.impl.LoginService;
import com.cheers.main.service.impl.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DBManager {

    private LoginService loginService;

    private EventsService eventsService;

    private MediaService mediaService;

    public MediaService getMediaService() {
        return mediaService;
    }

    @Autowired
    public void setMediaService(MediaService mediaService) {
        this.mediaService = mediaService;
    }

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
