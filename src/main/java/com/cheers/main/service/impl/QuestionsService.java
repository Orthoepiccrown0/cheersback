package com.cheers.main.service.impl;

import com.cheers.main.model.Question;
import com.cheers.main.model.events.CommercialEvent;
import com.cheers.main.model.events.PrivateEvent;
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
    public List<Question> findAllUncompletedQuestionsByEvent(PrivateEvent event) {
        return questionsRepository.findAllByAnswerIsNullAndPrivateEvent(event);
    }

    @Override
    public List<Question> findAllUncompletedQuestionsByEvent(CommercialEvent event) {
        return questionsRepository.findAllByAnswerIsNullAndCommercialEvent(event);
    }

    @Override
    public List<Question> findAllQuestionsByEvent(PrivateEvent event) {
        return null;
    }

    @Override
    public List<Question> findAllQuestionsByEvent(CommercialEvent event) {
        return null;
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
