package com.laioffer.staybooking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "stay_image")
public class StayImage implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String url;

    @ManyToOne
    @JoinColumn(name = "stay_id")
    //返回的时候不需要返回房子的id只需要返回一个url
    @JsonIgnore
    private Stay stay;

    public StayImage() {}

    public StayImage(String url, Stay stay) {
        this.url = url;
        this.stay = stay;
    }

    public String getUrl() {
        return url;
    }

    public StayImage setUrl(String url) {
        this.url = url;
        return this;
    }

    public Stay getStay() {
        return stay;
    }

    public StayImage setStay(Stay stay) {
        this.stay = stay;
        return this;
    }
}
