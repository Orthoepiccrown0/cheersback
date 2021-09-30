package com.cheers.main.repository;

import com.cheers.main.model.Achievement;
import com.cheers.main.model.enums.AchievementType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AchievementRepository extends JpaRepository<Achievement, String> {
    Optional<Achievement> findByTitle(AchievementType title);

}
