package com.cheers.main.model.account;

import com.cheers.main.model.enums.Gender;
import com.cheers.main.model.events.Event;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity
public class User extends Account {

    private String surname;

    private Date birthday;

    private Gender gender;

    @OneToMany
    private List<Event> subscribedEvents;

    public List<Event> getSubscribedEvents() {
        return subscribedEvents;
    }

    public void setSubscribedEvents(List<Event> subscribedEvents) {
        this.subscribedEvents = subscribedEvents;
    }

    public Gender getGender() {
        return gender;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Gender getSex() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
