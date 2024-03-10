package com.swiggy.fulfilmentservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.el.parser.BooleanNode;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryPersonnel {
    private long id;
    private String username;
    private Location location;
    private String role;

    public Boolean sameLocation(Location location) {
        return this.location.getLatitude() == location.getLatitude() && this.location.getLongitude() == location.getLongitude();
    }
}
