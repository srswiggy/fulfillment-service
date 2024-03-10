package com.swiggy.fulfilmentservice.requests;

import com.swiggy.fulfilmentservice.models.Location;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssignDeliveryRequest {
    private long orderId;
    private float totalPrice;
    private Location pickupLocation;
    private Location dropLocation;
}
