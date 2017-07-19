package com.example.nightc.gobuy.Fragments;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;

import com.example.nightc.gobuy.R;

/**
 * Created by Oppai on 7/16/2017.
 */

public class SettingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.account_settings);

    }
}
