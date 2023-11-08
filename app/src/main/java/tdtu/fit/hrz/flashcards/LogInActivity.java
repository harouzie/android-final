package tdtu.fit.hrz.flashcards;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import tdtu.fit.hrz.flashcards.objects.UserAccount;

public class LogInActivity extends AppCompatActivity {
    String username, password;
    EditText usernameEditText;
    TextInputEditText passwordEditText;
    UserAccount demo_account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        demo_account = new UserAccount("demoUsername", "demoPassword", "Demo User");
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
    }

    public void logInConfirmButtonClick(View view) {
        username = usernameEditText.getText().toString();
        password = passwordEditText.getText().toString();

        if (!username.equals(demo_account.getUsername())) {
            Toast.makeText(this, "Cannot find this username!", Toast.LENGTH_SHORT).show();
        } else if (!password.equals(demo_account.getPassword())) {
            Toast.makeText(this, "Wrong password!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Login Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

}