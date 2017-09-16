package com.diegusweb.dev.arbolurbano.models;

/**
 * Created by HP on 18/01/2017.
 */

public class TestItems {

    private int id;
    private String title;
    private String description;

    public TestItems(String description, int id, String title) {
        this.description = description;
        this.id = id;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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


}
