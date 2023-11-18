package tdtu.fit.hrz.flashcards.objects;

import static android.content.ContentValues.TAG;

import android.util.Log;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class UserAccount {
    private String username, password, displayName;
    private String keyID;
    private HashMap<String, String> flashcardCollection = new HashMap<>();
    public UserAccount() {};
    public UserAccount(String userID) {
        DatabaseReference userReference = FirebaseDatabase.getInstance().getReference("UserAccountInfo").child(userID);

        userReference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    DataSnapshot dataSnapshot = task.getResult();
                    if (dataSnapshot.exists()) {
                        keyID = userID;
                        username = dataSnapshot.child("username").getValue(String.class);
                        password = dataSnapshot.child("password").getValue(String.class);
                        displayName = dataSnapshot.child("displayName").getValue(String.class);

                        DataSnapshot flashcardSnapshot = dataSnapshot.child("flashcardCollection");
                        if (flashcardSnapshot.exists()) {
                            for (DataSnapshot cardSnapshot : flashcardSnapshot.getChildren()) {
                                String question = cardSnapshot.getKey();
                                String answer = cardSnapshot.getValue(String.class);
                                flashcardCollection.put(question, answer);
                            }
                        }
                    } else {
                        Log.e(TAG, "Cannot find provided userID", task.getException());
                    }
                } else {
                    Log.e(TAG, "Error loading user data", task.getException());
                }
            }
        });
    }

    public UserAccount(String username, String password, String displayName) {
        this.username = username;
        this.password = password;
        this.displayName = displayName;
    }

    public String getUsername() { return this.username; }
    public String getPassword() { return this.password; }
    public String getDisplayName() { return this.displayName; }
    public String getKeyID() { return keyID; }
    public HashMap<String, String> getFlashcardCollection() { return this.flashcardCollection; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setDisplayName(String displayName) { this.displayName = displayName; }
    public void setKeyID(String keyID) { this.keyID = keyID; }

    public void newCard(String question, String answer) {
        this.flashcardCollection.put(question, answer);
        updateCollectionToFirebase();
    }

    public void deleteCard(String question) {
        this.flashcardCollection.remove(question);
        updateCollectionToFirebase();
    }

    public void changeCardAnswer(String question, String newAnswer) {
        this.flashcardCollection.put(question, newAnswer);
        updateCollectionToFirebase();
    }

    public void updateCollectionToFirebase() {
        DatabaseReference userReference = FirebaseDatabase.getInstance().getReference("UserAccountInfo").child(this.keyID).child("flashcardCollection");

        for (Map.Entry<String, String> entry : this.flashcardCollection.entrySet()) {
            String question = entry.getKey();
            String answer = entry.getValue();
            userReference.child(question).setValue(answer);
        }
    }

}
