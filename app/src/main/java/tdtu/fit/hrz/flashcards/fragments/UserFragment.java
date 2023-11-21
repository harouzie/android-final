package tdtu.fit.hrz.flashcards.fragments;

import static android.content.Context.ALARM_SERVICE;

import android.app.ActionBar;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.util.Locale;
import tdtu.fit.hrz.flashcards.R;
import tdtu.fit.hrz.flashcards.activities.LogInActivity;
import tdtu.fit.hrz.flashcards.activities.SignUpActivity;
import tdtu.fit.hrz.flashcards.objects.AlarmReceiver;

public class UserFragment extends Fragment {

    Button loginButton, signUpButton, logoutButton;
    TextView haveAccountTitle, welcomeUser, alarmTime;
    CardView userInfoCardView;
    TimePicker timePicker;
    Switch reminderSwitch;
    int hourOfDay, minute;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        //onClick attribute in fragment's xml not working the way it work in activity's xml so we need this
        reminderSwitch = view.findViewById(R.id.reminderSwitch);
        alarmTime = view.findViewById(R.id.timePickerTextView);
        signUpButton = view.findViewById(R.id.signUpButton);
        haveAccountTitle = view.findViewById(R.id.haveAccountTitle);
        loginButton = view.findViewById(R.id.logInButton);
        logoutButton = view.findViewById(R.id.logoutButton);
        welcomeUser = view.findViewById(R.id.welcomeUser);
        userInfoCardView = view.findViewById(R.id.userInfoCardView);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
        boolean isReminderOn = sharedPreferences.getBoolean("reminderSwitch", false);
        reminderSwitch.setChecked(isReminderOn);

        alarmTime.setOnClickListener(v -> showTimePickerDialog());

        reminderSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                setReminder();
            } else {
                cancelReminder();
            }

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("reminderSwitch", isChecked);
            editor.apply();
        });

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

    private void showTimePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(
                requireContext(),
                (view, hourOfDay1, minute1) -> {
                    updateSelectedTime(hourOfDay1, minute1);
                },
                hourOfDay,
                minute,
                true
        );

        timePickerDialog.show();
    }

    private void updateSelectedTime(int hourOfDay, int minute) {
        String formattedTime = String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute);

        alarmTime.setText(formattedTime);
    }

    private void setReminder() {
        Intent intent = new Intent(requireContext(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                requireContext(),
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        AlarmManager alarmManager = (AlarmManager) getContext().getSystemService(ALARM_SERVICE);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);

        if (calendar.getTimeInMillis() <= System.currentTimeMillis()) {
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }

        alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY,
                pendingIntent
        );

        Toast.makeText(requireContext(), "Reminder is set at " + hourOfDay + ":" + minute, Toast.LENGTH_SHORT).show();
    }

    private void cancelReminder() {
        Intent intent = new Intent(requireContext(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                requireContext(),
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        AlarmManager alarmManager = (AlarmManager) getContext().getSystemService(ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
        Toast.makeText(requireContext(), "Reminder is off", Toast.LENGTH_SHORT).show();
    }

}