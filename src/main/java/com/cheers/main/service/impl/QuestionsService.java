package com.cheers.main.service.impl;

import com.cheers.main.model.events.Event;
import com.cheers.main.model.events.Questions;
import com.cheers.main.repository.QuestionsRepository;
import com.cheers.main.service.IQuestionsService;
import com.cheers.main.utils.DBManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionsService implements IQuestionsService {

    @Qualifier
    private QuestionsRepository questionsRepository;

    @Autowired
    public void setQuestionsRepository(QuestionsRepository questionsRepository) {
        this.questionsRepository = questionsRepository;
    }

    @Override
    public List<Questions> findAllQuestionsToAnswer(Event event) {
        return questionsRepository.findAllByAnswerIsNullAndEvent(event);
    }

    @Override
    public List<Questions> findAllQuestionsToEvent(Event event) {
        return questionsRepository.findAllByEvent(event);
    }

    @Override
    public void saveQuestion(Questions questions) {
        questionsRepository.save(questions);
    }

    @Override
    public Questions findById(String id) {
        return questionsRepository.findById(id).orElse(null);
    }
}
