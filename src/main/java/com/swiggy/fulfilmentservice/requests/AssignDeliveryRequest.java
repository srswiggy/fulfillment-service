package com.swiggy.fulfilmentservice.requests;

import com.swiggy.fulfilmentservice.models.Location;

public class AssignDeliveryRequest {
    private long orderId;
    private float totalPrice;
    private Location pickupLocation;
    private Location dropLocation;
}
