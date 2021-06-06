package com.cheers.main.model.events;

import com.cheers.main.model.account.User;
import com.cheers.main.model.messaging.Chat;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class PrivateEvent extends Event {

    @OneToOne
    private User creator;

    @OneToOne
    private Chat chat;

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }
}
