package com.example.nightc.gobuy.Activities;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nightc.gobuy.Fragments.DatePickerFragment;
import com.example.nightc.gobuy.GoBuySDK.Goal;
import com.example.nightc.gobuy.GoBuySDK.Item;
import com.example.nightc.gobuy.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
        final TextView dateWanted = (TextView) findViewById(R.id.DateText);
        final EditText Name = (EditText) findViewById(R.id.ItemNameText);
        final EditText Price = (EditText) findViewById(R.id.ItemPriceText);

        switch (item.getItemId()){
            case R.id.action_save:

                if (!dateWanted.getText().toString().trim().equals("Select Date") && !Name.getText().toString().trim().equals("") && !Price.getText().toString().trim().equals("")){
                    final FirebaseUser User = FirebaseAuth.getInstance().getCurrentUser();
                    final DatabaseReference[] reference = {FirebaseDatabase.getInstance().getReference()};
                    DatabaseReference GoalNo = FirebaseDatabase.getInstance().getReference();
                    final long[] GoalNumber = new long[1];




                    //Adds the data to the reference if there is no Goal ID like this
                    GoalNo = GoalNo.child("Goals").child(User.getUid()).child("GoalNumber"); //needed here to be able to use GoalNo in the Listener
                    final DatabaseReference finalGoalNo = GoalNo; //Temp final variable to use the GoalNo in the Listener
                    final ValueEventListener valueEventListener = new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (!dataSnapshot.exists()){
                                Item item = new Item(User.getUid().toString(),Name.getText().toString().trim(),"Something", Double.parseDouble(Price.getText().toString().trim()));
                                Goal goal = new Goal(item,2,2, new LocalDate(dateWanted.getText().toString()),200);
                                reference[0].setValue(goal.toHashMap());
                                finalGoalNo.setValue(GoalNumber[0] + 1);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    }; //Creates the listener but doesnt add it on the reference yet because we need this to run after the fetch of GoalNo

                    //Get the Value of the total goal number of the Current User
                    GoalNo.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()){
                                Log.d("DATA VALUE", String.valueOf(dataSnapshot.getValue()));
                                GoalNumber[0] = (long) dataSnapshot.getValue();
                                reference[0] = reference[0].child("Goals").child(User.getUid()).child(String.valueOf(GoalNumber[0] + 1));
                                reference[0].addListenerForSingleValueEvent(valueEventListener);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                    this.finish();

                }
                else
                    return false;


        }

        return true;
    }

    //When Date text view is clicked it creates a DatePicker dialog
    public void onDateTextClick(View v){
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
}
