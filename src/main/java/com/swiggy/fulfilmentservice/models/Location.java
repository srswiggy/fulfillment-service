package com.swiggy.fulfilmentservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Location {
    private float latitude;
    private float longitude;


    private double calculateDistance(float lat1, float lon1, float lat2, float lon2) {
        final int earthRadiusKm = 6371;

        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        lat1 = (float)Math.toRadians(lat1);
        lat2 = (float)Math.toRadians(lat2);

        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.sin(dLon/2) * Math.sin(dLon/2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return earthRadiusKm * c;
    }

    public Location findClosestLocation(List<Location> locations) {
        Location closest = null;
        double closestDistance = Double.MAX_VALUE;

        for (Location location : locations) {
            double distance = calculateDistance(this.latitude, this.longitude, location.getLatitude(), location.getLongitude());
            if (distance < closestDistance) {
                closestDistance = distance;
                closest = location;
            }
        }

        return closest;
    }

    public float getLatitude() {
        return this.latitude;
    }

    public float getLongitude() {
        return this.longitude;
    }
}
