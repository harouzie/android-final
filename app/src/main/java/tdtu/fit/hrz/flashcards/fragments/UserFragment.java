package tdtu.fit.hrz.flashcards.fragments;

import static android.content.Context.ALARM_SERVICE;

import android.app.ActionBar;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import tdtu.fit.hrz.flashcards.AlarmReceiver;
import tdtu.fit.hrz.flashcards.R;
import tdtu.fit.hrz.flashcards.activities.LogInActivity;
import tdtu.fit.hrz.flashcards.activities.SignUpActivity;

public class UserFragment extends Fragment {

    Button loginButton, signUpButton, logoutButton;
    TextView haveAccountTitle, welcomeUser, alarmTime;
    CardView userInfoCardView;
    TimePicker timePicker;
    Handler handler;
    Switch reminderSwitch;
    int hourOfDay, minute;
    String formattedTime;
    PendingIntent alarmIntent;
    AlarmManager alarmManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
        String restoredTime = preferences.getString("pickedAlarmTime", null);

        reminderSwitch = view.findViewById(R.id.reminderSwitch);
        alarmTime = view.findViewById(R.id.timePickerTextView);
        signUpButton = view.findViewById(R.id.signUpButton);
        haveAccountTitle = view.findViewById(R.id.haveAccountTitle);
        loginButton = view.findViewById(R.id.logInButton);
        logoutButton = view.findViewById(R.id.logoutButton);
        welcomeUser = view.findViewById(R.id.welcomeUser);
        userInfoCardView = view.findViewById(R.id.userInfoCardView);

        if (restoredTime != null) {
            alarmTime.setText(restoredTime);
        }

        handler = new Handler(Looper.getMainLooper());
        alarmManager = (AlarmManager) requireContext().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(requireContext(), AlarmReceiver.class);
        alarmIntent = PendingIntent.getBroadcast(requireContext(), 0, intent, PendingIntent.FLAG_IMMUTABLE);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
        boolean isReminderOn = sharedPreferences.getBoolean("reminderSwitch", false);
        reminderSwitch.setChecked(isReminderOn);

        alarmTime.setOnClickListener(v -> showTimePickerDialog());

        reminderSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                startAlarm();
            } else {
                cancelAlarm();
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

    private void startAlarm() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        try {
            Date date = sdf.parse(alarmTime.getText().toString());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            Calendar now = Calendar.getInstance();
            calendar.set(Calendar.YEAR, now.get(Calendar.YEAR));
            calendar.set(Calendar.MONTH, now.get(Calendar.MONTH));
            calendar.set(Calendar.DAY_OF_MONTH, now.get(Calendar.DAY_OF_MONTH));

            long alarmSet = calendar.getTimeInMillis();

            if (alarmSet <= System.currentTimeMillis()) {
                alarmSet += AlarmManager.INTERVAL_DAY;
            }
            Log.d("AlarmReceiver", "Alarm set for: " + new Date(alarmSet));

            // Set the alarm to start at the specified time
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, alarmIntent);
            Toast.makeText(requireContext(), "Study reminder is set at " + alarmTime.getText().toString(), Toast.LENGTH_SHORT).show();

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void cancelAlarm() {
        alarmManager.cancel(alarmIntent);
        Toast.makeText(requireContext(), "Study reminder is off", Toast.LENGTH_SHORT).show();
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
        formattedTime = String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute);
        alarmTime.setText(formattedTime);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("pickedAlarmTime", formattedTime);
        editor.apply();
    }

}