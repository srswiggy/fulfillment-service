package com.swiggy.fulfilmentservice.controllers;


import com.swiggy.fulfilmentservice.requests.AssignDeliveryRequest;
import com.swiggy.fulfilmentservice.responses.AssignDeliveryResponse;
import com.swiggy.fulfilmentservice.services.FulfillmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("/fullfilment-management/deliveries")
public class FulfillmentController {
    @Autowired
    FulfillmentService service;
    @PostMapping("")
    ResponseEntity<AssignDeliveryResponse> assignDeliveryPartner(@RequestBody AssignDeliveryRequest assignDeliveryRequest) throws Exception {
        System.out.println("Entered Fulfilment Controller");
        AssignDeliveryResponse response = service.createDelivery(assignDeliveryRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
