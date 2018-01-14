package com.technoblaze.easytravel;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    // UI references.
    private AutoCompleteTextView emailTextLogin;
    private EditText passwordTextLogin;
    private MaterialDialog dialog;

    // [START declare_auth]
    private FirebaseAuth fireAuth = FirebaseAuth.getInstance();
    // [END declare_auth]


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );
        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Button mEmailSignUpButton = (Button) findViewById(R.id.button2);
        mEmailSignUpButton.setOnClickListener( this );

        // Set up the login form.
        emailTextLogin = (AutoCompleteTextView) findViewById(R.id.email);
        passwordTextLogin = (EditText) findViewById(R.id.password);

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener( this );
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = fireAuth.getCurrentUser();
        updateUI(currentUser);
    }


    private void updateUI(FirebaseUser user) {
        if (user != null) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        } else {
            View parentLayout = findViewById(android.R.id.content);
            Snackbar.make(parentLayout, " Please login to continue", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        // Reset errors.
        emailTextLogin.setError(null);
        passwordTextLogin.setError(null);

        // Store values at the time of the login attempt.
        String email = emailTextLogin.getText().toString();
        String password = passwordTextLogin.getText().toString();

        boolean cancel = false;
        View focusView = null;

        TextInputLayout emailLayout = (TextInputLayout) findViewById(R.id.email_input_layout);
        TextInputLayout passwordLayout = (TextInputLayout) findViewById(R.id.password_input_layout);

        emailLayout.setError(null);
        passwordLayout.setError(null);

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password)) {
            //mPasswordView.setError(getString(R.string.error_invalid_password));
            passwordLayout.setError(getString(R.string.password_field_required));
            focusView = passwordTextLogin;
            cancel = true;
        } else if (!isPasswordValid(password)) {
            //mEmailView.setError(getString(R.string.error_invalid_email));
            passwordLayout.setError(getString(R.string.error_invalid_password));
            focusView = passwordTextLogin;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            //mEmailView.setError(getString(R.string.error_field_required));
            emailLayout.setError(getString(R.string.email_field_required));
            focusView = emailTextLogin;
            cancel = true;
        } else if (!isEmailValid(email)) {
            //mEmailView.setError(getString(R.string.error_invalid_email));
            emailLayout.setError(getString(R.string.error_invalid_email));
            focusView = emailTextLogin;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showLoading(true);
            sendLoginRequest(email, password);
        }
    }


    private void sendLoginRequest(String email, String password){
        //authenticate user
        fireAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            showLoading(false);
                            //There was an error
                            Toast.makeText(LoginActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                        } else {
                            showLoading(false);
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
    }



    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }






    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void click(View view) {

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.button1:
                showLoading(true);
                break;
            case R.id.button2:
                showLoading(true);
                break;
            case R.id.email_sign_in_button:
                attemptLogin();
                break;
            default:

                break;

        }
    }

    public void showLoading(Boolean isStop){

        if(isStop){
            dialog =  new MaterialDialog.Builder(this)
                    .content("Loading...")
                    .progress(true, 0)
                    .show();
            dialog.setCanceledOnTouchOutside(false);
        } else {
            dialog.dismiss();
        }

    }
}
