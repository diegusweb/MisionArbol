package com.diegusweb.dev.misionarbol.models;

/**
 * Created by HP on 05/01/2017.
 */

public class Tree {

    private int id;
    private String name;
    private String description;
    private int idDrawable;

    public Tree(String description, int id, int idDrawable, String name) {
        this.description = description;
        this.id = id;
        this.idDrawable = idDrawable;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getIdDrawable() {
        return idDrawable;
    }

    public void setIdDrawable(int idDrawable) {
        this.idDrawable = idDrawable;
    }


}
