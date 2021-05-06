package com.cheers.main.repository;

import com.cheers.main.model.messaging.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends JpaRepository<Chat,String> {
}
