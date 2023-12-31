package tdtu.fit.hrz.flashcards.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

import tdtu.fit.hrz.flashcards.R;
import tdtu.fit.hrz.flashcards.objects.UserAccount;

public class SignUpActivity extends AppCompatActivity {
    EditText usernameText, displayNameText;
    TextInputEditText firstPasswordText, secondPasswordText;
    TextView errorTextView;
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
        errorTextView = findViewById(R.id.errorTextView);
    }

    public void signUpConfirmButtonClick(View view) {
        username = usernameText.getText().toString();
        displayName = displayNameText.getText().toString();
        password1 = firstPasswordText.getText().toString();
        password2 = secondPasswordText.getText().toString();
        errorTextView.setTextColor(Color.parseColor("#FF0000"));
        boolean basicCheck = false;

        if (username.length() < 6) {
            errorTextView.setText(R.string.username_too_short);
        } else if (password1.length() < 8) {
            errorTextView.setText(R.string.password_too_short);
        } else if (!password1.equals(password2)) {
            errorTextView.setText(R.string.password_confirmation_unmatched);
        } else {
            UserAccount newAccount = new UserAccount(username, password1, displayName);
            addDataToFirebase(newAccount);
            basicCheck = true;
        }

        if (!basicCheck) {
            errorTextView.setVisibility(View.VISIBLE);
        }
    }

    private void addDataToFirebase(UserAccount acc) {
        databaseReference = FirebaseDatabase.getInstance().getReference("UserAccountInfo");
        errorTextView.setTextColor(Color.parseColor("#FF0000"));

        databaseReference.orderByChild("username")
                .equalTo(acc.getUsername()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    errorTextView.setText(R.string.this_username_already_exist);
                } else {
                    String key = databaseReference.push().getKey();
                    acc.setKeyID(key);
                    assert key != null;
                    databaseReference.child(key).setValue(acc, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable DatabaseError databaseError,
                                               @NonNull DatabaseReference databaseReference) {
                            if (databaseError == null) {
                                errorTextView.setText(String.format("%s %s",
                                        getString(R.string.signed_up_successfully),
                                        getString(R.string.you_can_return_to_home_screen)));
                                errorTextView.setTextColor(Color.parseColor("#00FF00"));
                            } else {
                                errorTextView.setText(String.format("%s %s",
                                        getString(R.string.error_adding_to_database),
                                        databaseError.getMessage()));
                            }
                        }
                    });
                }
                errorTextView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(SignUpActivity.this,
                        getString(R.string.error_checking_username)
                            + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}