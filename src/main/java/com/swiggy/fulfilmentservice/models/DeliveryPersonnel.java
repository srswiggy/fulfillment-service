package com.swiggy.fulfilmentservice.models;

import lombok.Getter;
import lombok.Setter;
import org.apache.el.parser.BooleanNode;

import java.util.List;

@Getter
@Setter
public class DeliveryPersonnel {
    private long id;
    private String username;
    private Location location;

    public Boolean sameLocation(Location location) {
        return this.location.getLatitude() == location.getLatitude() && this.location.getLongitude() == location.getLongitude();
    }
}
