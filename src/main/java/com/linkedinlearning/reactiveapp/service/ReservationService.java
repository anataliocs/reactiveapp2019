package com.linkedinlearning.reactiveapp.service;

import com.linkedinlearning.reactiveapp.model.Reservation;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReservationService {

    Mono<Reservation> getReservation(String priceName);

    Mono<Reservation> createReservation(Reservation reservation);

    Mono<Reservation> deleteReservation(String id);

    Flux<Reservation> listAllReservations();
}