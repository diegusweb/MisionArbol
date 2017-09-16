package com.diegusweb.dev.arbolurbano.models;

/**
 * Created by HP on 05/01/2017.
 */

public class Tree {

    private int id;
    private String title;
    private String name_scientist;
    private String description;
    private String description_less;
    private String path;

    public Tree() {
    }

    public Tree(int id, String path, int image,String description, String name_scientist, String description_less) {
        this.description = description;
        this.id = id;
        this.title = title;
        this.path = path;
        this.name_scientist = name_scientist;
        this.description_less = description_less;
    }

    public String getDescription_less() {
        return description_less;
    }

    public void setDescription_less(String description_less) {
        this.description_less = description_less;
    }

    public String getName_scientist() {
        return name_scientist;
    }

    public void setName_scientist(String name_scientist) {
        this.name_scientist = name_scientist;
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
