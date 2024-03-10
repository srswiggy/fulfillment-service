package com.swiggy.fulfilmentservice.models;

import jakarta.persistence.*;

@Embeddable
public class Location {
    private float latitude;
    private float longitude;
}
