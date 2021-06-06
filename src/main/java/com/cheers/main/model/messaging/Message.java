package com.cheers.main.model.messaging;

import com.cheers.main.model.account.Company;
import com.cheers.main.model.account.User;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
public class Message implements Comparable<Message> {

    @Id
    private String id;

    private String message;

    private Date created;

    @OneToOne
    private User privateSender;

    @OneToOne
    private Company commercialSender;

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

    @Override
    public int compareTo(Message o) {
        return created.compareTo(o.created);
    }
}
