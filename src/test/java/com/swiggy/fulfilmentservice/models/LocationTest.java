package com.swiggy.fulfilmentservice.models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LocationTest {
    @Test
    public void testFindClosestLocation() {
        Location referenceLocation = new Location(0, 0);

        Location location1 = new Location(1, 1);
        Location location2 = new Location(-1, -1);
        Location location3 = new Location(10, 10);

        List<Location> locations = Arrays.asList(location1, location2, location3);
        Location closest = referenceLocation.findClosestLocation(locations);

        assertEquals(location1.getLatitude(), closest.getLatitude());
        assertEquals(location1.getLongitude(), closest.getLongitude());
    }

    @Test
    public void testFindClosestLocationWithEmptyList() {
        Location referenceLocation = new Location(0, 0);
        List<Location> locations = new ArrayList<>();

        Location closest = referenceLocation.findClosestLocation(locations);

        assertNull(closest);
    }

    @Test
    public void testFindClosestLocationWithNullListThrowsException() {
        Location referenceLocation = new Location(0, 0);

        assertThrows(Exception.class, ()->referenceLocation.findClosestLocation(null));
    }
}