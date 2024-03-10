package com.swiggy.fulfilmentservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "deliveries")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long orderId;
    private float totalPrice;
    @Embedded
    @AttributeOverrides(
            {
                    @AttributeOverride(name = "latitude", column = @Column(name = "pickLocationLatitude")),
                    @AttributeOverride(name = "longitude", column = @Column(name = "pickLocationLongitude"))
            }
    )
    private Location pickupLocation;
    @Embedded
    @AttributeOverrides(
            {
                    @AttributeOverride(name = "latitude", column = @Column(name = "dropLocationLatitude")),
                    @AttributeOverride(name = "longitude", column = @Column(name = "dropLocationLongitude"))
            }
    )
    private Location dropLocation;
    private long deliveryPersonnelId;
    @Enumerated(EnumType.STRING)
    private Status status;

    public Delivery(long orderId, float totalPrice, Location pickupLocation, Location dropLocation, long deliveryPersonnelId, Status status) {
        this.orderId = orderId;
        this.totalPrice = totalPrice;
        this.pickupLocation = pickupLocation;
        this.dropLocation = dropLocation;
        this.deliveryPersonnelId = deliveryPersonnelId;
        this.status = status;
    }
}
