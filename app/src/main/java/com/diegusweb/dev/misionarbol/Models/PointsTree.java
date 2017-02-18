package com.diegusweb.dev.misionarbol.models;

import java.lang.reflect.Array;

/**
 * Created by HP on 18/02/2017.
 */

public class PointsTree {
    private int id;
    private String name;
    //private InfoUser user;
    private String title;
    private String description;
    private String path;
    private String status;
    private String lat;
    private String lng;
    private String country;
    private String city;

    public PointsTree(String city, String country, String description, int id, String lat, String lng, String name, String path, String status, String title) {
        this.city = city;
        this.country = country;
        this.description = description;
        this.id = id;
        this.lat = lat;
        this.lng = lng;
        this.name = name;
        this.path = path;
        this.status = status;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
