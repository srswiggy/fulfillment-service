package com.swiggy.fulfilmentservice.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryPersonnelTest {
    @Test
    public void testSameLocationTrue() {
        Location location1 = new Location(-23.53F, 123.342F);
        DeliveryPersonnel personnel = new DeliveryPersonnel();
        personnel.setLocation(location1);

        Location location2 = new Location(-23.53F, 123.342F);

        assertTrue(personnel.sameLocation(location2));
    }

    @Test
    public void testSameLocationFalseDifferentLatitude() {
        Location location1 = new Location(-23.53343F, 123.342F);
        DeliveryPersonnel personnel = new DeliveryPersonnel();
        personnel.setLocation(location1);

        Location location2 = new Location(-22.53343F, 123.342F);

        assertFalse(personnel.sameLocation(location2));
    }

    @Test
    public void testSameLocationFalseDifferentLongitude() {
        Location location1 = new Location(-23.53343F, 123.342F);
        DeliveryPersonnel personnel = new DeliveryPersonnel();
        personnel.setLocation(location1);

        Location location2 = new Location(-23.53343F, 124.342F);

        assertFalse(personnel.sameLocation(location2));
    }
}