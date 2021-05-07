package com.cheers.main.model.messaging;

import com.cheers.main.model.Media;
import com.cheers.main.model.account.User;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Room {

    @Id
    private String id;

    private String name;

    private String description;

    private Integer maxMembers;

    private Integer membersNum;

    private Date created;

    @OneToMany
    private List<User> members;

    @OneToOne
    private Chat chat;

    @OneToOne
    private Media image;

    public void addMember(User user) {
        if (members == null)
            members = new ArrayList<>();
        members.add(user);
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMaxMembers() {
        return maxMembers;
    }

    public void setMaxMembers(Integer maxMembers) {
        this.maxMembers = maxMembers;
    }

    public Integer getMembersNum() {
        return membersNum;
    }

    public void setMembersNum(Integer membersNum) {
        this.membersNum = membersNum;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public Media getImage() {
        return image;
    }

    public void setImage(Media image) {
        this.image = image;
    }
}
