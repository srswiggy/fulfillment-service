package com.swiggy.fulfilmentservice.controllers;


import com.swiggy.fulfilmentservice.requests.AssignDeliveryRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fullfilment-management/deliveries")
public class FulfillmentController {
    @PostMapping("")
    ResponseEntity<String> assignDeliveryPartner(@RequestBody AssignDeliveryRequest assignDeliveryRequest) {
        return null;
    }
}
