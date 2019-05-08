package com.linkedinlearning.reactiveapp.controller;

import com.linkedinlearning.reactiveapp.model.Reservation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

import static com.linkedinlearning.reactiveapp.controller.ReservationResource.ROOM_V1_RESERVATION;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReservationResourceTest {

    @Autowired
    ApplicationContext context;
    private WebTestClient webTestClient;
    private Reservation expectedReservation;

    @Before
    public void setUp() throws Exception {
        webTestClient = WebTestClient
                .bindToApplicationContext(this.context)
                .build();

        expectedReservation =
                new Reservation(123l, LocalDate.now(),
                        LocalDate.now().plus(10, ChronoUnit.DAYS), 150);
    }

    @Test
    public void getAllReservations() {
        webTestClient.get()
                .uri(ROOM_V1_RESERVATION)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Reservation.class);
    }

    @Test
    public void createReservation() {
        webTestClient.post()
                .uri(ROOM_V1_RESERVATION)
                .body(Mono.just(expectedReservation), Reservation.class)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody(Reservation.class);
    }
}