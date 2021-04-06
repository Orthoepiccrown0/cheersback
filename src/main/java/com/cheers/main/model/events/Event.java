package com.cheers.main.model.events;

import com.cheers.main.model.Media;
import com.cheers.main.model.account.Company;
import com.cheers.main.model.account.User;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Event {

    public Event() {
        this.guests = 0;
        this.views = 0;
        this.minGuests = 2;
        this.promoted = false;
    }

    @Id
    private String id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @OneToOne
    private Media media;

    @Column(nullable = false)
    private Date startSubscription;

    @Column(nullable = false)
    private Date eventDay;

    private Integer guests;

    private Integer maxGuests;

    private Integer minGuests;

    private Boolean promoted;

    private Integer views;

    private String lat;

    private String lon;

    private String address;

    @OneToOne
    private City city;

    @OneToMany
    private List<Tag> tags;

    @OneToOne
    private User privateCreator;

    @OneToOne
    private Company commercialCreator;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartSubscription() {
        return startSubscription;
    }

    public void setStartSubscription(Date startSubscription) {
        this.startSubscription = startSubscription;
    }

    public Date getEventDay() {
        return eventDay;
    }

    public void setEventDay(Date eventDay) {
        this.eventDay = eventDay;
    }

    public Integer getGuests() {
        return guests;
    }

    public void setGuests(Integer guests) {
        this.guests = guests;
    }

    public Integer getMaxGuests() {
        return maxGuests;
    }

    public void setMaxGuests(Integer maxGuests) {
        this.maxGuests = maxGuests;
    }

    public Integer getMinGuests() {
        return minGuests;
    }

    public void setMinGuests(Integer minGuests) {
        this.minGuests = minGuests;
    }

    public Boolean getPromoted() {
        return promoted;
    }

    public void setPromoted(Boolean promoted) {
        this.promoted = promoted;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public User getPrivateCreator() {
        return privateCreator;
    }

    public void setPrivateCreator(User privateCreator) {
        this.privateCreator = privateCreator;
    }

    public Company getCommercialCreator() {
        return commercialCreator;
    }

    public void setCommercialCreator(Company commercialCreator) {
        this.commercialCreator = commercialCreator;
    }
}
