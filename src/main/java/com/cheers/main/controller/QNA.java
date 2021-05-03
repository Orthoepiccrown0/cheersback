package com.cheers.main.controller;

import com.cheers.main.model.events.Event;
import com.cheers.main.model.events.Question;
import com.cheers.main.utils.DBManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
public class QNA {

    private DBManager dbManager;

    @Autowired
    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @GetMapping("/questions")
    public List<Question> getQuestionsByEvent(String eventId) {
        Event event = dbManager.getEventsService().findEventById(eventId);
        return dbManager.getQuestionsService().findAllQuestionsByEvent(event);
    }

    @GetMapping("/questions/uncompleted")
    public List<Question> getUncompletedQuestions(String userId) {
        List<Event> events = dbManager.getEventsService().getEventsByCreatorId(userId);
        List<Question> questions = new ArrayList<>();

        for (Event event : events) {
            questions.addAll(dbManager.getQuestionsService().findAllUncompletedQuestionsByEvent(event));
        }

        return questions;
    }

    @PostMapping("/questions/new")
    public String makeQuestion(@RequestParam String question,
                               @RequestParam String eventId) {
        Event event = dbManager.getEventsService().findEventById(eventId);
        Question q = new Question();
        q.setId(UUID.randomUUID().toString());
        q.setQuestion(question);
        q.setEvent(event);
        dbManager.getQuestionsService().saveQuestion(q);
        return "ok";
    }

    @PostMapping("/questions/answer")
    public String answerQuestion(@RequestParam String answer,
                                 @RequestParam String id) {
        Question q = dbManager.getQuestionsService().findById(id);
        q.setResponseDate(new Date());
        q.setAnswer(answer);
        dbManager.getQuestionsService().saveQuestion(q);
        return "ok";
    }

    @GetMapping("/questions/cancel")
    public String cancelQuestion(@RequestParam String id){
        Question q = dbManager.getQuestionsService().findById(id);
        dbManager.getQuestionsService().cancelQuestion(q);
        return "ok";
    }
}
