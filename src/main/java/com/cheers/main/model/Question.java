package com.cheers.main.model;

import com.cheers.main.model.events.CommercialEvent;
import com.cheers.main.model.events.PrivateEvent;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
public class Question {

    @Id
    private String id;

    private String question;

    private String answer;

    private Date responseDate;

    @OneToOne
    private PrivateEvent privateEvent;

    @OneToOne
    private CommercialEvent commercialEvent;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public PrivateEvent getPrivateEvent() {
        return privateEvent;
    }

    public void setPrivateEvent(PrivateEvent privateEvent) {
        this.privateEvent = privateEvent;
    }

    public CommercialEvent getCommercialEvent() {
        return commercialEvent;
    }

    public void setCommercialEvent(CommercialEvent commercialEvent) {
        this.commercialEvent = commercialEvent;
    }

    public Date getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(Date responseDate) {
        this.responseDate = responseDate;
    }
}
