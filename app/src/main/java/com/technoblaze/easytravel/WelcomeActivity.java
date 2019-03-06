package com.technoblaze.easytravel;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;

import java.text.SimpleDateFormat;

public class WelcomeActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_INTRO = 1;
    public static final String PREF_KEY_FIRST_START = "com.technoblaze.easytravel.PREF_KEY_FIRST_START";

    // [START declare_auth]
    private FirebaseAuth fireAuth = FirebaseAuth.getInstance();
    // [END declare_auth]

    ConstraintLayout welcomeScreen;
    SimpleDateFormat simpleDateFormat;
    Calendar calander;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        AppCenter.start(getApplication(), "35085b09-cca1-44d7-b70f-322197f2960f",
                Analytics.class, Crashes.class);
        setContentView(R.layout.activity_welcome);
        boolean firstStart = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean(PREF_KEY_FIRST_START, true);

        welcomeScreen  = findViewById(R.id.container);
        calander = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("HH");

        int timeOfDay  = Integer.parseInt( simpleDateFormat.format(calander.getTime()) );



        if(timeOfDay >= 0 && timeOfDay < 12){
            //Toast.makeText(this, "Good Morning " + timeOfDay, Toast.LENGTH_SHORT).show();
            welcomeScreen.setBackgroundResource(R.drawable.day_bg);
        }else if(timeOfDay >= 12 && timeOfDay < 14){
            //Toast.makeText(this, "Good Afternoon " + timeOfDay, Toast.LENGTH_SHORT).show();
            welcomeScreen.setBackgroundResource(R.drawable.day_bg);
        }else if(timeOfDay >= 14 && timeOfDay < 19){
            //Toast.makeText(this, "Good Evening " + timeOfDay, Toast.LENGTH_SHORT).show();
            welcomeScreen.setBackgroundResource(R.drawable.night_bg);
        }else if(timeOfDay >= 19 && timeOfDay < 24){
            //Toast.makeText(this, "Good Night " + timeOfDay, Toast.LENGTH_SHORT).show();
            welcomeScreen.setBackgroundResource(R.drawable.night_bg);
        }

        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out,
                        R.anim.fade_in, R.anim.fade_out)
                .replace(R.id.container, new WelcomeFragment() )
                .commit();


//        if (firstStart) {
//            Intent intent = new Intent(this, AppIntroActivity.class);
//            startActivityForResult(intent, REQUEST_CODE_INTRO);
//        } else {
//            // Check if user is signed in (non-null) and update UI accordingly.
//            FirebaseUser currentUser = fireAuth.getCurrentUser();
//            updateUI(currentUser);
//        }

        FirebaseUser currentUser = fireAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        } else {
           // View parentLayout = findViewById(android.R.id.content);
            //Snackbar.make(parentLayout, " Please login to continue", Snackbar.LENGTH_LONG)
             //       .setAction("Action", null).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_INTRO) {
            if (resultCode == RESULT_OK) {
                PreferenceManager.getDefaultSharedPreferences(this).edit()
                        .putBoolean(PREF_KEY_FIRST_START, false)
                        .apply();
                //Toast toast = Toast
               //         .makeText(WelcomeActivity.this, "RESULT_OK", Toast.LENGTH_SHORT);
               // toast.setGravity( Gravity.CENTER, 0, 0);
               // toast.show();
            } else {
                PreferenceManager.getDefaultSharedPreferences(this).edit()
                        .putBoolean(PREF_KEY_FIRST_START, true)
                        .apply();
                //User cancelled the intro so we'll finish this activity too.
                //Toast toast = Toast
               //         .makeText(WelcomeActivity.this, "RESULT_NOT_OK", Toast.LENGTH_SHORT);
               // toast.setGravity(Gravity.CENTER, 0, 0);
               // toast.show();
               // finish();
            }
        }
    }


}
