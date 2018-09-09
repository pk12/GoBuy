package com.example.nightc.gobuy.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.nightc.gobuy.Activities.AddGoalActivity;
import com.example.nightc.gobuy.Activities.NewIncome_ExpenseActivity;
import com.example.nightc.gobuy.R;

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

    //the Views to hide when Scrolling tabs
    private ProgressBar ToolbarProgess;
    private TextView DateBar;
    private TextView SaveBar;
    private TextView SaveAmountBar;
    private TextView SpendBar;
    private TextView SpendAmountBar;


    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tabbed_fragment,container,false);



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
            //When the plus button is clicked
            public void onClick(View view) {
                //if Goals Screen Active then add new goal activity starts
                if (mViewPager.getCurrentItem() == 0){
                    Intent intent = new Intent(TabbedFragment.this.getContext(), AddGoalActivity.class);
                    startActivity(intent);
                }

                //if Today Tab active then asks for new income or expense and begins add new Income_Expense activity
                //May change to In Activity Selection
                else if (mViewPager.getCurrentItem() == 1){

                    Intent i = new Intent(TabbedFragment.this.getActivity(), NewIncome_ExpenseActivity.class);
                    startActivity(i);


                }
            }
        });



        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                if (position == 3){
//                    ToolbarProgess.setVisibility(View.VISIBLE);
//                    DateBar.setVisibility(View.VISIBLE);
//                    SaveAmountBar.setVisibility(View.VISIBLE);
//                    SpendBar.setVisibility(View.VISIBLE);
//                    SaveBar.setVisibility(View.VISIBLE);
//                    SpendAmountBar.setVisibility(View.VISIBLE);
//                    ToolbarProgess.setVisibility(View.VISIBLE);
//                    DateBar.setVisibility(View.VISIBLE);
//                    DateBar.setText(new LocalDate().toString("dd-MMM-yy"));
//                }
//                else {
//                    ToolbarProgess.setVisibility(View.INVISIBLE);
//                    DateBar.setVisibility(View.INVISIBLE);
//                    SaveAmountBar.setVisibility(View.INVISIBLE);
//                    SpendBar.setVisibility(View.INVISIBLE);
//                    SaveBar.setVisibility(View.INVISIBLE);
//                    SpendAmountBar.setVisibility(View.INVISIBLE);
//                    ToolbarProgess.setVisibility(View.INVISIBLE);
//                    DateBar.setVisibility(View.INVISIBLE);
//                }
//
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        return v;

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
                    goalsScreenTab = new GoalsScreenTab();
//                    ToolbarProgess.setVisibility(View.GONE);
//                    DateBar.setVisibility(View.GONE);
                    return goalsScreenTab;
                default:
                    todayTab = new TodayTab();
//                    ToolbarProgess.setVisibility(View.VISIBLE);
//                    DateBar.setVisibility(View.VISIBLE);
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


}

