package com.swiggy.fulfilmentservice.services;

import com.swiggy.fulfilmentservice.models.Delivery;
import com.swiggy.fulfilmentservice.models.DeliveryPersonnel;
import com.swiggy.fulfilmentservice.models.Location;
import com.swiggy.fulfilmentservice.models.Status;
import com.swiggy.fulfilmentservice.repositories.DeliveryRepository;
import com.swiggy.fulfilmentservice.requests.AssignDeliveryRequest;
import com.swiggy.fulfilmentservice.responses.AssignDeliveryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FulfillmentService {

    @Autowired
    DeliveryPersonnelService deliveryPersonnelService;
    @Autowired
    DeliveryRepository deliveryRepo;

    public AssignDeliveryResponse createDelivery(AssignDeliveryRequest request) throws Exception {
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

        Delivery delivery = deliveryRepo.save(newDelivery);

        return new AssignDeliveryResponse(delivery.getId(), delivery.getStatus());
    }
}
