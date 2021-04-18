package com.cheers.main.repository;

import com.cheers.main.model.account.Company;
import com.cheers.main.model.account.User;
import com.cheers.main.model.events.City;
import com.cheers.main.model.events.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, String> {

    List<Event> findAllByCity(City city);

    List<Event> findAllByPrivateCreator(User user);

    List<Event> findAllByCommercialCreator(Company company);

    List<Event> findAllByEventDay(Date eventDay);

    List<Event> findAllByStartSubscriptionLessThanEqualAndEventDayGreaterThanEqual(Date start, Date end);

    List<Event> findAllByTitleStartsWith(String title);


}
