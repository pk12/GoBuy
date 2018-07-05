package com.example.nightc.gobuy.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.nightc.gobuy.CustomAdapters.GoalCardsAdapter;
import com.example.nightc.gobuy.GoBuySDK.Goal;
import com.example.nightc.gobuy.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Oppai on 7/14/2017.
 */

public class GoalsScreenTab extends Fragment {
    private ArrayList<Goal> goals = new ArrayList<Goal>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View RootView = inflater.inflate(R.layout.fragment_goal_selection,container,false);

        RecyclerView rv = (RecyclerView) RootView.findViewById(R.id.GoalRecyclerView);

        rv.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(inflater.getContext());
        rv.setLayoutManager(mLayoutManager);

        GoalCardsAdapter goalCardsAdapter = new GoalCardsAdapter(goals);
        FetchData(goalCardsAdapter);
        rv.setAdapter(goalCardsAdapter);

        return RootView;



    }

    //MUST CREATE ASYNC TASK TO LOAD THE DATA FROM THE DB
    public ArrayList<Goal> FetchData(final GoalCardsAdapter adapter){


        FirebaseUser User = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference = reference.child("Goals").child(User.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                HashMap hashMap;
                goals.clear();
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    if (!snapshot.getKey().toString().equals("GoalNumber")){
                        hashMap = (HashMap) snapshot.getValue();
                        Goal goal = new Goal();
                        goal.HashMapToGoal(hashMap);
                        goals.add(goal);
                    }

                }
                adapter.notifyDataSetChanged();
                Toast.makeText(GoalsScreenTab.this.getContext(), "Data successfully fetched", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return goals;
    }


}
