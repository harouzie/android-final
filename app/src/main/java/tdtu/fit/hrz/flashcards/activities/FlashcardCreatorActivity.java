package tdtu.fit.hrz.flashcards.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import tdtu.fit.hrz.flashcards.R;

public class FlashcardCreatorActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageButton btnAddQuesImg,btnAddAnsImg;
    private ImageView questionImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard_creator);

        questionImageView = findViewById(R.id.questionImage);
        btnAddQuesImg = findViewById(R.id.btnAddQuesImg);

        btnAddQuesImg.setOnClickListener(v -> openImagePicker());
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
