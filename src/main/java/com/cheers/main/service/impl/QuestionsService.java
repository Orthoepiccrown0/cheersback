package com.cheers.main.service.impl;

import com.cheers.main.model.events.Event;
import com.cheers.main.model.events.Question;
import com.cheers.main.repository.QuestionsRepository;
import com.cheers.main.service.IQuestionsService;
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
    public List<Question> findAllUncompletedQuestionsByEvent(Event event) {
        return questionsRepository.findAllByAnswerIsNullAndEvent(event);
    }

    @Override
    public List<Question> findAllQuestionsByEvent(Event event) {
        return questionsRepository.findAllByEventAndAnswerIsNotNull(event);
    }

    @Override
    public void saveQuestion(Question question) {
        questionsRepository.save(question);
    }

    @Override
    public Question findById(String id) {
        return questionsRepository.findById(id).orElse(null);
    }

    @Override
    public void cancelQuestion(Question item) {
        questionsRepository.delete(item);
    }
}
