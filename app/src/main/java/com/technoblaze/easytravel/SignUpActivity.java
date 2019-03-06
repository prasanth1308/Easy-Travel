package com.technoblaze.easytravel;

import android.graphics.Color;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

public class SignUpActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    public SignUpActivity(){

    }

    private DatePickerDialog dpd;
    private EditText txt1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_sign_up );
        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        txt1 = (EditText) findViewById(R.id.dob_sign_up);
        txt1.setInputType( InputType.TYPE_NULL);
        txt1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Toast.makeText(SignUpActivity.this, "Click", Toast.LENGTH_LONG).show();
                showDatePicker();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void showDatePicker(){
        Calendar now = Calendar.getInstance();
        /*
        It is recommended to always create a new instance whenever you need to show a Dialog.
        The sample app is reusing them because it is useful when looking for regressions
        during testing
        */
        if (dpd == null) {
            dpd = DatePickerDialog.newInstance(
                    SignUpActivity.this,
                    now.get(Calendar.YEAR),
                    now.get(Calendar.MONTH),
                    now.get(Calendar.DAY_OF_MONTH)
            );
        } else {
            dpd.initialize(
                    SignUpActivity.this,
                    now.get(Calendar.YEAR),
                    now.get(Calendar.MONTH),
                    now.get(Calendar.DAY_OF_MONTH)
            );
        }
        dpd.dismissOnPause(true);
        dpd.show(getFragmentManager(), "Datepickerdialog");

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        DatePickerDialog dpd = (DatePickerDialog) getFragmentManager().findFragmentByTag("Datepickerdialog");
        if(dpd != null) dpd.setOnDateSetListener(this);
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = dayOfMonth+"/"+(++monthOfYear)+"/"+year;
        txt1.setText(date);
    }
}




