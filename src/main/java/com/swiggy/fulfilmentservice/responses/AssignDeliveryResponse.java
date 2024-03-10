package com.swiggy.fulfilmentservice.responses;

import com.swiggy.fulfilmentservice.models.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssignDeliveryResponse {
    private long id;
    private Status status;
}
