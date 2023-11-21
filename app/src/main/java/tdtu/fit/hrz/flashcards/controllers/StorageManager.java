package tdtu.fit.hrz.flashcards.controllers;

import static tdtu.fit.hrz.flashcards.R.drawable.birb;
import static tdtu.fit.hrz.flashcards.R.drawable.circle_border;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;

import com.google.gson.reflect.TypeToken;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import tdtu.fit.hrz.flashcards.R;
import tdtu.fit.hrz.flashcards.objects.Card;
import tdtu.fit.hrz.flashcards.objects.Deck;

public class StorageManager {

    private static StorageManager instance;
    private static ArrayList<Deck> decks = new ArrayList<>();
    private static List<String> jsonFiles = new ArrayList<>();
    private static List<String> deckJsons = new ArrayList<>();

    private final String path = "app/sampledata/data.json";
    private final String url = "https://raw.githubusercontent.com/harouzie/android-final/master/app/sampledata/data.json";
    private static DeckSerializer deckSerializer;
    public static Context context;

    private StorageManager(){
        decks = loadDecks();
        deckSerializer = new DeckSerializer();
        writeToJson(decks);
    }

    public static StorageManager getInstance() {
        if(instance == null) {
            instance = new StorageManager();
        }
        return instance;
    }

    private String normalizeName(String name){
        name = name.toLowerCase();
        name = name.replaceAll("[^a-zA-Z0-9\\s\\-_]", "");
        name = name.replaceAll("\\s+", "_");
        return name;
    }
    private void writeToJson(List<Deck> decks){

        for(Deck deck: decks){
            jsonFiles.add(normalizeName(deck.getDeckName()) + ".json");
            deckJsons.add(deckSerializer.serializeDeck(deck));
        }
        int i = 0;
        for (String fileName: jsonFiles){

            try {
                FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
                fos.write(deckJsons.get(i).getBytes());
                fos.close();
                i++;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void writeDeck(Deck deck){
        try {
            FileOutputStream fos = new FileOutputStream(path);
            fos.write(deckSerializer.serializeDeck(deck).getBytes());
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private ArrayList<Deck> loadFromJson(){

        ArrayList<Deck> loadedDecks = new ArrayList<>();
        for (String fileName: jsonFiles) {
            try {
                FileInputStream fis = context.openFileInput(fileName);
                Scanner sc = new Scanner(fis);
                String data = sc.useDelimiter("\\A").next();

                loadedDecks.add(deckSerializer.deserializeDeck(data));
                fis.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return loadedDecks;
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
