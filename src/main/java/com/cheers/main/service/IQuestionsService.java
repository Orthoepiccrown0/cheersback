package com.cheers.main.service;

import com.cheers.main.model.events.Event;
import com.cheers.main.model.events.Question;

import java.util.List;

public interface IQuestionsService {
    List<Question> findAllUncompletedQuestionsByEvent(Event event);

    List<Question> findAllQuestionsByEvent(Event event);

    void saveQuestion(Question question);

    Question findById(String id);

    void cancelQuestion(Question question);
}
