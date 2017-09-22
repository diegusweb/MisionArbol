package com.diegusweb.dev.arbolurbano.models;

import java.util.List;

/**
 * Created by HP on 05/01/2017.
 */

public class Tree {

    private int id;
    private String title;
    private String otherTitle;
    private String scientificName;
    private String description;
    private String location;
    private String flowers;
    private String path;

    private List<Image> slug;

    public Tree(){}

    public String getOtherTitle() {
        return otherTitle;
    }

    public void setOtherTitle(String otherTitle) {
        this.otherTitle = otherTitle;
    }

    public List<Image> getListImage() {
        return slug;
    }

    public void setListImage(List<Image> listImage) {
        this.slug = listImage;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFlowers() {
        return flowers;
    }

    public void setFlowers(String flowers) {
        this.flowers = flowers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
