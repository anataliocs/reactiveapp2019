package com.linkedinlearning.reactiveapp.service;

import com.fasterxml.jackson.databind.ObjectWriter;
import com.linkedinlearning.reactiveapp.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ReservationServiceImpl implements ReservationService {

    private final ObjectWriter objectWriter;

    private final WebClient webClient;

    private final ReactiveMongoOperations reactiveMongoTemplate;

    @Autowired
    public ReservationServiceImpl(ObjectWriter objectWriter,
                                  WebClient webClient,
                                  ReactiveMongoOperations reactiveMongoTemplate) {
        this.objectWriter = objectWriter;
        this.webClient = webClient;
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }

    @Override
    public Mono<Reservation> getReservation(String id) {
        return reactiveMongoTemplate
                .findById(id, Reservation.class);
    }

    @Override
    public Mono<Reservation> createReservation(Reservation reservation) {
        return reactiveMongoTemplate
                .save(reservation);
    }

    @Override
    public Mono<Reservation> deleteReservation(String id) {
        return Mono.empty();
    }

    @Override
    public Flux<Reservation> listAllReservations() {
        return reactiveMongoTemplate.findAll(Reservation.class);
    }
}


