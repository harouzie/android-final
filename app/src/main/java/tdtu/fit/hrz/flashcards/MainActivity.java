package tdtu.fit.hrz.flashcards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button loginButton, signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signUpButton = findViewById(R.id.signUpButton);
    }

    public void signUpButtonClick(View view) {
        Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    public void loginButtonClick(View view) {
        Intent intent = new Intent(MainActivity.this, LogInActivity.class);
        startActivity(intent);
    }

    public void quizLayoutClick(View view) {
        Toast.makeText(this, "Quiz Activity was chosen!", Toast.LENGTH_SHORT).show();
    }

    public void flashcardLayoutClick(View view) {
        Toast.makeText(this, "FlashCard Activity was chosen!", Toast.LENGTH_SHORT).show();
    }
}