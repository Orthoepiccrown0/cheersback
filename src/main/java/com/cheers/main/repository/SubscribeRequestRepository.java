package com.cheers.main.repository;

import com.cheers.main.model.events.PrivateEvent;
import com.cheers.main.model.events.SubscribeRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscribeRequestRepository extends JpaRepository<SubscribeRequest, String> {
    List<SubscribeRequest> findAllByPrivateEvent(PrivateEvent event);

}
