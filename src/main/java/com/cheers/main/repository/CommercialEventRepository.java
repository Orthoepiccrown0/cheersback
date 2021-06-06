package com.cheers.main.repository;

import com.cheers.main.model.account.Company;
import com.cheers.main.model.events.CommercialEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CommercialEventRepository extends JpaRepository<CommercialEvent, String> {

    List<CommercialEvent> findAllByCreatorAndDeleted(Company company, boolean deleted);

    List<CommercialEvent> findAllByEventDayAndDeleted(Date date, boolean deleted);

    List<CommercialEvent> findAllByTitleStartsWithAndDeleted(String s, boolean deleted);

    List<CommercialEvent> findAllByDeletedOrderByEventDayDesc(boolean deleted);
}
