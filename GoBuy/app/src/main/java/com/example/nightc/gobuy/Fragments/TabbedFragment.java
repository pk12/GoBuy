package com.example.nightc.gobuy.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.nightc.gobuy.Activities.AddGoalActivity;
import com.example.nightc.gobuy.GoBuySDK.Goal;
import com.example.nightc.gobuy.GoBuySDK.Item;
import com.example.nightc.gobuy.GoBuySDK.UserClasses.SpontaeousExpense;
import com.example.nightc.gobuy.GoBuySDK.UserClasses.SpontaneousIncome;
import com.example.nightc.gobuy.R;

import org.joda.time.LocalDate;

import java.util.ArrayList;

public class TabbedFragment extends Fragment {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    private GoalsScreenTab goalsScreenTab;
    private TodayTab todayTab;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_goal_selection,container,false);
        Toolbar toolbar = (Toolbar) v.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter( getChildFragmentManager());  // Key to recreate view pager content on BottomTabNavigation fragment changing
                                                                                        //use childFragmentManager because the GoalSelection is a fragment,not an activity

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) v.findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) v.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mViewPager.getCurrentItem() == 0){
                    Intent intent = new Intent(TabbedFragment.this.getContext(), AddGoalActivity.class);
                    startActivity(intent);
                }
                else if (mViewPager.getCurrentItem() == 1){
                    Snackbar.make(view,"Today Showing",Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        return v;

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.menu_goal_selection, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    goalsScreenTab = new GoalsScreenTab(DemoData());
                    return goalsScreenTab;
                default:
                    todayTab = new TodayTab(DemoIncomes());
                    return todayTab;
            }
        }


        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Goals";
                case 1:
                    return "Today";
            }
            return null;
        }
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

    public ArrayList<SpontaneousIncome> DemoIncomes(){
        SpontaeousExpense expense = new SpontaeousExpense("Coffee",4);
        SpontaeousExpense expense1 = new SpontaeousExpense("Coffee",4);
        SpontaneousIncome income = new SpontaneousIncome("Mom",6);
        SpontaneousIncome income1 = new SpontaneousIncome("Dad",6);
        SpontaneousIncome income2 = new SpontaneousIncome("You",6);
        SpontaneousIncome income3 = new SpontaneousIncome("Id",6);
        SpontaneousIncome income4 = new SpontaneousIncome("Loe",6);

        ArrayList<SpontaneousIncome> incomes = new ArrayList<SpontaneousIncome>();
        incomes.add(income);
        incomes.add(income1);
        incomes.add(income2);
        incomes.add(income3);
        incomes.add(income4);
        return incomes;
    }
}
