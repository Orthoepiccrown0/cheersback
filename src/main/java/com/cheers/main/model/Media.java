package com.cheers.main.model;

import com.cheers.main.model.enums.MediaType;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Media {

    @Id
    private String id;

    private String url;

    private MediaType type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public MediaType getType() {
        return type;
    }

    public void setType(MediaType type) {
        this.type = type;
    }
}
