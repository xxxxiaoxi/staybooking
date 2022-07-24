package com.laioffer.staybooking.model;

import javax.persistence.*;

@Entity
@Table(name = "stay_reserved_date")
public class StayReservedDate {
    @EmbeddedId
    private StayReservedDateKey id;

    @ManyToOne
    //create foreign key but do not create a new column(stay_id is FK for stay table)
    @MapsId("stay_id")
    private Stay stay;

    public StayReservedDate() {}

    public StayReservedDate(StayReservedDateKey id, Stay stay) {
        this.id = id;
        this.stay = stay;
    }

    public StayReservedDateKey getId() {
        return id;
    }

    public Stay getStay() {
        return stay;
    }
}
