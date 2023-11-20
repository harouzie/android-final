package tdtu.fit.hrz.flashcards.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

import tdtu.fit.hrz.flashcards.R;
import tdtu.fit.hrz.flashcards.controllers.DeckAdapter;
import tdtu.fit.hrz.flashcards.objects.Card;
import tdtu.fit.hrz.flashcards.objects.Deck;
import tdtu.fit.hrz.flashcards.objects.UserAccount;

public class CardReviewActivity extends AppCompatActivity {
    MaterialToolbar topAppBar;
    ShapeableImageView deckCover;
    TextView deckName, deckNumcard, cardQuestion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_review);

        topAppBar = findViewById(R.id.topAppBar);
        deckCover = findViewById(R.id.deckCover);
        deckName  = findViewById(R.id.deckName);
        deckNumcard  = findViewById(R.id.deckNumCard);
        cardQuestion = findViewById(R.id.cardQuestion);

        //=======================================
        topAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id  = item.getItemId();
                if(id == R.id.action_edit_deck){
                    Intent intent = new Intent(CardReviewActivity.this, DeckEditActivity.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });
        topAppBar.setNavigationOnClickListener(view -> {
            super.onBackPressed();
        });

        //=========================================
        Intent intent = getIntent();
        int p = intent.getIntExtra("deck_pos", 0);
        Deck deck = DeckAdapter.deckList.get(p);
        List<Card> cards = deck.getCards();
        deckCover.setImageDrawable(deck.getCoverImage());
        deckName.setText(deck.getDeckName());
        deckNumcard.setText(String.valueOf(deck.getSize()));

        if(deck.getSize() > 0) {
            cardQuestion.setText(cards.get(0).getQuestion());
        }
    }

}