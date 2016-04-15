package com.mycompany.sza.entities;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;


@Entity
@Embeddable
public class Address implements Serializable {
  
    @Id
    private Long id;
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
    }

    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Address{" + "id=" + id + ", city=" + city + ", state=" + state + ", country=" + country + ", street=" + street + '}';
    } 
}
