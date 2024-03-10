package com.swiggy.fulfilmentservice.services;

import com.swiggy.fulfilmentservice.models.Delivery;
import com.swiggy.fulfilmentservice.models.DeliveryPersonnel;
import com.swiggy.fulfilmentservice.models.Location;
import com.swiggy.fulfilmentservice.models.Status;
import com.swiggy.fulfilmentservice.repositories.DeliveryRepository;
import com.swiggy.fulfilmentservice.requests.AssignDeliveryRequest;
import com.swiggy.fulfilmentservice.responses.AssignDeliveryResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class FulfillmentServiceTest {
    @Mock
    private DeliveryPersonnelService deliveryPersonnelService;

    @Mock
    private DeliveryRepository deliveryRepo;

    @InjectMocks
    private FulfillmentService fulfillmentService;

    @BeforeEach
    public void setUp() {
        openMocks(this);
    }

    @Test
    public void testCreateDeliverySuccess() throws Exception {
        List<DeliveryPersonnel> personnelList = new ArrayList<>();
        Location personnelLocation = new Location(10, 10);
        personnelList.add(new DeliveryPersonnel(1, "dp", personnelLocation, "DELIVERY_PERSONNEL"));

        AssignDeliveryRequest request = new AssignDeliveryRequest(1, 100, new Location(9, 9), new Location(20, 20));

        when(deliveryPersonnelService.getAll()).thenReturn(personnelList);
        when(deliveryRepo.save(any(Delivery.class))).thenAnswer(i -> i.getArguments()[0]);

        AssignDeliveryResponse response = fulfillmentService.createDelivery(request);

        assertNotNull(response);
        assertEquals(0, response.getId());
        assertEquals(Status.ASSIGNED, response.getStatus());
    }
    @Test
    public void testCreateDeliveryNoMatchingPersonnelThrowsException() throws Exception {
        when(deliveryPersonnelService.getAll()).thenReturn(new ArrayList<>());

        AssignDeliveryRequest request = new AssignDeliveryRequest(1, 200, new Location(9, 9), new Location(20, 20));

        assertThrows(Exception.class, ()->fulfillmentService.createDelivery(request));
    }


    @Test
    public void testCreateDeliveryPersonnelServiceReturnsNull() throws Exception {
        when(deliveryPersonnelService.getAll()).thenReturn(null);

        AssignDeliveryRequest request = new AssignDeliveryRequest(1, 300, new Location(9, 9), new Location(20, 20));

        assertThrows(Exception.class, ()->fulfillmentService.createDelivery(request));
    }

}