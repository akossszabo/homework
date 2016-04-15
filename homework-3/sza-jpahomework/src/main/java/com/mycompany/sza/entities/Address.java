package com.mycompany.sza.entities;

import java.io.Serializable;
import javax.persistence.Embeddable;



@Embeddable
public class Address implements Serializable {
    private String city;
     private String state;
     private String country;
    private String street;

    public Address(String city, String state, String country, String street) {
        this.city = city;
        this.state = state;
        this.country = country;
        this.street = street;
    }

    public Address() {
        //empty constructor
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

}
