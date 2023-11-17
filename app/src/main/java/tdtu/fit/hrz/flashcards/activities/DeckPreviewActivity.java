package tdtu.fit.hrz.flashcards.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import tdtu.fit.hrz.flashcards.R;
import tdtu.fit.hrz.flashcards.controllers.DeckRCVAdapter;
import tdtu.fit.hrz.flashcards.objects.Flashcard;
import tdtu.fit.hrz.flashcards.objects.Deck;

public class DeckPreviewActivity extends AppCompatActivity {
    RecyclerView flashcardCollectionRCV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck_preview);

        flashcardCollectionRCV = findViewById(R.id.flashcardCollectionRCV);
        DeckRCVAdapter mRCVAdapter = new DeckRCVAdapter(
                this, R.layout.rcv_deck, loadFlashcardCollection());
        flashcardCollectionRCV.setAdapter(mRCVAdapter);
        flashcardCollectionRCV.setLayoutManager(new LinearLayoutManager(this));

        setTitle("Collections");
    }

    private ArrayList<Deck> loadFlashcardCollection(){
        ArrayList<Deck> flashcardCollections = new ArrayList<>();
        Deck fcBird = new Deck(loadFlashcard());
        Deck fcCat = new Deck(loadFlashcard());
        Deck fcDog = new Deck(loadFlashcard());

//        fcBird.setCoverImage(Drawable.createFromPath("drawable/huhcat.png"));
        flashcardCollections.add(fcBird);
        flashcardCollections.add(fcCat);
        flashcardCollections.add(fcDog);

        return flashcardCollections;
    }
    private ArrayList<Flashcard> loadFlashcard(){
        ArrayList<Flashcard> f = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            f.add(new Flashcard());
        }
        return f;
    }
}