package com.example.nightc.gobuy.Activities;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.example.nightc.gobuy.Fragments.DatePickerFragment;
import com.example.nightc.gobuy.R;

public class AddGoalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_goal);

        TextView textView = (TextView) findViewById(R.id.DateText);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_new, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //When Date text view is clicked it creates a DatePicker dialog
    public void onDateTextClick(View v){
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
}
