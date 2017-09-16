package com.diegusweb.dev.arbolurbano.models;

/**
 * Created by HP on 02/02/2017.
 */

public class GithubUser {
    public String login;
    public String name;
    public String email;
    public String following;

    @Override
    public String toString() {
        return(login);
    }
}
