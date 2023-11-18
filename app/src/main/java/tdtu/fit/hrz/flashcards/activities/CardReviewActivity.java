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

import tdtu.fit.hrz.flashcards.R;
import tdtu.fit.hrz.flashcards.objects.UserAccount;

public class CardReviewActivity extends AppCompatActivity {
    TextView tv;
    AppCompatButton bt;
    MaterialToolbar toolbar;
    UserAccount loggedUser;
    MaterialToolbar topAppBar;
    String loggedUserID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_review);
        loggedUserID = getSharedPreferences("UserInfo", Context.MODE_PRIVATE).getString("userKey", "none");
        loggedUser = new UserAccount(loggedUserID);
        toolbar = findViewById(R.id.topAppBar);
        topAppBar = findViewById(R.id.topAppBar);
        tv = findViewById(R.id.tv);
        bt = findViewById(R.id.bt);

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

    public void testClick(View view) {
        tv.setText(loggedUser.getFlashcardCollection().get("Sample question 1") + "!!!");
        loggedUser.newCard("Sample question 1", "Sample answer 1");
        loggedUser.changeCardAnswer("Sample question 2", "New answer 2");
        loggedUser.getFlashcardCollection();
    }
}