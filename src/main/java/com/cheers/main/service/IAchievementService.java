package com.cheers.main.service;

import com.cheers.main.model.Achievement;
import com.cheers.main.model.enums.AchievementType;

import java.util.List;

public interface IAchievementService {

    void saveAchievement(Achievement achievement);

    Achievement getAchievementByTitle(AchievementType title);

    List<Achievement> getAllAchievements();

    Achievement unlockAchievement(AchievementType title);
}
