package com.cheers.main.repository;

import com.cheers.main.model.events.CommercialEvent;
import com.cheers.main.model.events.Event;
import com.cheers.main.model.Question;
import com.cheers.main.model.events.PrivateEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionsRepository extends JpaRepository<Question, String> {

    List<Question> findAllByAnswerIsNullAndPrivateEvent(PrivateEvent event);

    List<Question> findAllByAnswerIsNullAndCommercialEvent(CommercialEvent event);

    List<Question> findAllByPrivateEventAndAnswerIsNotNull(PrivateEvent event);

    List<Question> findAllByCommercialEventAndAnswerIsNotNull(CommercialEvent event);

}
