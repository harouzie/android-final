package tdtu.fit.hrz.flashcards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class SignUpActivity extends AppCompatActivity {

    EditText usernameText;
    TextInputEditText firstPasswordText, secondPasswordText;
    String username, password1, password2;
    Button confirmSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        usernameText = findViewById(R.id.usernameEditText);
        usernameText.requestFocus();
        firstPasswordText = findViewById(R.id.passwordEditText);
        secondPasswordText = findViewById(R.id.passwordConfirmEditText);
        confirmSignUp = findViewById(R.id.signUpConfirmButton);

    }

    public void signUpConfirmButtonClick(View view) {
        username = usernameText.getText().toString();
        password1 = firstPasswordText.getText().toString();
        password2 = secondPasswordText.getText().toString();

        if (username.length() < 6) {
            Toast.makeText(SignUpActivity.this, "Username too short! It must" +
                            " be at least 6 characters",
                    Toast.LENGTH_SHORT).show();
        } else if (password1.length() < 8 || password2.length() < 8) {
            Toast.makeText(SignUpActivity.this, "Password too short! It must" +
                            " be at least 8 characters",
                    Toast.LENGTH_SHORT).show();
        } else if (!password1.equals(password2)) {
            Toast.makeText(SignUpActivity.this, "Passwords unmatched!",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(SignUpActivity.this, "Sign up successfully!",
                    Toast.LENGTH_SHORT).show();
        }

        // Already exist username handle later
    }

}