package com.swiggy.fulfilmentservice.requests;

import com.swiggy.fulfilmentservice.models.Location;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssignDeliveryRequest {
    private long orderId;
    private float totalPrice;
    private Location pickupLocation;
    private Location dropLocation;
}
