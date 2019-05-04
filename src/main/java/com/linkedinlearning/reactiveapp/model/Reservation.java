package com.linkedinlearning.reactiveapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Document
public class Reservation {

    private final Long roomNumber;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private final LocalDate checkin;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private final LocalDate checkout;
    private final Integer price;

    public Reservation(Long roomNumber, LocalDate checkin, LocalDate checkout, Integer price) {
        this.roomNumber = roomNumber;
        this.checkin = checkin;
        this.checkout = checkout;
        this.price = price;
    }

    @Id
    private String id;

    public Long getRoomNumber() {
        return roomNumber;
    }

    public LocalDate getCheckin() {
        return checkin;
    }

    public LocalDate getCheckout() {
        return checkout;
    }

    public Integer getPrice() {
        return price;
    }

    public String getId() {
        return id;
    }
}