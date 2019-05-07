package com.linkedinlearning.reactiveapp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.linkedinlearning.reactiveapp.config.ApiConfig;
import com.linkedinlearning.reactiveapp.config.DataConfig;
import com.linkedinlearning.reactiveapp.model.Reservation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.IOException;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {ApiConfig.class, DataConfig.class})
@ComponentScan
@EnableAutoConfiguration
public class ReservationServiceImplTest {

    @Autowired
    private ReservationService classUnderTest;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getReservation() {

        final Mono<Reservation> actualResponse = classUnderTest.getReservation("");

        StepVerifier.create(actualResponse).consumeNextWith(reservation -> {

            // Assert that response was deserialized correctly
            Assert.assertNotNull(reservation);
            Assert.assertNotNull(reservation.getPrice());
            Assert.assertNotNull(reservation.getRoomNumber());
        })
                .expectComplete()
                .verify();
    }
}