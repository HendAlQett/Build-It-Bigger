package com.example;

import java.util.ArrayList;
import java.util.Random;

public class JokeSupplier {

    ArrayList<String> jokesList;
    public JokeSupplier() {
        jokesList = new ArrayList<>();
        jokesList.add("Just changed my Facebook name to ‘No one' so when I see stupid posts I can click like and it will say ‘No one likes this'.");
        jokesList.add("How do you make holy water? You boil the hell out of it.");
        jokesList.add("What do you call a bear with no teeth? -- A gummy bear!");
        jokesList.add("I used to like my neighbors, until they put a password on their Wi-Fi.");
        jokesList.add("What did the ocean say to the beach? Nothing, it just waved.");
    }

    public String getJoke() {

        int jokeId= randInt(jokesList.size());
        return  jokesList.get(jokeId);
    }

    int randInt(int size) {
        Random rand = new Random();

        //min =0
        //max =size -1 --> size = max +1

        int randomNum = rand.nextInt(size);
        return randomNum;
    }
}