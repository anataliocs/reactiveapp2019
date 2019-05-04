package com.linkedinlearning.reactiveapp.controller;

import com.linkedinlearning.reactiveapp.model.Reservation;
import com.linkedinlearning.reactiveapp.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

import static com.linkedinlearning.reactiveapp.controller.ReservationResource.ROOM_V1_RESERVATION;


@RestController
@RequestMapping(ROOM_V1_RESERVATION)
@CrossOrigin
public class ReservationResource {

    public static final String ROOM_V1_RESERVATION = "/room/v1/reservation/";

    private final ReservationService reservationService;

    @Autowired
    public ReservationResource(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

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
    public Mono<Reservation> getRoomById(
            @PathVariable
                    String roomId) {

        return reservationService.getReservation(roomId);
    }

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Mono<Reservation> createReservation(
            @RequestBody
                    Mono<Reservation> reservationRequest) {

        return reservationService.createReservation(reservationRequest);
    }

    @PutMapping(path = "/{roomId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Mono<Reservation> updateReservation(@PathVariable String roomId,
                                               @RequestBody
                                                       Mono<Reservation> reservation) {

        return reservationService.updateReservation(roomId, reservation);
    }

    @DeleteMapping(path = "/{reservationId}")
    public Mono<Boolean> deleteReservation(
            @PathVariable
                    String reservationId) {

        return reservationService.deleteReservation(reservationId);
    }
}