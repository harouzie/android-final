package tdtu.fit.hrz.flashcards.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

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

        setTitle("Flashcard Preview");

        flashcardRCV = findViewById(R.id.flashcardRCV);
        FlashcardRCVAdapter mRCVAdapter = new FlashcardRCVAdapter(
                this, R.layout.rcv_flashcard, loadFlashcard());
        flashcardRCV.setAdapter(mRCVAdapter);
        flashcardRCV.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.flashcard_option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.flashcard_action_add){
            Intent intent = new Intent(this, FlashcardCreatorActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private ArrayList<Flashcard> loadFlashcard(){
        ArrayList<Flashcard> f = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            f.add(new Flashcard());
        }
        return f;
    }
}