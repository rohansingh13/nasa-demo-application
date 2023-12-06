package com.example.demobackend.model;

public class Asteroid {

    private Long id;
    private String name;
    private boolean isPotentiallyHazardousAsteroid;
    private String nasaJplUrl;
    private Double absoluteMagnitude;
    private Double estimatedDiameterMin;
    private Double estimatedDiameterMax;
    private String closeApproachDate;
    private String relativeVelocityKmPerSec;
    private String relativeVelocityKmPerHour;
    private String relativeVelocityMilesPerHour;
    private String missDistanceAstronomical;
    private String missDistanceLunar;
    private String missDistanceKilometers;
    private String missDistanceMiles;


    public Asteroid() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPotentiallyHazardousAsteroid() {
        return isPotentiallyHazardousAsteroid;
    }

    public void setPotentiallyHazardousAsteroid(boolean potentiallyHazardousAsteroid) {
        isPotentiallyHazardousAsteroid = potentiallyHazardousAsteroid;
    }

    public String getNasaJplUrl() {
        return nasaJplUrl;
    }

    public void setNasaJplUrl(String nasaJplUrl) {
        this.nasaJplUrl = nasaJplUrl;
    }

    public Double getAbsoluteMagnitude() {
        return absoluteMagnitude;
    }

    public void setAbsoluteMagnitude(Double absoluteMagnitude) {
        this.absoluteMagnitude = absoluteMagnitude;
    }

    public Double getEstimatedDiameterMin() {
        return estimatedDiameterMin;
    }

    public void setEstimatedDiameterMin(Double estimatedDiameterMin) {
        this.estimatedDiameterMin = estimatedDiameterMin;
    }

    public Double getEstimatedDiameterMax() {
        return estimatedDiameterMax;
    }

    public void setEstimatedDiameterMax(Double estimatedDiameterMax) {
        this.estimatedDiameterMax = estimatedDiameterMax;
    }

    public String getCloseApproachDate() {
        return closeApproachDate;
    }

    public void setCloseApproachDate(String closeApproachDate) {
        this.closeApproachDate = closeApproachDate;
    }

    public String getRelativeVelocityKmPerSec() {
        return relativeVelocityKmPerSec;
    }

    public void setRelativeVelocityKmPerSec(String relativeVelocityKmPerSec) {
        this.relativeVelocityKmPerSec = relativeVelocityKmPerSec;
    }

    public String getRelativeVelocityKmPerHour() {
        return relativeVelocityKmPerHour;
    }

    public void setRelativeVelocityKmPerHour(String relativeVelocityKmPerHour) {
        this.relativeVelocityKmPerHour = relativeVelocityKmPerHour;
    }

    public String getRelativeVelocityMilesPerHour() {
        return relativeVelocityMilesPerHour;
    }

    public void setRelativeVelocityMilesPerHour(String relativeVelocityMilesPerHour) {
        this.relativeVelocityMilesPerHour = relativeVelocityMilesPerHour;
    }

    public String getMissDistanceAstronomical() {
        return missDistanceAstronomical;
    }

    public void setMissDistanceAstronomical(String missDistanceAstronomical) {
        this.missDistanceAstronomical = missDistanceAstronomical;
    }

    public String getMissDistanceLunar() {
        return missDistanceLunar;
    }

    public void setMissDistanceLunar(String missDistanceLunar) {
        this.missDistanceLunar = missDistanceLunar;
    }

    public String getMissDistanceKilometers() {
        return missDistanceKilometers;
    }

    public void setMissDistanceKilometers(String missDistanceKilometers) {
        this.missDistanceKilometers = missDistanceKilometers;
    }

    public String getMissDistanceMiles() {
        return missDistanceMiles;
    }

    public void setMissDistanceMiles(String missDistanceMiles) {
        this.missDistanceMiles = missDistanceMiles;
    }
}
