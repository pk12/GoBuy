package com.example.nightc.gobuy.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nightc.gobuy.GoBuySDK.Goal;
import com.example.nightc.gobuy.GoalCardsAdapter;
import com.example.nightc.gobuy.R;

import java.util.ArrayList;

/**
 * Created by Oppai on 7/14/2017.
 */

public class GoalsScreenTab extends Fragment {

    private ArrayList<Goal> goals;

    public GoalsScreenTab(ArrayList<Goal> goals) {
        this.goals = goals;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View RootView = inflater.inflate(R.layout.fragment_goal_selection,container,false);

        RecyclerView rv = (RecyclerView) RootView.findViewById(R.id.GoalRecyclerView);

        rv.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(inflater.getContext());
        rv.setLayoutManager(mLayoutManager);

        GoalCardsAdapter goalCardsAdapter = new GoalCardsAdapter(goals);
        rv.setAdapter(goalCardsAdapter);

        return RootView;



    }

}
