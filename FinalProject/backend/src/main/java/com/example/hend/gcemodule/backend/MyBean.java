package com.example.hend.gcemodule.backend;

import com.example.JokeSupplier;

/** The object model for the data we are sending through endpoints */
public class MyBean {

    private String myJoke;

    public String getJoke() {
        return myJoke;
    }

    public void setJoke() {

        JokeSupplier joke = new JokeSupplier();
        myJoke = joke.getJoke();
    }
}