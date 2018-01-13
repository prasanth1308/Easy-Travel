package com.technoblaze.easytravel;

import android.view.View;

/**
 * Created by prasanth on 13/1/18.
 */

abstract class VisibleToggleClickListener implements View.OnClickListener {

    private boolean mVisible;

    @Override
    public void onClick(View v) {
        mVisible = !mVisible;
        changeVisibility(mVisible);
    }

    protected abstract void changeVisibility(boolean visible);
}
