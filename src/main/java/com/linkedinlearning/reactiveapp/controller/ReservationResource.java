package com.linkedinlearning.reactiveapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

import static com.linkedinlearning.reactiveapp.controller.ReservationResource.ROOM_RESERVATION_V1;

@RestController
@RequestMapping(ROOM_RESERVATION_V1)
@CrossOrigin
public class ReservationResource {

    public static final String ROOM_RESERVATION_V1 = "/room/reservation/v1";

    @Autowired
    ConversionService conversionService;

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Mono<String> getAvailableRooms(
            @RequestParam(value = "checkin")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                    LocalDate checkin,
            @RequestParam(value = "checkout")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                    LocalDate checkout, Pageable pageable) {


        return Mono.empty();
    }

    @GetMapping(path = "/{roomId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Mono<String> getRoomById(
            @PathVariable
                    Long roomId) {

        return Mono.empty();
    }

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Mono<String> createReservation(
            @RequestBody
                    String reservationRequest) {

        return Mono.empty();
    }

    @PutMapping(path = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Mono<String> updateReservation(
            @RequestBody
                    String reservationRequest) {

        return Mono.empty();
    }

    @DeleteMapping(path = "/{reservationId}")
    public Mono<Void> deleteReservation(
            @PathVariable
                    long reservationId) {

        return Mono.empty();
    }
}