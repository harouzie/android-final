package tdtu.fit.hrz.flashcards.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

import tdtu.fit.hrz.flashcards.objects.Deck;

public class DeckSerializer {

    private final Gson gson;

    public DeckSerializer() {
        gson = new Gson();
    }

    public String serializeDeck(Deck deck) {
        return gson.toJson(deck);
    }

    public Deck deserializeDeck(String json) {
        Type deckType = new TypeToken<Deck>(){}.getType();
        return gson.fromJson(json, deckType);
    }
}