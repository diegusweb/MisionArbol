package com.diegusweb.dev.arbolurbano.models;

/**
 * Created by DIego on 9/16/2017.
 */

public class Image {


    private int id;
    private String file;
    private String thumb;
    private int tree_id;
    private int status;

    public Image(int id, String file, String thumb, int tree_id, int status) {
        this.id = id;
        this.file = file;
        this.thumb = thumb;
        this.tree_id = tree_id;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getTree_id() {
        return tree_id;
    }

    public void setTree_id(int tree_id) {
        this.tree_id = tree_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
