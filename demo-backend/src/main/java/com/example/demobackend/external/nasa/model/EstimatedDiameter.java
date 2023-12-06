package com.example.demobackend.external.nasa.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;


@JsonIgnoreProperties(ignoreUnknown = true)
public class EstimatedDiameter implements Serializable {

    private static final long serialVersionUID = 1L;

    private Measurement kilometers;
    private Measurement meters;
    private Measurement miles;
    private Measurement feet;


    public EstimatedDiameter() {
    }


    public Measurement getKilometers() {
        return kilometers;
    }

    public void setKilometers(Measurement kilometers) {
        this.kilometers = kilometers;
    }

    public Measurement getMeters() {
        return meters;
    }

    public void setMeters(Measurement meters) {
        this.meters = meters;
    }

    public Measurement getMiles() {
        return miles;
    }

    public void setMiles(Measurement miles) {
        this.miles = miles;
    }

    public Measurement getFeet() {
        return feet;
    }

    public void setFeet(Measurement feet) {
        this.feet = feet;
    }
}
