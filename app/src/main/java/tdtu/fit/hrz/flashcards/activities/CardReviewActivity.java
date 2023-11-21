package tdtu.fit.hrz.flashcards.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import tdtu.fit.hrz.flashcards.R;
import tdtu.fit.hrz.flashcards.controllers.DeckAdapter;
import tdtu.fit.hrz.flashcards.fragments.CardAnswerFragment;
import tdtu.fit.hrz.flashcards.fragments.CardQuestionFragment;
import tdtu.fit.hrz.flashcards.fragments.DeckDownloadFragment;
import tdtu.fit.hrz.flashcards.fragments.DeckLibraryFragment;
import tdtu.fit.hrz.flashcards.fragments.UserFragment;
import tdtu.fit.hrz.flashcards.objects.Card;
import tdtu.fit.hrz.flashcards.objects.Deck;
import tdtu.fit.hrz.flashcards.objects.UserAccount;
import tdtu.fit.hrz.flashcards.viewmodels.CardReviewViewModel;
import tdtu.fit.hrz.flashcards.viewmodels.DeckViewModel;

public class CardReviewActivity extends AppCompatActivity {
    private MaterialToolbar topAppBar;
    private ShapeableImageView deckCover;
    private TextView deckName, deckNumcard;
    private CardQuestionFragment questionFragment;
    private CardAnswerFragment answerFragment;
    private CardReviewViewModel model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_review);

        topAppBar = findViewById(R.id.topAppBar);
        deckCover = findViewById(R.id.deckCover);
        deckName  = findViewById(R.id.deckName);
        deckNumcard  = findViewById(R.id.deckNumCard);

        //=======================================
        topAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id  = item.getItemId();
                if(id == R.id.action_edit_deck){
                    Intent intent = new Intent(CardReviewActivity.this, DeckEditActivity.class);
                    intent.setAction(Deck.ACTION_EDIT_DECK);
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
        model = new ViewModelProvider(this).get(CardReviewViewModel.class);
        Deck deck = DeckAdapter.deckList.get(DeckAdapter.selectedPos);
        model.setCards(deck.getDueDateCard());

        deckCover.setImageDrawable(deck.getCoverImage());
        deckName.setText(deck.getDeckName());
        deckNumcard.setText(String.format(Locale.ENGLISH, "%03d",deck.getSize()));

        switchFragment(-1); //-1 is only for load first question of the first card, one time use
    }

    //Handling fragment switching
    public void switchFragment(int rating) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        if (rating == -1) {
            questionFragment = new CardQuestionFragment();
            ft.replace(R.id.fragment_container, questionFragment);
        } else if (rating == 0) {
            ft.replace(R.id.fragment_container, new CardAnswerFragment());
        } else if (rating == 1) {
            model.getCards().get(model.getCurrentCard()).updateNextPractice(rating);
            ft.replace(R.id.fragment_container, questionFragment);
        } else {
            model.getCards().get(model.getCurrentCard()).updateNextPractice(rating);
            model.setCurrentCard(model.getCurrentCard()+1);

            if (!model.isOver()) {
                questionFragment = new CardQuestionFragment();
                ft.replace(R.id.fragment_container, questionFragment);
            } else {
                //done reviewing, maybe show popup congratulation, then back to library
                Toast.makeText(getApplicationContext(), "Done reviewing for this deck today", Toast.LENGTH_LONG).show();
                finish();
            }
        }
        ft.commit();
    }
}