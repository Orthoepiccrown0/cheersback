package com.cheers.main.model.events;

import com.cheers.main.model.account.Company;
import com.cheers.main.model.messaging.Room;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
public class CommercialEvent extends Event{

    @OneToMany
    private List<Room> rooms;

    @OneToOne
    private Company creator;

    private Integer maxRooms;

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public Company getCreator() {
        return creator;
    }

    public void setCreator(Company creator) {
        this.creator = creator;
    }

    public Integer getMaxRooms() {
        return maxRooms;
    }

    public void setMaxRooms(Integer maxRooms) {
        this.maxRooms = maxRooms;
    }
}
