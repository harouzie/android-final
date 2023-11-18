package tdtu.fit.hrz.flashcards.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import tdtu.fit.hrz.flashcards.R;

public class DeckDownloadActivity extends AppCompatActivity {

    private BottomNavigationView navBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck_download);

        /////////////////// - Navbar

        navBar = findViewById(R.id.bottom_navigation);
        navBar.setSelectedItemId(R.id.nav_download);

        navBar.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.nav_library) {
                    Intent intent = new Intent(DeckDownloadActivity.this, DeckLibraryActivity.class);
                    DeckDownloadActivity.this.startActivity(intent);
                    return true;
                } else if (itemId == R.id.nav_user) {
                    //not yet
                    return true;
                }
                return false;
            }
        });

        ///////////////////
    }
}