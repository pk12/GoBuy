package com.example.nightc.gobuy.Activities;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nightc.gobuy.DBHandler;
import com.example.nightc.gobuy.Fragments.DatePickerFragment;
import com.example.nightc.gobuy.GoBuySDK.BillsAPI;
import com.example.nightc.gobuy.GoBuySDK.Goal;
import com.example.nightc.gobuy.GoBuySDK.IncomesAPI;
import com.example.nightc.gobuy.GoBuySDK.Item;
import com.example.nightc.gobuy.GoBuySDK.UserClasses.UserData;
import com.example.nightc.gobuy.R;

import org.joda.time.LocalDate;

public class AddGoalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_goal);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_new, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //find the views
        TextView dateWanted = (TextView) findViewById(R.id.DateText);
        EditText Name = (EditText) findViewById(R.id.ItemNameText);
        EditText Price = (EditText) findViewById(R.id.ItemPriceText);

        switch (item.getItemId()){
            case R.id.action_save:
                //check if every line is filled
                //create new goal arguments
                //Create UserData object from DB
                DBHandler dbHandler = new DBHandler(getApplicationContext());
                UserData userData = dbHandler.getUserData(1); //should pass the UserID as an argument i think
                Item Gitem = new Item(1,1,Name.getText().toString(),"nothing",Double.parseDouble(Price.getText().toString()));
                IncomesAPI incomesAPI = new IncomesAPI(userData.CalculateIncomeAmount(),0);
                BillsAPI billsAPI = new BillsAPI(userData.CalculateBillAmount());
                Goal goal = new Goal(Gitem,incomesAPI,billsAPI,new LocalDate(dateWanted.getText().toString()),100,1,1);

                //Save goal to DB
                dbHandler.addGoal(goal);
        }

        return true;
    }

    //When Date text view is clicked it creates a DatePicker dialog
    public void onDateTextClick(View v){
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
}
