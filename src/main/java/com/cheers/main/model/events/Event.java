package com.cheers.main.model.events;

import com.cheers.main.model.account.Account;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Date;
import java.util.List;

@Entity
public class Event {

    @Id
    private String id;

    private String title;

    private String description;

    private String img;

    private Date start;

    private Date end;

    private Integer guests;

    private Integer maxGuests;

    private Integer minGuests;

    private Boolean promoted;

    private Integer views;

    @OneToOne
    private City city;

    @OneToMany
    private List<Tag> tags;

    @OneToOne
    private Account creator;
}
