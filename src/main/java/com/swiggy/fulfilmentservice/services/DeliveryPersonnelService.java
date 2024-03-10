package com.swiggy.fulfilmentservice.services;

import com.swiggy.fulfilmentservice.models.DeliveryPersonnel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class DeliveryPersonnelService {
    @Autowired
    RestTemplate restTemplate;

    public List<DeliveryPersonnel> getAll() {
        System.out.println("entereted deliverypersonnel service");

        String url = "http://localhost:8080/user-management/users";
        DeliveryPersonnel[] deliveryPersonnels = restTemplate.getForObject(url, DeliveryPersonnel[].class);
        System.out.println("called user service");
        System.out.println(deliveryPersonnels.length);

        if(deliveryPersonnels != null) {
            List<DeliveryPersonnel> deliveryPersonnelList = Arrays.asList(deliveryPersonnels);
            List<DeliveryPersonnel> deliveryPersonnelListWithRoleDelivery = new ArrayList<>();
            deliveryPersonnelList.forEach((item) -> {
                if(Objects.equals(item.getRole(), "DELIVERY_PERSONNEL")) deliveryPersonnelListWithRoleDelivery.add(item);
            });

            System.out.println("Delivery Partners = " + deliveryPersonnelListWithRoleDelivery);
            return deliveryPersonnelListWithRoleDelivery;
        }
        else return List.of();
    }
}
