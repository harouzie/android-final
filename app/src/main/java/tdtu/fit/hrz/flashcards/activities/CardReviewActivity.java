package tdtu.fit.hrz.flashcards.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.appbar.MaterialToolbar;

import tdtu.fit.hrz.flashcards.R;

public class CardReviewActivity extends AppCompatActivity {

    MaterialToolbar topAppBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_review);

        topAppBar = findViewById(R.id.topAppBar);
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
    }
}