package com.example.nightc.gobuy.Fragments;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nightc.gobuy.CustomAdapters.GoalCardsAdapter;
import com.example.nightc.gobuy.DBHandler;
import com.example.nightc.gobuy.GoBuySDK.Goal;
import com.example.nightc.gobuy.R;

import java.util.ArrayList;

/**
 * Created by Oppai on 7/14/2017.
 */

public class GoalsScreenTab extends Fragment {



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        CreateDB();
        View RootView = inflater.inflate(R.layout.fragment_goal_selection,container,false);

        RecyclerView rv = (RecyclerView) RootView.findViewById(R.id.GoalRecyclerView);

        rv.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(inflater.getContext());
        rv.setLayoutManager(mLayoutManager);

        GoalCardsAdapter goalCardsAdapter = new GoalCardsAdapter(DemoData());
        rv.setAdapter(goalCardsAdapter);

        return RootView;



    }

    //MUST CREATE ASYNC TASK TO LOAD THE DATA FROM THE DB
    public ArrayList<Goal> DemoData(){
        ArrayList<Goal> goals = new ArrayList<Goal>();
        DBHandler dbHandler = new DBHandler(getContext());
        Goal goal = dbHandler.getGoal(1);
        if (goal!= null)
            goals.add(goal); //NullPointer exception if db is empty

//        Item i1 = new Item(1,1,"Ipad","Electronics",3000.2);
//        Item i2 = new Item(1,1,"Iphone","Phone",30400.2);
//        Goal goal = new Goal(i1,null,null,new LocalDate(),500,1,1);
//        Goal g1oal = new Goal(i2,null,null,new LocalDate(),500,1,1);
//        goals.add(goal);
//        goals.add(g1oal);
        return goals;
    }

    //Open DB Method
    public SQLiteDatabase CreateDB(){
        SQLiteDatabase database = getContext().openOrCreateDatabase("GobuyDB", Context.MODE_PRIVATE,null);
        return database;
    }

}
