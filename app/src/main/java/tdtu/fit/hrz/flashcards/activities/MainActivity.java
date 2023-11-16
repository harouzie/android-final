package tdtu.fit.hrz.flashcards.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import tdtu.fit.hrz.flashcards.R;

public class MainActivity extends AppCompatActivity {

    Button loginButton, signUpButton, logoutButton;
    TextView haveAccountTitle, welcomeUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signUpButton = findViewById(R.id.signUpButton);
        haveAccountTitle = findViewById(R.id.haveAccountTitle);
        loginButton = findViewById(R.id.logInButton);
        logoutButton = findViewById(R.id.logoutButton);
        welcomeUser = findViewById(R.id.welcomeUser);


        if (getIntent().hasExtra("DISPLAY_NAME")) {
            String displayName = getIntent().getStringExtra("DISPLAY_NAME");
            logoutButton.setVisibility(View.VISIBLE);
            loginButton.setVisibility(View.INVISIBLE);
            signUpButton.setVisibility(View.INVISIBLE);
            haveAccountTitle.setVisibility(View.INVISIBLE);
            welcomeUser.setText("Welcome " + displayName);
        }
    }

    public void signUpButtonClick(View view) {
        Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    public void loginButtonClick(View view) {
        Intent intent = new Intent(MainActivity.this, LogInActivity.class);
        startActivity(intent);
        finish();
    }

    public void quizLayoutClick(View view) {
        Toast.makeText(this, "Quiz Activity was chosen!", Toast.LENGTH_SHORT).show();
    }

    public void flashcardLayoutClick(View view) {
        Toast.makeText(this, "FlashCard Activity was chosen!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, FlashcardCollectionPreviewActivity.class);
        startActivity(intent);
    }
}