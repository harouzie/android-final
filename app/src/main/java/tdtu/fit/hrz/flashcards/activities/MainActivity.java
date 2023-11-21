package tdtu.fit.hrz.flashcards.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import tdtu.fit.hrz.flashcards.R;
import tdtu.fit.hrz.flashcards.controllers.StorageManager;
import tdtu.fit.hrz.flashcards.fragments.DeckDownloadFragment;
import tdtu.fit.hrz.flashcards.fragments.DeckLibraryFragment;
import tdtu.fit.hrz.flashcards.fragments.UserFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView navBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotificationChannel();

        navBar = findViewById(R.id.bottom_navigation);
        navBar.setSelectedItemId(R.id.nav_library);
        StorageManager.context = this;
        StorageManager storageManager = StorageManager.getInstance();
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
    }

    private void replaceFragment(Fragment frag) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container_view, frag);
        ft.commit();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Reminder";
            String description = "Get in and study";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("channel_id", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

}