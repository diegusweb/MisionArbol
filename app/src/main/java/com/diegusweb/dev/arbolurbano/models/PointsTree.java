package com.diegusweb.dev.arbolurbano.models;

/**
 * Created by HP on 18/02/2017.
 */

public class PointsTree {

    private int id;
    private String email;
    private String name_user;
    private String caption;
    private String file;
    private String thumb;
    private String scientificName;
    private String commonName;
    private String total_height;
    private String stem_diameter;
    private String development_stage;
    private String status_tree;
    private int status;
    private double latitude;
    private double longitude;


    public PointsTree(int id, String email, String name_user, String caption, String file, String thumb, String scientificName, String commonName, String total_height, String stem_diameter, String development_stage, String status_tree, int status, double latitude, double longitude) {
        this.id = id;
        this.email = email;
        this.name_user = name_user;
        this.caption = caption;
        this.file = file;
        this.thumb = thumb;
        this.scientificName = scientificName;
        this.commonName = commonName;
        this.total_height = total_height;
        this.stem_diameter = stem_diameter;
        this.development_stage = development_stage;
        this.status_tree = status_tree;
        this.status = status;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName_user() {
        return name_user;
    }

    public void setName_user(String name_user) {
        this.name_user = name_user;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getTotal_height() {
        return total_height;
    }

    public void setTotal_height(String total_height) {
        this.total_height = total_height;
    }

    public String getStem_diameter() {
        return stem_diameter;
    }

    public void setStem_diameter(String stem_diameter) {
        this.stem_diameter = stem_diameter;
    }

    public String getDevelopment_stage() {
        return development_stage;
    }

    public void setDevelopment_stage(String development_stage) {
        this.development_stage = development_stage;
    }

    public String getStatus_tree() {
        return status_tree;
    }

    public void setStatus_tree(String status_tree) {
        this.status_tree = status_tree;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getLat() {
        return latitude;
    }

    public void setLat(double lat) {
        this.latitude = lat;
    }

    public double getLng() {
        return longitude;
    }

    public void setLng(double lng) {
        this.longitude = lng;
    }


}
