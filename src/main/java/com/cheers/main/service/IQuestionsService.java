package com.cheers.main.service;

import com.cheers.main.model.events.Event;
import com.cheers.main.model.events.Questions;

import java.util.List;

public interface IQuestionsService {
    List<Questions> findAllQuestionsToAnswer(Event event);

    List<Questions> findAllQuestionsToEvent(Event event);

    void saveQuestion(Questions questions);

    Questions findById(String id);
}
