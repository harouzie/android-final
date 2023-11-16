package tdtu.fit.hrz.flashcards.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import tdtu.fit.hrz.flashcards.R;
import tdtu.fit.hrz.flashcards.controllers.FlashcardCollectionRCVAdapter;
import tdtu.fit.hrz.flashcards.controllers.FlashcardRCVAdapter;
import tdtu.fit.hrz.flashcards.objects.Flashcard;
import tdtu.fit.hrz.flashcards.objects.FlashcardCollection;

public class FlashcardCollectionPreviewActivity extends AppCompatActivity {
    RecyclerView flashcardCollectionRCV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard_collection_preview);
        flashcardCollectionRCV = findViewById(R.id.flashcardCollectionRCV);
        FlashcardCollectionRCVAdapter mRCVAdapter = new FlashcardCollectionRCVAdapter(
                this, R.layout.rcv_flc_collection, loadFlashcardCollection());
        flashcardCollectionRCV.setAdapter(mRCVAdapter);
        flashcardCollectionRCV.setLayoutManager(new LinearLayoutManager(this));

    }

    private ArrayList<FlashcardCollection> loadFlashcardCollection(){
        ArrayList<FlashcardCollection> flashcardCollections = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            flashcardCollections.add(new FlashcardCollection(loadFlashcard()));
        }
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