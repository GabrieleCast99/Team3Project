package it.develhope.shoppyz.entity;

import jakarta.persistence.*;

@Embeddable
public class Address {

    private int id;

    private String street;

    private String city;

    private String state;

    private String postalCode;

    public Address(){}

    public Address(Integer id, String street, String city, String state, String postalCode) {
        this.id = id;
        this.street = street;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
