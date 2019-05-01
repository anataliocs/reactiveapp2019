package com.linkedinlearning.reactiveapp.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ReservationServiceImpl implements ReservationService {

    @Override
    public Mono<String> getReservation(String priceName) {
        return null;
    }

    @Override
    public Mono<String> createReservation(String priceName) {
        return null;
    }

    @Override
    public Mono<String> deleteReservation(String id) {
        return null;
    }

    @Override
    public Flux<String> listAllReservations() {
        return null;
    }
}


