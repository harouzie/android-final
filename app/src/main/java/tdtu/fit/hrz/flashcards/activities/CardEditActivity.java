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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;

import tdtu.fit.hrz.flashcards.R;
import tdtu.fit.hrz.flashcards.viewmodels.DeckViewModel;

public class CardEditActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageButton btnAddQuesImg,btnAddAnsImg;
    private ImageView questionImageView;
    private MaterialToolbar topAppBar;
    private TextInputEditText text;
    private DeckViewModel model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_edit);

        model = new ViewModelProvider(this).get(DeckViewModel.class);

        text = findViewById(R.id.question);
        topAppBar = findViewById(R.id.topAppBar);
        topAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id  = item.getItemId();
                if(id == R.id.action_delete_card){
                    Toast.makeText(CardEditActivity.this, "delete card", LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });
        topAppBar.setNavigationOnClickListener(view -> {
            super.onBackPressed();
        });


        // TODO add image for flashcard
//        questionImageView = findViewById(R.id.questionImage);
//        btnAddQuesImg = findViewById(R.id.btnAddQuesImg);
//        btnAddQuesImg.setOnClickListener(v -> openImagePicker());


    }

    @Override
    protected void onStart() {
        super.onStart();
        text.setText(model.getQuestion());
    }

    @Override
    protected void onPause() {
        super.onPause();
        model.setQuestion(String.valueOf(text.getText()));
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
