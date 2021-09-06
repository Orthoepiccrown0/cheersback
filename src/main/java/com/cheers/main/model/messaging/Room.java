package com.cheers.main.model.messaging;

import com.cheers.main.model.Media;
import com.cheers.main.model.account.Company;
import com.cheers.main.model.account.User;

import javax.persistence.*;
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

    @Column(nullable = false)
    private Boolean deleted = false;

    private Date created;

    @OneToOne
    private User creator;

    @OneToOne
    private Company host;

    @OneToMany(cascade = CascadeType.DETACH, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private List<User> members;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Chat chat;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Media image;

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Company getHost() {
        return host;
    }

    public void setHost(Company host) {
        this.host = host;
    }

    public void addMember(User user) {
        if (members == null)
            members = new ArrayList<>();
        members.add(user);
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
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
