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
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.nightc.gobuy.Activities.AddGoalActivity;
import com.example.nightc.gobuy.Activities.NewIncome_ExpenseActivity;
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
        ToolbarProgess = (ProgressBar) v.findViewById(R.id.ProgressToolbar);
        SaveBar = (TextView) v.findViewById(R.id.SaveText);
        SaveAmountBar = (TextView) v.findViewById(R.id.SaveAmountText);
        SpendBar = (TextView) v.findViewById(R.id.SpendText);
        SpendAmountBar = (TextView) v.findViewById(R.id.SpendAmountText);
        DateBar = (TextView) v.findViewById(R.id.textDate);
        //Hide Views when Goals tab is inflated
        SaveAmountBar.setVisibility(View.GONE);
        SpendBar.setVisibility(View.GONE);
        SaveBar.setVisibility(View.GONE);
        SpendAmountBar.setVisibility(View.GONE);
        ToolbarProgess.setVisibility(View.GONE);
        DateBar.setVisibility(View.GONE);



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
                    View v = getActivity().getLayoutInflater().inflate(R.layout.fragment_ask_income_expense,null);
                    AlertDialog.Builder aBuilder = new AlertDialog.Builder(getActivity());

                    aBuilder.setView(v);
                    final AlertDialog alertDialog = aBuilder.create();
                    alertDialog.show();

                    Button Expense = (Button) v.findViewById(R.id.ExpenseButton);
                    Button Income = (Button) v.findViewById(R.id.IncomeButton);

                    View.OnClickListener listener = new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getContext(),NewIncome_ExpenseActivity.class);
                            if (v.getId() == R.id.ExpenseButton)
                                intent.putExtra("Type","Expense");
                            else
                                intent.putExtra("Type","Income");

                            startActivity(intent);
                            alertDialog.dismiss();

                        }
                    };

                    Expense.setOnClickListener(listener);
                    Income.setOnClickListener(listener);


                }
            }
        });



        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == 1){
                    ToolbarProgess.setVisibility(View.VISIBLE);
                    DateBar.setVisibility(View.VISIBLE);
                    SaveAmountBar.setVisibility(View.VISIBLE);
                    SpendBar.setVisibility(View.VISIBLE);
                    SaveBar.setVisibility(View.VISIBLE);
                    SpendAmountBar.setVisibility(View.VISIBLE);
                    ToolbarProgess.setVisibility(View.VISIBLE);
                    DateBar.setVisibility(View.VISIBLE);
                    DateBar.setText(new LocalDate().toString("dd-MMM-yy"));
                }
                else {
                    ToolbarProgess.setVisibility(View.GONE);
                    DateBar.setVisibility(View.GONE);
                    SaveAmountBar.setVisibility(View.GONE);
                    SpendBar.setVisibility(View.GONE);
                    SaveBar.setVisibility(View.GONE);
                    SpendAmountBar.setVisibility(View.GONE);
                    ToolbarProgess.setVisibility(View.GONE);
                    DateBar.setVisibility(View.GONE);
                }

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
                    goalsScreenTab = new GoalsScreenTab();
                    ToolbarProgess.setVisibility(View.GONE);
                    DateBar.setVisibility(View.GONE);
                    return goalsScreenTab;
                default:
                    todayTab = new TodayTab(DemoIncomes());
                    ToolbarProgess.setVisibility(View.VISIBLE);
                    DateBar.setVisibility(View.VISIBLE);
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





    public ArrayList<SpontaneousIncome> DemoIncomes(){
        SpontaeousExpense expense = new SpontaeousExpense("Coffee",4,1,1);
        SpontaeousExpense expense1 = new SpontaeousExpense("Coffee",4,1,1);
        SpontaneousIncome income = new SpontaneousIncome("Mom",6,1,1);
        SpontaneousIncome income1 = new SpontaneousIncome("Dad",6,1,1);
        SpontaneousIncome income2 = new SpontaneousIncome("You",6,1,1);
        SpontaneousIncome income3 = new SpontaneousIncome("Id",6,1,1);
        SpontaneousIncome income4 = new SpontaneousIncome("Loe",6,1,1);

        ArrayList<SpontaneousIncome> incomes = new ArrayList<SpontaneousIncome>();
        incomes.add(income);
        incomes.add(income1);
        incomes.add(income2);
        incomes.add(income3);
        incomes.add(income4);
        return incomes;
    }

}

