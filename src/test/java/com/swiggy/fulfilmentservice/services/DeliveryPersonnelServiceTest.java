package com.swiggy.fulfilmentservice.services;

import com.swiggy.fulfilmentservice.models.DeliveryPersonnel;
import com.swiggy.fulfilmentservice.models.Location;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class DeliveryPersonnelServiceTest {
    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private DeliveryPersonnelService deliveryPersonnelService;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        reset(restTemplate);
    }

    @Test
    public void testGetAllReturnsDeliveryPersonnel() {
        DeliveryPersonnel[] personnelArray = {
                new DeliveryPersonnel(1, "dp1", new Location(), "DELIVERY_PERSONNEL"),
                new DeliveryPersonnel(2, "dp2", new Location(), "DELIVERY_PERSONNEL")
        };

        when(restTemplate.getForObject("http://localhost:8080/user-management/users", DeliveryPersonnel[].class))
                .thenReturn(personnelArray);

        List<DeliveryPersonnel> result = deliveryPersonnelService.getAll();

        assertEquals(2, result.size());
        assertEquals("dp1", result.get(0).getUsername());
        assertEquals("DELIVERY_PERSONNEL", result.get(0).getRole());
    }

    @Test
    public void testGetAllReturnsOnlyDeliveryPersonnel() {
        DeliveryPersonnel[] personnelArray = {
                new DeliveryPersonnel(1, "dp1", new Location(), "DELIVERY_PERSONNEL"),
                new DeliveryPersonnel(2, "admin", new Location(), "ADMIN"),
                new DeliveryPersonnel(2, "user", new Location(), "CUSTOMER")
        };

        when(restTemplate.getForObject("http://localhost:8080/user-management/users", DeliveryPersonnel[].class))
                .thenReturn(personnelArray);

        List<DeliveryPersonnel> result = deliveryPersonnelService.getAll();

        assertEquals(1,  result.size());
        assertEquals("dp1", result.get(0).getUsername());
        assertEquals("DELIVERY_PERSONNEL", result.get(0).getRole());
    }

    @Test
    public void testGetAllWithEmptyArray() {
        when(restTemplate.getForObject("http://localhost:8080/user-management/users", DeliveryPersonnel[].class))
                .thenReturn(new DeliveryPersonnel[]{});

        List<DeliveryPersonnel> result = deliveryPersonnelService.getAll();

        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetAllWithNullResponse() {
        when(restTemplate.getForObject("http://localhost:8080/user-management/users", DeliveryPersonnel[].class))
                .thenReturn(null);

        List<DeliveryPersonnel> result = deliveryPersonnelService.getAll();

        assertTrue(result.isEmpty());
    }

}