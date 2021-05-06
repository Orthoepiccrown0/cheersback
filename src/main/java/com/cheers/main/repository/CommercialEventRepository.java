package com.cheers.main.repository;

import com.cheers.main.model.account.Company;
import com.cheers.main.model.events.CommercialEvent;
import com.cheers.main.model.events.PrivateEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CommercialEventRepository extends JpaRepository<CommercialEvent, String> {

    List<CommercialEvent> findAllByCreator(Company company);

    List<CommercialEvent> findAllByEventDay(Date date);

    List<CommercialEvent> findAllByTitleStartsWith(String s);

}
