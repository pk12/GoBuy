package com.example.nightc.gobuy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

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
            View rootView;
            if (getArguments().getInt(ARG_SECTION_NUMBER) == 1){
                 rootView = inflater.inflate(R.layout.fragment_sign_up, container, false);
                ImageView v = (ImageView) rootView.findViewById(R.id.imageView);

            }
            else if (getArguments().getInt(ARG_SECTION_NUMBER) == 2){
                 rootView = inflater.inflate(R.layout.add_job, container, false);
            }
            else if (getArguments().getInt(ARG_SECTION_NUMBER) == 3){
                 rootView = inflater.inflate(R.layout.add_other_incomes, container, false);
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
            // Show 4 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:

                    return "SECTION 1";
                case 1:

                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
                case 4:
                    return "SECTION 4";
            }
            return null;
        }
    }

    //On signUp screen makes the layout change when pressing the next button
    public void OnNextSelected(View view){
        final ViewPager pager = (ViewPager) findViewById(R.id.SignUpPager);

        //if the user wants to add other incomes it will navigate him to other incomes screen
        //if he doesn't want to then it will navigate to Expense screen
        if (pager.getCurrentItem() == 1){

            View v = getLayoutInflater().inflate(R.layout.fragment_ask_other_income,null);
            AlertDialog.Builder aBuilder = new AlertDialog.Builder(this);

            aBuilder.setView(v);
            final AlertDialog alertDialog = aBuilder.create();
            alertDialog.show();

            Button yes = (Button) v.findViewById(R.id.YesButton);
            Button no = (Button) v.findViewById(R.id.NoButton);



            yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pager.setCurrentItem(pager.getCurrentItem() + 1,true);
                    alertDialog.dismiss();
                }
            });

            no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pager.setCurrentItem(pager.getCurrentItem() + 2,true);
                    alertDialog.dismiss();
                }
            });

        }else{
            pager.setCurrentItem(pager.getCurrentItem() + 1,true);
        }
    }
}
