package com.swiggy.fulfilmentservice.controllers;

import com.swiggy.fulfilmentservice.models.Location;
import com.swiggy.fulfilmentservice.models.Status;
import com.swiggy.fulfilmentservice.requests.AssignDeliveryRequest;
import com.swiggy.fulfilmentservice.responses.AssignDeliveryResponse;
import com.swiggy.fulfilmentservice.services.FulfillmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class FulfillmentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FulfillmentService service;

    @Test
    public void assignDeliveryPartnerSuccess() throws Exception {
        AssignDeliveryResponse expectedResponse = new AssignDeliveryResponse(1, Status.ASSIGNED);

        given(service.createDelivery(any(AssignDeliveryRequest.class))).willReturn(expectedResponse);

        String jsonRequest = """
            {
                "orderId": 1,
                "totalPrice": 2320.343,
                "pickupLocation": {
                    "latitude": -23.53343,
                    "longitude": 123.342
                },
                "dropLocation": {
                    "latitude": 12,
                    "longitude": 232.442
                }
            }
            """;

        mockMvc.perform(post("/fullfilment-management/deliveries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.status").value("ASSIGNED"));
    }

    @Test
    public void assignDeliveryPartnerFailureWithBadRequest() throws Exception {
        AssignDeliveryRequest request = new AssignDeliveryRequest(1, 200, new Location(9, 9), new Location(20, 20));

        given(service.createDelivery(any(AssignDeliveryRequest.class))).willThrow(new Exception("Something went wrong."));

        mockMvc.perform(post("/fullfilment-management/deliveries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"orderId\":\"order2\",\"totalPrice\":200,\"pickupLocation\":{\"latitude\":9,\"longitude\":9},\"dropLocation\":{\"latitude\":20,\"longitude\":20}}"))
                .andExpect(status().isBadRequest());
    }

}