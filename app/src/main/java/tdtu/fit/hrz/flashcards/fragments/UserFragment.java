package tdtu.fit.hrz.flashcards.fragments;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import tdtu.fit.hrz.flashcards.R;
import tdtu.fit.hrz.flashcards.activities.LogInActivity;
import tdtu.fit.hrz.flashcards.activities.MainActivity;
import tdtu.fit.hrz.flashcards.activities.SignUpActivity;

public class UserFragment extends Fragment {

    Button loginButton, signUpButton, logoutButton;
    TextView haveAccountTitle, welcomeUser;
    CardView userInfoCardView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        //onClick attribute in fragment's xml not working the way it work in activity's xml so we need this
        signUpButton = view.findViewById(R.id.signUpButton);
        haveAccountTitle = view.findViewById(R.id.haveAccountTitle);
        loginButton = view.findViewById(R.id.logInButton);
        logoutButton = view.findViewById(R.id.logoutButton);
        welcomeUser = view.findViewById(R.id.welcomeUser);
        userInfoCardView = view.findViewById(R.id.userInfoCardView);

        signUpButton.setOnClickListener(v -> signUpButtonClick(v));
        loginButton.setOnClickListener(v -> loginButtonClick(v));
        logoutButton.setOnClickListener(v -> logoutButtonClick(v));

        loginCheck();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loginCheck();

        ActionBar actionBar = getActivity().getActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    public void signUpButtonClick(View view) {
        Intent intent = new Intent(getActivity(), SignUpActivity.class);
        startActivity(intent);
    }

    public void loginButtonClick(View view) {
        Intent intent = new Intent(getActivity(), LogInActivity.class);
        startActivity(intent);
        //getActivity().finish();
    }

    public void logoutButtonClick(View view) {
        SharedPreferences preferences = getActivity().getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isLoggedIn", false);
        editor.putString("displayName", "NONE");
        editor.apply();
        loginCheck();
        Toast.makeText(getActivity(), "Logged out successfully!", Toast.LENGTH_LONG).show();
    }

    public void loginCheck() {
        SharedPreferences preferences = getActivity().getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        boolean isLoggedIn = preferences.getBoolean("isLoggedIn", false);

        if (isLoggedIn) {
            String displayName = preferences.getString("displayName", "NONE");
            userInfoCardView.setVisibility(View.VISIBLE);
            logoutButton.setVisibility(View.VISIBLE);
            loginButton.setVisibility(View.INVISIBLE);
            signUpButton.setVisibility(View.INVISIBLE);
            haveAccountTitle.setVisibility(View.INVISIBLE);
            welcomeUser.setText("Welcome " + displayName);
            welcomeUser.setVisibility(View.VISIBLE);
        } else {
            userInfoCardView.setVisibility(View.INVISIBLE);
            logoutButton.setVisibility(View.INVISIBLE);
            loginButton.setVisibility(View.VISIBLE);
            signUpButton.setVisibility(View.VISIBLE);
            haveAccountTitle.setVisibility(View.VISIBLE);
            welcomeUser.setVisibility(View.INVISIBLE);
        }
    }
}