package com.cheers.main.model.events;

import com.cheers.main.model.account.User;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class SubscribeRequest {

    @Id
    private String id;

    private Boolean isAccepted;

    @OneToOne
    private User user;

    @OneToOne
    private PrivateEvent privateEvent;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getAccepted() {
        return isAccepted;
    }

    public void setAccepted(Boolean accepted) {
        isAccepted = accepted;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PrivateEvent getPrivateEvent() {
        return privateEvent;
    }

    public void setPrivateEvent(PrivateEvent privateEvent) {
        this.privateEvent = privateEvent;
    }


}
