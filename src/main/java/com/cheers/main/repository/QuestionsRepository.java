package com.cheers.main.repository;

import com.cheers.main.model.events.Event;
import com.cheers.main.model.events.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionsRepository extends JpaRepository<Question, String> {

    List<Question> findAllByAnswerIsNullAndEvent(Event event);

    List<Question> findAllByEventAndAnswerIsNotNull(Event event);

}
