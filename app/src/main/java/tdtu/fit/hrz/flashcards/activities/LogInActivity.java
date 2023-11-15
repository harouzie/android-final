package tdtu.fit.hrz.flashcards.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import tdtu.fit.hrz.flashcards.R;
import tdtu.fit.hrz.flashcards.objects.UserAccount;

public class LogInActivity extends AppCompatActivity {
    String username, password;
    EditText usernameEditText;
    TextInputEditText passwordEditText;
    UserAccount demo_account;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        demo_account = new UserAccount("demoUsername", "demoPassword", "Demo User");
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("UserAccountInfo");
    }

    public void loginConfirmButtonClick(View view) {
        username = usernameEditText.getText().toString();
        password = passwordEditText.getText().toString();

        checkLoginInfo(username, password);
    }

    private void checkLoginInfo(String username, String password) {
        databaseReference.orderByChild("username").equalTo(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        UserAccount userAccount = snapshot.getValue(UserAccount.class);

                        if (userAccount != null && userAccount.getPassword().equals(password)) {
                            Toast.makeText(LogInActivity.this, "Login Successfully!", Toast.LENGTH_SHORT).show();
                            loginSuccess(userAccount.getDisplayName());
                        } else {
                            Toast.makeText(LogInActivity.this, "Incorrect password!", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(LogInActivity.this, "Username not found!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(LogInActivity.this, "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void loginSuccess(String displayName) {
        Intent intent = new Intent(LogInActivity.this, MainActivity.class);
        intent.putExtra("DISPLAY_NAME", displayName);
        startActivity(intent);
        finish();  // Đóng LogInActivity nếu cần
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(LogInActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}