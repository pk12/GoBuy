package com.example.nightc.gobuy.Fragments;

import android.animation.LayoutTransition;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.nightc.gobuy.Activities.Bottom_Tabs_Activity;
import com.example.nightc.gobuy.CustomAdapters.ActivityCardAdapter;
import com.example.nightc.gobuy.GoBuySDK.UserClasses.Expense;
import com.example.nightc.gobuy.GoBuySDK.UserClasses.Income;
import com.example.nightc.gobuy.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Oppai on 7/16/2017.
 */

//The fragment that will populate the today tab

public class TodayTab extends Fragment {
    private ArrayList<Income> spontaneousIncomes;
    private ArrayList<Expense> spontaeousExpenses;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.today_fragment,container,false);

        //Initialise arrayLists
        spontaneousIncomes = new ArrayList<>();
        spontaeousExpenses = new ArrayList<>();




        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.ActivityRecycler);
        RecyclerView Expenses = (RecyclerView) v.findViewById(R.id.ActivityRecyclerExpenses);
        recyclerView.setHasFixedSize(true);
        Expenses.setHasFixedSize(true);

        LinearLayoutManager LayoutManager = new LinearLayoutManager(getContext());
        LinearLayoutManager LayoutManager1 = new LinearLayoutManager(getContext());

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), LayoutManager.getOrientation());
        DividerItemDecoration dividerItemDecoration1 = new DividerItemDecoration(Expenses.getContext(), LayoutManager1.getOrientation());

        //RecyclerView 1
        recyclerView.setLayoutManager(LayoutManager);
        recyclerView.addItemDecoration(dividerItemDecoration);
        //RecyclerView 2
        Expenses.setLayoutManager(LayoutManager1);
        Expenses.addItemDecoration(dividerItemDecoration1);


        final ActivityCardAdapter activityCardAdapter = new ActivityCardAdapter(spontaneousIncomes,getContext());
        final ActivityCardAdapter expenseAdapter = new ActivityCardAdapter(spontaeousExpenses, getContext());

        Expenses.setAdapter(expenseAdapter);
        recyclerView.setAdapter(activityCardAdapter);
        LayoutTransition layoutTransition = new LayoutTransition();


        //fetch data from db and populate the RecyclerView
        DatabaseReference incomesReference = FirebaseDatabase.getInstance().getReference("Days/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/"
                + Bottom_Tabs_Activity.goalHandler.getDay().getDate().toString() + "/Incomes");
        DatabaseReference expensesReference = FirebaseDatabase.getInstance().getReference("Days/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/"
                + Bottom_Tabs_Activity.goalHandler.getDay().getDate().toString() + "/Expenses");

        //Handle Income
        incomesReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                HashMap<String, Income> hashMap;
                spontaneousIncomes.clear();
                if (dataSnapshot.exists()){
                    for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                        hashMap = (HashMap<String, Income>) snapshot.getValue();
                        Income income = new Income();
                        income.hashMapToIncome(hashMap);
                        spontaneousIncomes.add(income);

                        //Notify the Goal Handler
                        Bottom_Tabs_Activity.goalHandler.getDay().getSpontaneousIncomes().putIfAbsent(income.getName(), income);

                    }
                    activityCardAdapter.notifyDataSetChanged();
                    Toast.makeText(TodayTab.this.getContext(), "Data fetched", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        //Handle Expense
        expensesReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                HashMap<String, Expense> hashMap;
                spontaeousExpenses.clear();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        hashMap = (HashMap<String, Expense>) snapshot.getValue();
                        Expense expense = new Expense();
                        expense.hashMapToExpense(hashMap);
                        spontaeousExpenses.add(expense);

                        //Notify the Goal Handler
                        Bottom_Tabs_Activity.goalHandler.getDay().getSpontaneousExpenses().putIfAbsent(expense.getName(), expense);

                    }
                    expenseAdapter.notifyDataSetChanged();
                    Toast.makeText(TodayTab.this.getContext(), "Data fetched", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });









        return v;
    }
}
