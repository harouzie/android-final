package tdtu.fit.hrz.flashcards.activities;

import static android.widget.Toast.LENGTH_SHORT;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;
import java.util.Locale;

import tdtu.fit.hrz.flashcards.R;
import tdtu.fit.hrz.flashcards.controllers.CardRCVAdapter;
import tdtu.fit.hrz.flashcards.controllers.DeckAdapter;
import tdtu.fit.hrz.flashcards.controllers.StorageManager;
import tdtu.fit.hrz.flashcards.objects.Card;
import tdtu.fit.hrz.flashcards.objects.Deck;
import tdtu.fit.hrz.flashcards.viewmodels.DeckViewModel;

public class CardEditActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageButton btnAddQuesImg,btnAddAnsImg;
    private ImageView questionImageView;
    private MaterialToolbar topAppBar;
    private TextInputEditText cardTextInput;
    private DeckViewModel model;
    private ShapeableImageView deckCover;
    private TextView deckNumcard, deckName, cardQAText;
    private Button flipButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_edit);

        model = new ViewModelProvider(this).get(DeckViewModel.class);

        topAppBar = findViewById(R.id.topAppBar);
        deckCover = findViewById(R.id.deckCover);
        deckName  = findViewById(R.id.deckName);
        deckNumcard  = findViewById(R.id.deckNumCard);
        cardTextInput = findViewById(R.id.cardSideText);
        flipButton =  findViewById(R.id.flipButton);
        cardQAText =  findViewById(R.id.cardQA);

        //========DECK and CARD Binding=======================================================
        StorageManager storageManager = StorageManager.getInstance();

        Deck deck = storageManager.getDecks().get(DeckAdapter.selectedDeckIdx);
        List<Card> cards = deck.getCards();
        Card selectedCard = cards.get(CardRCVAdapter.selectedCardIdx);

        deckCover.setImageDrawable(deck.getCoverImage());
        deckName.setText(deck.getDeckName());
        deckNumcard.setText(String.format(Locale.ENGLISH, "%03d",deck.getSize()));

        if (deck.getSize() != 0) {
            cardTextInput.setText(selectedCard.getQuestion());
            cardTextInput.setHint("Your question");
        }

        //======FLIP Button=========================================================
        flipButton.setOnClickListener(view -> {
            if (selectedCard.isFlipped()){
                // flipped = answer -> set to ques now
                cardQAText.setText(R.string.question);
                cardTextInput.setHint("Your question");
                cardTextInput.setText(selectedCard.getQuestion());
                selectedCard.flip();
            } else {
                //not flipped yet = currently question side -> set to ans now
                cardQAText.setText(R.string.answer);
                cardTextInput.setHint("Your answer");
                cardTextInput.setText(selectedCard.getAnswer());
                cardTextInput.clearFocus();
                cardTextInput.requestFocus();
                selectedCard.flip();
            }
        });

        //=======TOP APP BAR========================================================
        topAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id  = item.getItemId();
                if(id == R.id.action_delete_card){
                    DeckEditActivity.mRCVAdapter.removeCard();
                    Toast.makeText(CardEditActivity.this, "Card deleted", LENGTH_SHORT).show();
                    finish();
                    return true;
                }
                return false;
            }
        });
        topAppBar.setNavigationOnClickListener(view -> {
            super.onBackPressed();
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
//        deckName.setText(model.getQuestion());
    }

    @Override
    protected void onPause() {
        super.onPause();
//        model.setQuestion(String.valueOf(deckName.getText()));
    }

    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK) {
            if (data != null) {
                Uri selectedImageUri = data.getData();
                questionImageView.setImageURI(selectedImageUri);
//                int imageWidth = questionImageView.getDrawable().getIntrinsicWidth();
                int imageHeight = questionImageView.getDrawable().getIntrinsicHeight()/2;

                ViewGroup.LayoutParams layoutParams = questionImageView.getLayoutParams();
                // Set the ImageView's size based on the image's dimensions
                layoutParams.height = imageHeight;
                questionImageView.setLayoutParams(layoutParams);
            }
        }
    }
}
