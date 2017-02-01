package com.diegusweb.dev.misionarbol.models;

/**
 * Created by HP on 31/01/2017.
 */

public class InfoUser {

    private int id;
    private String firt_name;
    private String last_name;
    private String email;
    private String type;
    private boolean active;

    public InfoUser(boolean active, String email, String firt_name, int id, String last_name, String type) {
        this.active = active;
        this.email = email;
        this.firt_name = firt_name;
        this.id = id;
        this.last_name = last_name;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirt_name() {
        return firt_name;
    }

    public void setFirt_name(String firt_name) {
        this.firt_name = firt_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }


}
