package com.swiggy.fulfilmentservice.repositories;

import com.swiggy.fulfilmentservice.models.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
}
