package com.linkedinlearning.reactiveapp.controller;

import com.linkedinlearning.reactiveapp.model.Reservation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static com.linkedinlearning.reactiveapp.controller.ReservationResource.ROOM_V1_RESERVATION;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReservationResourceTest {

    @Autowired
    ApplicationContext context;
    private WebTestClient webTestClient;

    @Before
    public void setUp() throws Exception {
        webTestClient = WebTestClient
                .bindToApplicationContext(this.context)
                .build();
    }

    @Test
    public void getAllReservations() {
        webTestClient.get()
                .uri(ROOM_V1_RESERVATION, "123")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Reservation.class);
    }
}