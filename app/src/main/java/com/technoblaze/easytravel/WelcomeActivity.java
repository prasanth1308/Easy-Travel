package com.technoblaze.easytravel;

import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.text.SimpleDateFormat;

public class WelcomeActivity extends AppCompatActivity {

    ConstraintLayout welcomeScreen;
    SimpleDateFormat simpleDateFormat;
    String time;
    Calendar calander;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_welcome );
        welcomeScreen  = findViewById(R.id.container);
        calander = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("HH");

        int timeOfDay  = Integer.parseInt( simpleDateFormat.format(calander.getTime()) );
       // int timeOfDay = calander.get(Calendar.HOUR_OF_DAY);

        if(timeOfDay >= 0 && timeOfDay < 12){
            Toast.makeText(this, "Good Morning", Toast.LENGTH_SHORT).show();
            welcomeScreen.setBackgroundResource(R.drawable.day_bg);
        }else if(timeOfDay >= 12 && timeOfDay < 14){
            Toast.makeText(this, "Good Afternoon", Toast.LENGTH_SHORT).show();
            welcomeScreen.setBackgroundResource(R.drawable.day_bg);
        }else if(timeOfDay >= 14 && timeOfDay < 19){
            Toast.makeText(this, "Good Evening", Toast.LENGTH_SHORT).show();
            welcomeScreen.setBackgroundResource(R.drawable.night_bg);
        }else if(timeOfDay >= 19 && timeOfDay < 24){
            Toast.makeText(this, "Good Night", Toast.LENGTH_SHORT).show();
            welcomeScreen.setBackgroundResource(R.drawable.night_bg);
        }

        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out,
                        R.anim.fade_in, R.anim.fade_out)
                .replace(R.id.container, new WelcomeFragment() )
                .commit();
    }
}
