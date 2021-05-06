package com.cheers.main.service;

import com.cheers.main.model.events.CommercialEvent;
import com.cheers.main.model.events.Event;
import com.cheers.main.model.Question;
import com.cheers.main.model.events.PrivateEvent;

import java.util.List;

public interface IQuestionsService {
    List<Question> findAllUncompletedQuestionsByEvent(PrivateEvent event);

    List<Question> findAllUncompletedQuestionsByEvent(CommercialEvent event);

    List<Question> findAllQuestionsByEvent(PrivateEvent event);

    List<Question> findAllQuestionsByEvent(CommercialEvent event);

    void saveQuestion(Question question);

    Question findById(String id);

    void cancelQuestion(Question question);
}
