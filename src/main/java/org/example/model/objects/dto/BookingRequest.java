package org.example.model.objects.dto;

import java.time.LocalDate;

public class BookingRequest {

    private LocalDate anreise = null;
    private LocalDate abreise = null;
    private String iban = null;
    private int number;
    private Hotel hotel;


    public LocalDate getAnreise() {
        return anreise;
    }

    public void setAnreise(LocalDate anreise) {
        this.anreise = anreise;
    }

    public LocalDate getAbreise() {
        return abreise;
    }

    public void setAbreise(LocalDate abreise) {
        this.abreise = abreise;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
