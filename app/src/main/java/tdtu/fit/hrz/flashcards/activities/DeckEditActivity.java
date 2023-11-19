package tdtu.fit.hrz.flashcards.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

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
//        int id = item.getItemId();
//        if(id == R.id.action_delete_card){
//            Toast.makeText(this, "delete card", Toast.LENGTH_SHORT).show();
//        }
        return super.onOptionsItemSelected(item);
    }

    private ArrayList<Card> loadFlashcard(){
        ArrayList<Card> f = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            f.add(new Card());
        }
        return f;
    }
    // TODO delete flashcard - deck change
    public void deleteFlashCard(View view) {
        Toast.makeText(this, "Delete card action", Toast.LENGTH_SHORT).show();
    }
}