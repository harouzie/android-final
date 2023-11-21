package tdtu.fit.hrz.flashcards.activities;

import static android.widget.Toast.LENGTH_SHORT;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Locale;

import tdtu.fit.hrz.flashcards.R;
import tdtu.fit.hrz.flashcards.controllers.CardRCVAdapter;
import tdtu.fit.hrz.flashcards.controllers.DeckAdapter;
import tdtu.fit.hrz.flashcards.objects.Card;
import tdtu.fit.hrz.flashcards.objects.Deck;
import tdtu.fit.hrz.flashcards.viewmodels.DeckViewModel;

public class DeckEditActivity extends AppCompatActivity {

    private RecyclerView cardRCV;
    public static CardRCVAdapter mRCVAdapter;
    private MaterialToolbar topAppBar;
    private TextInputEditText edtDeckName;
    private FloatingActionButton floatingActionButton;
    private DeckViewModel model;

    private ShapeableImageView deckCover;
    private TextView deckNumcard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck_edit);

        model = new ViewModelProvider(this).get(DeckViewModel.class);

        //========initialize views========================
        topAppBar = findViewById(R.id.topAppBar);
        edtDeckName = findViewById(R.id.deckName);
        cardRCV = findViewById(R.id.cardRCV);
        floatingActionButton = findViewById(R.id.deckEditFAB);
        deckCover = findViewById(R.id.deckCover);
        deckNumcard  = findViewById(R.id.deckNumCard);

        //========TOP BAR================================
        topAppBar.setOnMenuItemClickListener(item -> {
            int id  = item.getItemId();
            if(id == R.id.action_delete_deck){
                Toast.makeText(DeckEditActivity.this, "delete deck", LENGTH_SHORT).show();
                return true;
            }
            return false;
        });
        topAppBar.setNavigationOnClickListener(view -> {
            super.onBackPressed();
        });
        //============HANDLE INTENT==================================
        String action = getIntent().getAction();
//        assert action != null;
        if (action.equals(Deck.ACTION_CREATE_DECK)){
            edtDeckName.setText(R.string.new_deck);
            deckNumcard.setText(String.format(Locale.ENGLISH, "%03d", 0));

            topAppBar.setTitle("Deck Create");

        } else if (action.equals(Deck.ACTION_EDIT_DECK)) {
            Deck deck = DeckAdapter.deckList.get(DeckAdapter.selectedPos);
            ArrayList<Card> cards = deck.getCards();
            deckCover.setImageDrawable(deck.getCoverImage());
            edtDeckName.setText(deck.getDeckName());
            deckNumcard.setText(String.format(Locale.ENGLISH, "%03d",deck.getSize()));

            //========RCV===================================
            mRCVAdapter = new CardRCVAdapter(
                    this, R.layout.rcv_card, cards);
            cardRCV.setAdapter(mRCVAdapter);
            cardRCV.setLayoutManager(new LinearLayoutManager(this));
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        //===Change deck's title========================
//        edtDeckName.setText(model.getDeckTitle());
    }

    @Override
    protected void onResume() {
        super.onResume();
        //========FAB===================================
        floatingActionButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, CardEditActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        //===Change deck's title========================
//        model.setDeckTitle(String.valueOf(edtDeckName.getText()));
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