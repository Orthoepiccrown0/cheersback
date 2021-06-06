package com.cheers.main.controller;

import com.cheers.main.model.Question;
import com.cheers.main.model.account.Company;
import com.cheers.main.model.account.User;
import com.cheers.main.model.events.CommercialEvent;
import com.cheers.main.model.events.PrivateEvent;
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
        PrivateEvent privateEvent = dbManager.getEventsService().findPrivateEventById(eventId);
        CommercialEvent commercialEvent = dbManager.getEventsService().findCommercialEventById(eventId);
        if (privateEvent != null) {
            return dbManager.getQuestionsService().findAllQuestionsByEvent(privateEvent);
        } else if (commercialEvent != null) {
            return dbManager.getQuestionsService().findAllQuestionsByEvent(commercialEvent);
        }
        return null;
    }

    @GetMapping("/questions/uncompleted")
    public List<Question> getUncompletedQuestions(String userId) {
        User user = dbManager.getLoginService().findUserById(userId);
        Company company = dbManager.getLoginService().findCompanyById(userId);

        List<Question> questions = new ArrayList<>();
        if (user != null) {
            List<PrivateEvent> privateEvents = dbManager.getEventsService().getPrivateEventsByCreatorId(userId);
            for (PrivateEvent event : privateEvents) {
                questions.addAll(dbManager.getQuestionsService().findAllUncompletedQuestionsByEvent(event));
            }
        } else if (company != null) {
            List<CommercialEvent> commercialEvents = dbManager.getEventsService().getCommercialEventsByCreatorId(userId);
            for (CommercialEvent event : commercialEvents) {
                questions.addAll(dbManager.getQuestionsService().findAllUncompletedQuestionsByEvent(event));
            }
        }


        return questions;
    }

    @PostMapping("/questions/new")
    public String makeQuestion(@RequestParam String question,
                               @RequestParam String eventId) {
        PrivateEvent privateEvent = dbManager.getEventsService().findPrivateEventById(eventId);
        CommercialEvent commercialEvent = dbManager.getEventsService().findCommercialEventById(eventId);

        Question q = new Question();
        q.setId(UUID.randomUUID().toString());
        q.setQuestion(question);

        if (privateEvent != null)
            q.setPrivateEvent(privateEvent);
        else if (commercialEvent != null)
            q.setCommercialEvent(commercialEvent);
        else
            return "error";

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
    public String cancelQuestion(@RequestParam String id) {
        Question q = dbManager.getQuestionsService().findById(id);
        dbManager.getQuestionsService().cancelQuestion(q);
        return "ok";
    }
}
