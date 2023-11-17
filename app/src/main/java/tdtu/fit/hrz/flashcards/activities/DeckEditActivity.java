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
import tdtu.fit.hrz.flashcards.controllers.CardRCVAdapter;
import tdtu.fit.hrz.flashcards.objects.Card;

public class DeckEditActivity extends AppCompatActivity {

    RecyclerView flashcardRCV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck_edit);

        setTitle("Flashcard Preview");

        flashcardRCV = findViewById(R.id.flashcardRCV);
        CardRCVAdapter mRCVAdapter = new CardRCVAdapter(
                this, R.layout.rcv_card, loadFlashcard());
        flashcardRCV.setAdapter(mRCVAdapter);
        flashcardRCV.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.card_edit_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.flashcard_action_add){
            Intent intent = new Intent(this, CardEditActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private ArrayList<Card> loadFlashcard(){
        ArrayList<Card> f = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            f.add(new Card());
        }
        return f;
    }
}