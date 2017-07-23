package com.example.nightc.gobuy.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nightc.gobuy.CustomAdapters.ActivityCardAdapter;
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


        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.ActivityRecycler);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),3);
        ActivityCardAdapter activityCardAdapter = new ActivityCardAdapter(spontaneousIncomes,getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(activityCardAdapter);







        return v;
    }
}
