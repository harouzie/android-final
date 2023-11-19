package tdtu.fit.hrz.flashcards.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import tdtu.fit.hrz.flashcards.R;
import tdtu.fit.hrz.flashcards.fragments.DeckDownloadFragment;
import tdtu.fit.hrz.flashcards.fragments.DeckLibraryFragment;
import tdtu.fit.hrz.flashcards.fragments.UserFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView navBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Navbar
        navBar = findViewById(R.id.bottom_navigation);
        navBar.setSelectedItemId(R.id.nav_library);
        //

    }

    @Override
    protected void onResume() {
        super.onResume();

        /////////////////// - Navbar
        navBar.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.nav_download) {
                replaceFragment(new DeckDownloadFragment());
                return true;
            } else if (itemId == R.id.nav_user) {
                replaceFragment(new UserFragment());
                return true;
            } else if (itemId == R.id.nav_library) {
                replaceFragment(new DeckLibraryFragment());
                return true;
            }
            return false;
        });
        ///////////////////
    }

    //Handling fragment switching
    private void replaceFragment(Fragment frag) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container_view, frag);
        ft.commit();
    }
}