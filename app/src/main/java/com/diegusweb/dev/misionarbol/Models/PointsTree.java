package com.diegusweb.dev.misionarbol.models;

import java.lang.reflect.Array;

/**
 * Created by HP on 18/02/2017.
 */

public class PointsTree {

    private int id;
    private String name;
    private InfoUser user_id;
    private Type type_id;
    private String title;
    private String description;
    private String path;
    private int status;
    private double lat;
    private double lng;
    private String country;
    private String city;

    public PointsTree(String city, String country, String description, int id, double lat, double lng, String name, String path, int status, String title, Type type_id, InfoUser user_id) {
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
        this.type_id = type_id;
        this.user_id = user_id;
    }

    public InfoUser getUser_id() {
        return user_id;
    }

    public void setUser_id(InfoUser user_id) {
        this.user_id = user_id;
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

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Type getType_id() {
        return type_id;
    }

    public void setType_id(Type type_id) {
        this.type_id = type_id;
    }








}
