package com.swiggy.fulfilmentservice.services;

import com.swiggy.fulfilmentservice.models.DeliveryPersonnel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeliveryPersonnelService {

    public List<DeliveryPersonnel> getAll() {
        List<DeliveryPersonnel> list = new ArrayList<>();
        return list;
    }
}
