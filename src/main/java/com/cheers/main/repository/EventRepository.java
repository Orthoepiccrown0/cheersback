package com.cheers.main.repository;

import com.cheers.main.model.account.Company;
import com.cheers.main.model.account.User;
import com.cheers.main.model.events.City;
import com.cheers.main.model.events.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, String> {

    List<Event> findAllByCity(City city);

    List<Event> findAllByPrivateCreator(User user);

    List<Event> findAllByCommercialCreator(Company company);
}
