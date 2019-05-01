package com.linkedinlearning.reactiveapp.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReservationService {

    Mono<String> getReservation(String priceName);

    Mono<String> createReservation(String priceName);

    Mono<String> deleteReservation(String id);

    Flux<String> listAllReservations();
}