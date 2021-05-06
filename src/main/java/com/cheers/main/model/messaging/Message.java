package com.cheers.main.model.messaging;

import com.cheers.main.model.account.Company;
import com.cheers.main.model.account.User;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Message {

    @Id
    private String id;

    private String message;

    @OneToOne
    private User privateSender;

    @OneToOne
    private Company commercialSender;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getPrivateSender() {
        return privateSender;
    }

    public void setPrivateSender(User privateSender) {
        this.privateSender = privateSender;
    }

    public Company getCommercialSender() {
        return commercialSender;
    }

    public void setCommercialSender(Company commercialSender) {
        this.commercialSender = commercialSender;
    }
}
