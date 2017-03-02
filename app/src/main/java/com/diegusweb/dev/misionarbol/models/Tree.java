package com.diegusweb.dev.misionarbol.models;

import com.diegusweb.dev.misionarbol.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 05/01/2017.
 */

public class Tree {

    private int id;
    private String title;
    private String description;
    private String path;

    public Tree() {
    }

    public Tree(int id, String path, int image,String description) {
        this.description = description;
        this.id = id;
        this.title = title;
        this.path = path;
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
