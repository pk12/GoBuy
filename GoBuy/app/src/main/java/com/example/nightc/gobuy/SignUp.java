package com.example.nightc.gobuy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nightc.gobuy.Activities.Bottom_Tabs_Activity;
import com.example.nightc.gobuy.GoBuySDK.UserClasses.StableExpense;
import com.example.nightc.gobuy.GoBuySDK.UserClasses.StableIncome;
import com.example.nightc.gobuy.GoBuySDK.UserClasses.User;
import com.example.nightc.gobuy.GoBuySDK.UserClasses.UserData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SignUp extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private static final HashMap<String, StableIncome> SteadyIncomes = new HashMap();
    private static HashMap<String, StableExpense> SteadyExpenses = new HashMap();
    private static double totalDailyIncome = 0;
    private static double totalDailyExpense = 0;


    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private NonSwipeableViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (NonSwipeableViewPager) findViewById(R.id.SignUpPager);
        mViewPager.setAdapter(mSectionsPagerAdapter);





    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_up, menu);
        return true;
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
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        //HERE WE SHALL LOCATE THE CONTENTS OF EACH LAYOUT AND HANDLE THE Information inserted NOTE****WE CAN CREATE DIFFERENT CLASSES EXTENDING FRAGMENT TO HANDLE EACH LAYOUT
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            //Initialise all the variables that are going to be used in every clause to be able to use them on the final next
            final Spinner Type;
            final Spinner PayPeriod;
            final EditText Amount;
            final EditText OtherType;
            Button Add;
            final View rootView;

            if (getArguments().getInt(ARG_SECTION_NUMBER) == 1){
                 rootView = inflater.inflate(R.layout.add_other_incomes, container, false);
                 Type = (Spinner) rootView.findViewById(R.id.IncomeTypeSpinner);
                 PayPeriod = (Spinner) rootView.findViewById(R.id.PayPeriodSpinner);
                 Amount = (EditText) rootView.findViewById(R.id.Income_AmountEditText);
                 Add = (Button) rootView.findViewById(R.id.AddIncomeButton);

                 Add.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         if (!Amount.getText().toString().trim().equals("")){
                             //Create Income
                             StableIncome stableIncome = new StableIncome(Type.getSelectedItem().toString(), Double.parseDouble(Amount.getText().toString().trim()),
                                     PayPeriod.getSelectedItem().toString());
                             //Add the amount to the variable to use on firebase
                             //Convert totalDailyIncome to Daily
                             String payPeriod = PayPeriod.getSelectedItem().toString();
                             if (payPeriod.equals("Annual")){
                                 totalDailyIncome += Math.round(stableIncome.getAmount() / 365);
                             }
                             else if (payPeriod.equals("Monthly")){
                                 totalDailyIncome += Math.round(stableIncome.getAmount() / 30);
                             }
                             else {
                                 totalDailyIncome += stableIncome.getAmount();
                             }

                             SteadyIncomes.put(String.valueOf(SteadyIncomes.size() + 1), stableIncome);
                             Toast.makeText(rootView.getContext(), "Income successfully added", Toast.LENGTH_SHORT).show();

                         }
                         else
                             Toast.makeText(rootView.getContext(), "Please enter the amount", Toast.LENGTH_SHORT).show();
                     }
                 });

            }
            else if (getArguments().getInt(ARG_SECTION_NUMBER) == 2){
                rootView = inflater.inflate(R.layout.add_expenses, container, false);
                Type = (Spinner) rootView.findViewById(R.id.ExpenseType);
                PayPeriod = (Spinner) rootView.findViewById(R.id.PayPeriodSpinner);
                Amount = (EditText) rootView.findViewById(R.id.ExpenseAmount);
                ImageButton Add1 = (ImageButton) rootView.findViewById(R.id.AddExpenseButton);
                OtherType = (EditText) rootView.findViewById(R.id.OtherType);

                //if the type is Other then the user must enter the Type
                //else the user must only enter the amount
                Add1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (Type.getSelectedItem().toString().equals("Other")){
                            if (!Amount.getText().toString().trim().equals("") && !OtherType.getText().toString().trim().equals("")){
                                StableExpense stableExpense = new StableExpense(OtherType.getText().toString(), Double.parseDouble(Amount.getText().toString()), PayPeriod.getSelectedItem().toString());
                                SteadyExpenses.put(String.valueOf(SteadyExpenses.size() + 1), stableExpense);

                                //Convert amount to Daily
                                String payPeriod = PayPeriod.getSelectedItem().toString();
                                if (payPeriod.equals("Annual")){
                                    totalDailyExpense += Math.round(stableExpense.getAmount() / 365);
                                }
                                else if (payPeriod.equals("Monthly")){
                                    totalDailyExpense += Math.round(stableExpense.getAmount() / 30);
                                }
                                else {
                                    totalDailyExpense += stableExpense.getAmount();
                                }

                                Toast.makeText(rootView.getContext(), "Expense successfully added", Toast.LENGTH_SHORT).show();
                            }
                            else
                                Toast.makeText(rootView.getContext(), "Please complete all the required fields", Toast.LENGTH_SHORT).show();
                        }
                        else if (!Amount.getText().toString().trim().equals("")){
                            StableExpense stableExpense = new StableExpense(Type.getSelectedItem().toString(), Double.parseDouble(Amount.getText().toString()), PayPeriod.getSelectedItem().toString());
                            SteadyExpenses.put(String .valueOf(SteadyExpenses.size() + 1), stableExpense);

                            //Convert to Daily
                            String payPeriod = PayPeriod.getSelectedItem().toString();
                            if (payPeriod.equals("Annual")){
                                totalDailyExpense += Math.round(stableExpense.getAmount() / 365);
                            }
                            else if (payPeriod.equals("Monthly")){
                                totalDailyExpense += Math.round(stableExpense.getAmount() / 30);
                            }
                            else {
                                totalDailyExpense += stableExpense.getAmount();
                            }

                            Toast.makeText(rootView.getContext(), "Expense successfully added", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(rootView.getContext(), "Please complete all the required fields", Toast.LENGTH_SHORT).show();
                    }
                });

            }
            else {
                rootView = inflater.inflate(R.layout.add_expenses, container, false);
            }
//            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
//            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }


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
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            //If i used a toolbar these would be each tab's name
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }

    //On signUp screen makes the layout change when pressing the next button

    public void OnNextSelected(View view){
        final ViewPager pager = (ViewPager) findViewById(R.id.SignUpPager);
        if (pager.getCurrentItem() == 0){
            if (SteadyIncomes.isEmpty()){
                Toast.makeText(this, "You have not entered any incomes", Toast.LENGTH_SHORT).show();
            }
            else
                pager.setCurrentItem(pager.getCurrentItem() + 1);

        }
        else if (pager.getCurrentItem() == 1){
            if (SteadyExpenses.isEmpty()){
                Toast.makeText(this, "You have not entered any expenses", Toast.LENGTH_SHORT).show();
            }
            else {

                //Initialize variables
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
                FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                UserData userData = new UserData("N/A", 11, "N/A", null, SteadyIncomes, SteadyExpenses, totalDailyIncome, totalDailyExpense);
                User user = new User(null, userData, firebaseUser.getUid().toString(), false);

                //Add the user data into Firebase DB
                reference = reference.child("Users").child(firebaseUser.getUid());
                reference.setValue(user.toHashMap());



                //Initialize User Goals DB section
                reference = FirebaseDatabase.getInstance().getReference("Goals").child(firebaseUser.getUid()).child("GoalNumber");
                reference.setValue(0);


                //Start the LoggedIn Activity
                Intent intent = new Intent(SignUp.this, Bottom_Tabs_Activity.class);
                startActivity(intent);
                this.finish();
            }
        }

        // May not use the job field after all
        //if the user wants to add other incomes it will navigate him to other incomes screen
        //if he doesn't want to then it will navigate to Expense screen
//        if (pager.getCurrentItem() == 0){
//
//            View v = getLayoutInflater().inflate(R.layout.fragment_ask_other_income,null);
//            AlertDialog.Builder aBuilder = new AlertDialog.Builder(this);
//
//            aBuilder.setView(v);
//            final AlertDialog alertDialog = aBuilder.create();
//            alertDialog.show();
//
//            Button yes = (Button) v.findViewById(R.id.YesButton);
//            Button no = (Button) v.findViewById(R.id.NoButton);
//
//
//
//            yes.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    pager.setCurrentItem(pager.getCurrentItem() + 1,true);
//                    alertDialog.dismiss();
//                }
//            });
//
//            no.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    pager.setCurrentItem(pager.getCurrentItem() + 2,true);
//                    alertDialog.dismiss();
//                }
//            });
//
//        }else{
//            pager.setCurrentItem(pager.getCurrentItem() + 1,true);
//        }
    }
}
