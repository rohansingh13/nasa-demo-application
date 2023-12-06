package com.example.demobackend.external.nasa.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


@JsonIgnoreProperties(ignoreUnknown = true)
public class NasaNeoFeedResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Map<String, List<NasaAsteroidInfo>> near_earth_objects;
    private Map<String, String> links;
    private int elementCount;

    public NasaNeoFeedResponse() {
    }

    public Map<String, String> getLinks() {
        return links;
    }

    public void setLinks(Map<String, String> links) {
        this.links = links;
    }

    public int getElementCount() {
        return elementCount;
    }

    public void setElementCount(int elementCount) {
        this.elementCount = elementCount;
    }

    public Map<String, List<NasaAsteroidInfo>> getNear_earth_objects() {
        return near_earth_objects;
    }

    public void setNear_earth_objects(Map<String, List<NasaAsteroidInfo>> near_earth_objects) {
        this.near_earth_objects = near_earth_objects;
    }
}


