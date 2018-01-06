package com.technoblaze.easytravel;

import android.content.Intent;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TypefaceSpan;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.SimpleSlide;

public class AppIntroActivity extends IntroActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setFullscreen(true);
        super.onCreate(savedInstanceState);

        setButtonBackVisible(true);
        setButtonNextVisible(true);
        setButtonCtaVisible(false);
        setButtonCtaTintMode(BUTTON_CTA_TINT_MODE_BACKGROUND);
        TypefaceSpan labelSpan = new TypefaceSpan(
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ? "sans-serif-medium" : "sans serif");
        SpannableString label = SpannableString
                .valueOf(getString(R.string.label_button_cta_canteen_intro));
        label.setSpan(labelSpan, 0, label.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        setButtonCtaLabel(label);

        setPageScrollDuration(500);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setPageScrollInterpolator(android.R.interpolator.fast_out_slow_in);
        }

        addSlide(new SimpleSlide.Builder()
                .title(R.string.title_canteen_intro1)
                .description(R.string.description_canteen_intro1)
                .image(R.drawable.art_canteen_intro1)
                .background(R.color.color_canteen)
                .backgroundDark(R.color.color_dark_canteen)
                .layout(R.layout.activity_app_intro)
                .build());

        addSlide(new SimpleSlide.Builder()
                .title(R.string.title_canteen_intro2)
                .description(R.string.description_canteen_intro2)
                .image(R.drawable.art_canteen_intro2)
                .background(R.color.color_canteen)
                .backgroundDark(R.color.color_dark_canteen)
                .layout(R.layout.activity_app_intro)
                .build());

        addSlide(new SimpleSlide.Builder()
                .title(R.string.title_canteen_intro3)
                .description(R.string.description_canteen_intro3)
                .image(R.drawable.art_canteen_intro3)
                .background(R.color.color_canteen)
                .backgroundDark(R.color.color_dark_canteen)
                .layout(R.layout.activity_app_intro)
                .build());

        autoplay(2500, INFINITE);

        setButtonCtaClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });


    }


}
