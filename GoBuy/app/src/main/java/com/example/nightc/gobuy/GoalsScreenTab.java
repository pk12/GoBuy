package com.example.nightc.gobuy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nightc.gobuy.GoBuySDK.Goal;
import com.example.nightc.gobuy.GoBuySDK.Item;

import org.joda.time.LocalDate;

import java.util.ArrayList;

/**
 * Created by Oppai on 7/14/2017.
 */

public class GoalsScreenTab extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View RootView = inflater.inflate(R.layout.fragment_goal_selection,container,false);

        RecyclerView rv = (RecyclerView) RootView.findViewById(R.id.GoalRecyclerView);

        rv.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(inflater.getContext());
        rv.setLayoutManager(mLayoutManager);

        GoalAdapter goalAdapter = new GoalAdapter(DemoData());
        rv.setAdapter(goalAdapter);

        return RootView;



    }

    public ArrayList<Goal> DemoData(){
        ArrayList<Goal> goals = new ArrayList<Goal>();
        Item i1 = new Item("Ipad",3000.2,"Electronics");
        Item i2 = new Item("Iphone",30400.2,"Phone");
        Goal goal = new Goal(i1,null,null,new LocalDate(),500);
        Goal g1oal = new Goal(i2,null,null,new LocalDate(),500);
        goals.add(goal);
        goals.add(g1oal);
        return goals;
    }
}
