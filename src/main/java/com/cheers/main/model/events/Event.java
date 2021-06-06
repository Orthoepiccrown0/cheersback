package com.cheers.main.model.events;

import com.cheers.main.model.Media;
import com.cheers.main.model.Tag;
import com.cheers.main.model.account.User;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@MappedSuperclass
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

    @Column(nullable = false)
    private Date createdDate;

    private Integer guests;

    private Integer maxGuests;

    private Integer minGuests;

    private Boolean promoted;

    private Integer views;

    private String lat;

    private String lon;

    private String address;

    @Column(nullable = false)
    private Boolean deleted = false;

    @OneToMany
    private List<Tag> tags;

    @ManyToMany
    private List<User> subscribers;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
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

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<User> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<User> subscribers) {
        this.subscribers = subscribers;
    }
}
