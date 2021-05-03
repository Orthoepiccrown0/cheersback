package com.cheers.main.repository;

import com.cheers.main.model.account.Company;
import com.cheers.main.model.account.User;
import com.cheers.main.model.events.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, String> {

    List<Event> findAllByPrivateCreatorIsNotNullAndPrivateCreator(User user);

    List<Event> findAllByCommercialCreatorIsNotNullAndCommercialCreator(Company company);

    List<Event> findAllByEventDay(Date eventDay);

    List<Event> findAllByStartSubscriptionLessThanEqualAndEventDayGreaterThanEqual(Date start, Date end);

    List<Event> findAllByTitleStartsWith(String title);

    List<Event> findAllBySubscribers(User user);

    List<Event> findAllByOrderByEventDayDesc();

}
