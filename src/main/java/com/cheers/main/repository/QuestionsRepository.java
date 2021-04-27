package com.cheers.main.repository;

import com.cheers.main.model.events.Event;
import com.cheers.main.model.events.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionsRepository extends JpaRepository<Questions, String> {

    List<Questions> findAllByAnswerIsNullAndEvent(Event event);

    List<Questions> findAllByEvent(Event event);

}
