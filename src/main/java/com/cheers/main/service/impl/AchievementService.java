package com.cheers.main.service.impl;

import com.cheers.main.model.Achievement;
import com.cheers.main.model.enums.AchievementType;
import com.cheers.main.repository.AchievementRepository;
import com.cheers.main.service.IAchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AchievementService implements IAchievementService {

    private AchievementRepository achievementRepository;

    @Autowired
    public void setAchievementRepository(AchievementRepository achievementRepository) {
        this.achievementRepository = achievementRepository;
    }

    @Override
    public void saveAchievement(Achievement achievement) {
        achievementRepository.save(achievement);
    }

    @Override
    public Achievement getAchievementByTitle(AchievementType title) {
        return achievementRepository.findByTitle(title).orElseThrow();
    }

    @Override
    public List<Achievement> getAllAchievements() {
        return achievementRepository.findAll();
    }

    @Override
    public Achievement unlockAchievement(AchievementType title) {
        Achievement a = achievementRepository.findByTitle(title).orElseThrow();
        a.setUnlocked(true);
        return a;
    }


}
