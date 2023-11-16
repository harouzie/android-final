package tdtu.fit.hrz.flashcards.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import tdtu.fit.hrz.flashcards.R;
import tdtu.fit.hrz.flashcards.controllers.FlashcardRCVAdapter;
import tdtu.fit.hrz.flashcards.objects.Flashcard;

public class FlashcardPreviewActivity extends AppCompatActivity {

    RecyclerView flashcardRCV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard_preview);

        flashcardRCV = findViewById(R.id.flashcardRCV);
        FlashcardRCVAdapter mRCVAdapter = new FlashcardRCVAdapter(this, R.layout.rcv_flc_collection,
                loadFlashcard());
        flashcardRCV.setAdapter(mRCVAdapter);
        flashcardRCV.setLayoutManager(new LinearLayoutManager(this));
    }

    private ArrayList<Flashcard> loadFlashcard(){
        ArrayList<Flashcard> f = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            f.add(new Flashcard());
        }
        return f;
    }
}