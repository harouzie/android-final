package tdtu.fit.hrz.flashcards.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.Nullable;

import tdtu.fit.hrz.flashcards.R;
import tdtu.fit.hrz.flashcards.objects.UserAccount;

public class SignUpActivity extends AppCompatActivity {

    EditText usernameText, displayNameText;
    TextInputEditText firstPasswordText, secondPasswordText;
    String username, password1, password2, displayName;
    Button confirmSignUp;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        usernameText = findViewById(R.id.usernameEditText);
        displayNameText = findViewById(R.id.displayNameEditText);
        usernameText.requestFocus();
        firstPasswordText = findViewById(R.id.passwordEditText);
        secondPasswordText = findViewById(R.id.passwordConfirmEditText);
        confirmSignUp = findViewById(R.id.signUpConfirmButton);
    }

    public void signUpConfirmButtonClick(View view) {

        username = usernameText.getText().toString();
        displayName = displayNameText.getText().toString();
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
            UserAccount newAccount = new UserAccount(username, password1, displayName);
            addDataToFirebase(newAccount);
        }

    }

    private void addDataToFirebase(UserAccount acc) {
        databaseReference = FirebaseDatabase.getInstance().getReference("UserAccountInfo");

        databaseReference.orderByChild("username").equalTo(acc.getUsername()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Toast.makeText(SignUpActivity.this, "Username already exists!", Toast.LENGTH_SHORT).show();
                } else {
                    String key = databaseReference.push().getKey();
                    databaseReference.child(key).setValue(acc, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                            if (databaseError == null) {
                                Toast.makeText(SignUpActivity.this, "Added to database!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(SignUpActivity.this, "Error adding to database: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Xử lý lỗi khi kiểm tra tên người dùng
                Toast.makeText(SignUpActivity.this, "Error checking username: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}