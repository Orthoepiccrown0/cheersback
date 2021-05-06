package com.cheers.main.repository;

import com.cheers.main.model.account.User;
import com.cheers.main.model.events.PrivateEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PrivateEventRepository extends JpaRepository<PrivateEvent, String> {

    List<PrivateEvent> findAllByCreator(User creator);

    List<PrivateEvent> findAllByEventDay(Date date);

    List<PrivateEvent> findAllByTitleStartsWith(String s);

}
