package com.swiggy.fulfilmentservice.services;

import com.swiggy.fulfilmentservice.models.Delivery;
import com.swiggy.fulfilmentservice.models.DeliveryPersonnel;
import com.swiggy.fulfilmentservice.models.Location;
import com.swiggy.fulfilmentservice.models.Status;
import com.swiggy.fulfilmentservice.requests.AssignDeliveryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FulfillmentService {

    @Autowired
    DeliveryPersonnelService deliveryPersonnelService;
    public void createDelivery(AssignDeliveryRequest request) throws Exception {
        List<DeliveryPersonnel> deliveryPersonnelList = deliveryPersonnelService.getAll();
        List<Location> locationList = new ArrayList<>();
        deliveryPersonnelList.forEach((deliveryPersonnel -> locationList.add(deliveryPersonnel.getLocation())));

        Location closestLocation = request.getPickupLocation().findClosestLocation(locationList);
        Optional<DeliveryPersonnel> matchingPersonnel = deliveryPersonnelList.stream()
                .filter(personnel -> personnel.sameLocation(closestLocation))
                .findFirst();

        DeliveryPersonnel personnel = null;
        if (matchingPersonnel.isPresent()) {
            personnel = matchingPersonnel.get();
        }

        if(personnel == null) throw new Exception("Something went wrong.");

        Delivery newDelivery = new Delivery(
                request.getOrderId(),
                request.getTotalPrice(),
                request.getPickupLocation(),
                request.getDropLocation(),
                personnel.getId(),
                Status.ASSIGNED
        );
    }
}
