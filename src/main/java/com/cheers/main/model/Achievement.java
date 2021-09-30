package com.cheers.main.model;

import com.cheers.main.model.enums.AchievementType;

import javax.persistence.*;

@Entity
public class Achievement {

    @Id
    private String id;

    private AchievementType title;

    private String description;

    @OneToOne
    private Media image;

    private Boolean isUnlocked;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AchievementType getTitle() {
        return title;
    }

    public void setTitle(AchievementType title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Media getImage() {
        return image;
    }

    public void setImage(Media image) {
        this.image = image;
    }

    public Boolean getUnlocked() {
        return isUnlocked;
    }

    public void setUnlocked(Boolean unlocked) {
        isUnlocked = unlocked;
    }
}
