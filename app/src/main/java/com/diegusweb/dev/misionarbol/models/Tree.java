package com.diegusweb.dev.misionarbol.models;

import com.diegusweb.dev.misionarbol.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 05/01/2017.
 */

public class Tree {

    private int id;
    private String name;
    private String description;
    private int image;

    public Tree() {
    }

    public Tree(int id, String name, int image,String description) {
        this.description = description;
        this.id = id;
        this.image = image;
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

    public int getImage() {
        return image;
    }

    public void setImage(int idDrawable) {
        this.image = image;
    }


}
