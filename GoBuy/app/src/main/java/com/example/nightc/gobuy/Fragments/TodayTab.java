package com.example.nightc.gobuy.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nightc.gobuy.ActivityCardAdapter;
import com.example.nightc.gobuy.GoBuySDK.UserClasses.SpontaneousIncome;
import com.example.nightc.gobuy.R;

import java.util.ArrayList;

/**
 * Created by Oppai on 7/16/2017.
 */

//The fragment that will populate the today tab

public class TodayTab extends Fragment {
    private ArrayList<SpontaneousIncome> spontaneousIncomes;

    public TodayTab(ArrayList<SpontaneousIncome> spontaneousIncomes) {
        this.spontaneousIncomes = spontaneousIncomes ;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.today_fragment,container,false);

//        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.ActivityRecycler);
//        recyclerView.setHasFixedSize(true);
//        GridAutofitLayoutManager layoutManager = new GridAutofitLayoutManager(getContext(),400);
//        ActivityCardAdapter activityCardAdapter = new ActivityCardAdapter(spontaneousIncomes);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(activityCardAdapter);


        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.ActivityRecycler);
        recyclerView.setHasFixedSize(true);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        ActivityCardAdapter activityCardAdapter = new ActivityCardAdapter(spontaneousIncomes);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(activityCardAdapter);





        return v;
    }
}
