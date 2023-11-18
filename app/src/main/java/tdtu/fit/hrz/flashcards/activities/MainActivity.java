package tdtu.fit.hrz.flashcards.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import tdtu.fit.hrz.flashcards.R;
import tdtu.fit.hrz.flashcards.objects.UserAccount;

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
        loginCheck();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loginCheck();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
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

    public void logoutButtonClick(View view) {
        SharedPreferences preferences = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isLoggedIn", false);
        editor.putString("displayName", "NONE");
        editor.apply();
        loginCheck();
        Toast.makeText(this, "Logged out successfully!", Toast.LENGTH_LONG).show();
    }

    public void quizLayoutClick(View view) {
        Toast.makeText(this, "Quiz Activity was chosen!", Toast.LENGTH_SHORT).show();
    }

    public void flashcardLayoutClick(View view) {
        Toast.makeText(this, "FlashCard Activity was chosen!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, DeckLibraryActivity.class);
        startActivity(intent);
    }

    public void loginCheck() {
        SharedPreferences preferences = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        boolean isLoggedIn = preferences.getBoolean("isLoggedIn", false);

        if (isLoggedIn) {
            String displayName = preferences.getString("displayName", "NONE");
            logoutButton.setVisibility(View.VISIBLE);
            loginButton.setVisibility(View.INVISIBLE);
            signUpButton.setVisibility(View.INVISIBLE);
            haveAccountTitle.setVisibility(View.INVISIBLE);
            welcomeUser.setText("Welcome " + displayName);
            welcomeUser.setVisibility(View.VISIBLE);
        } else {
            logoutButton.setVisibility(View.INVISIBLE);
            loginButton.setVisibility(View.VISIBLE);
            signUpButton.setVisibility(View.VISIBLE);
            haveAccountTitle.setVisibility(View.VISIBLE);
            welcomeUser.setVisibility(View.INVISIBLE);
        }
    }

}