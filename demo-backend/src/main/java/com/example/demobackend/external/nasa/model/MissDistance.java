package com.example.demobackend.external.nasa.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MissDistance implements Serializable {

    private static final long serialVersionUID = 1L;

    private String astronomical;
    private String lunar;
    private String kilometers;
    private String miles;

    public MissDistance() {
    }

    public String getAstronomical() {
        return astronomical;
    }

    public void setAstronomical(String astronomical) {
        this.astronomical = astronomical;
    }

    public String getLunar() {
        return lunar;
    }

    public void setLunar(String lunar) {
        this.lunar = lunar;
    }

    public String getKilometers() {
        return kilometers;
    }

    public void setKilometers(String kilometers) {
        this.kilometers = kilometers;
    }

    public String getMiles() {
        return miles;
    }

    public void setMiles(String miles) {
        this.miles = miles;
    }
}
