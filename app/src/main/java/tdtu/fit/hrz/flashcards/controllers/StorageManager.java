package tdtu.fit.hrz.flashcards.controllers;

import static tdtu.fit.hrz.flashcards.R.drawable.birb;
import static tdtu.fit.hrz.flashcards.R.drawable.circle_border;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;

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
    public static Context context;
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


    @SuppressLint("UseCompatLoadingForDrawables")
    private ArrayList<Deck> loadDecks(){
        ArrayList<Deck> decks = new ArrayList<>();
        Deck fcBird = new Deck(loadFlashcard());
        Deck fcCat = new Deck(loadFlashcard());
        Deck fcDog = new Deck(loadFlashcard());

        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

        fcBird.setCoverImage(context.getResources().getDrawable(R.drawable.birb, null));
        fcBird.setDeckName("birbs");
        fcBird.setCreator("not me");

        fcCat.setCoverImage(context.getResources().getDrawable(R.drawable.huhcat, null));
        fcCat.setDeckName("huh cat");
        fcCat.setCreator("me");

        fcDog.setCoverImage(context.getResources().getDrawable(android.R.drawable.sym_def_app_icon, null));
        fcDog.setDeckName("My Deck");
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
