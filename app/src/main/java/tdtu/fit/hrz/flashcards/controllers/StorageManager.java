package tdtu.fit.hrz.flashcards.controllers;

import android.annotation.SuppressLint;
import android.content.Context;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import tdtu.fit.hrz.flashcards.R;
import tdtu.fit.hrz.flashcards.objects.Card;
import tdtu.fit.hrz.flashcards.objects.Deck;

public class StorageManager {

    private static StorageManager instance;
    private static ArrayList<Deck> decks = new ArrayList<>();

    private StorageManager(){
        decks = loadDecks();
    }
    public static StorageManager getInstance() {
        if(instance == null) {
            instance = new StorageManager();
        }
        return instance;
    }

    public ArrayList<Deck> getDecks() {
        return decks;
    }

    public void setDecks(ArrayList<Deck> decks) {
        StorageManager.decks = decks;
    }


    private ArrayList<Deck> loadDecks(){
        ArrayList<Deck> decks = new ArrayList<>();
        Deck fcBird = new Deck(loadFlashcard());
        Deck fcCat = new Deck(loadFlashcard());
        Deck fcDog = new Deck(loadFlashcard());

        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

//        fcBird.setCoverImage(R.drawable.birb);
        fcBird.setDeckName("fucking birb");
        fcBird.setCreator("not me");

//        fcCat.setCoverImage(getResources().getDrawable(R.drawable.huhcat, null));
        fcCat.setDeckName("fucking cat");
        fcCat.setCreator("me");

//        fcDog.setCoverImage(getResources().getDrawable(android.R.drawable.sym_def_app_icon, null));
        fcDog.setDeckName("not fucking dog");
        fcDog.setCreator("you");

        try {
            Date specificDate = dateFormat.parse("2021/12/20");

            fcBird.setLastModifiedDate(specificDate);
            fcCat.setLastModifiedDate(specificDate);
            fcDog.setLastModifiedDate(specificDate);
        } catch (ParseException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }

        decks.add(fcBird);
        decks.add(fcCat);
        decks.add(fcDog);


        return decks;
    }
    private static ArrayList<Card> loadFlashcard(){
        ArrayList<Card> f = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < random.nextInt(100); i++) {
            f.add(new Card(
                    "question " + (i+1),
                    "answer" + (i+1))
            );
        }
        return f;
    }
}
