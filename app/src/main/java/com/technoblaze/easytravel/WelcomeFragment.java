package com.technoblaze.easytravel;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.transitionseverywhere.Slide;
import com.transitionseverywhere.TransitionManager;



public class WelcomeFragment extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_welcome, container, false);

        final ViewGroup transitionsContainer = (ViewGroup) view.findViewById(R.id.transitions_container);
        final TextView text = (TextView) transitionsContainer.findViewById(R.id.text);
        final LinearLayout authLayout = view.findViewById( R.id.welcomeAuthLayout );

        final Handler waitHandler = new Handler();
        final Runnable waitCallback = new Runnable() {
            @Override
            public void run() {
                TransitionManager.beginDelayedTransition(transitionsContainer, new Slide( Gravity.BOTTOM));
                authLayout.setVisibility(View.VISIBLE);
            }
        };

        Button mEmailSignInButton = (Button) view.findViewById(R.id.welcomeAuthLoginButton);
        Button mEmailSignUpButton = (Button) view.findViewById(R.id.welcomeAuthSingupButton);
        mEmailSignInButton.setOnClickListener( this );
        mEmailSignUpButton.setOnClickListener( this );

        //Wait time 0.5s to simulate some transition.
        waitHandler.postDelayed(waitCallback, 500);

        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch(v.getId()) {
            case R.id.welcomeAuthLoginButton:
                intent = new Intent(getActivity(), LoginActivity.class);
                break;
            case R.id.welcomeAuthSingupButton:
                intent = new Intent(getActivity(), SignUpActivity.class);
                break;
            default:

        }
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);}
    }

