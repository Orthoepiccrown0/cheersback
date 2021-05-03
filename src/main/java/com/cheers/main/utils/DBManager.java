package com.cheers.main.utils;

import com.cheers.main.model.Media;
import com.cheers.main.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DBManager {

    private LoginService loginService;

    private EventsService eventsService;

    private MediaService mediaService;

    private QuestionsService questionsService;

    private TagsService tagsService;

    public TagsService getTagsService() {
        return tagsService;
    }

    @Autowired
    public void setTagsService(TagsService tagsService) {
        this.tagsService = tagsService;
    }

    public MediaService getMediaService() {
        return mediaService;
    }

    public QuestionsService getQuestionsService() {
        return questionsService;
    }

    @Autowired
    public void setQuestionsService(QuestionsService questionsService) {
        this.questionsService = questionsService;
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
