package com.cheers.main.controller;

import com.cheers.main.model.events.Event;
import com.cheers.main.model.events.Questions;
import com.cheers.main.utils.DBManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QNA {

    private DBManager dbManager;

    @Autowired
    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @GetMapping("/questions")
    public List<Questions> getQuestionsByEvent(String eventId) {
        Event event = dbManager.getEventsService().findEventById(eventId);
        return dbManager.getQuestionsService().findAllQuestionsToEvent(event);
    }

    @GetMapping("/questions/uncompleted")
    public List<Questions> getUncompletedQuestions(String eventId) {
        Event event = dbManager.getEventsService().findEventById(eventId);
        return dbManager.getQuestionsService().findAllQuestionsToAnswer(event);
    }
}
