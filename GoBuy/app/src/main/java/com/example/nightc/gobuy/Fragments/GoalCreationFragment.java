package com.example.nightc.gobuy.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nightc.gobuy.R;

/**
 * Created by Oppai on 7/19/2017.
 */

public class GoalCreationFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_goal,container,false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //handle fragment of tab 1
        //NOTE may create a new class extending fragment for it
        //getData from the Fragment Views when Done Button is pressed
        //and create the new goal class
        //send it to the UserData to be saved Database
        //and finally create the new activity with goals
    }
}
