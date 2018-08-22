package com.example.nightc.gobuy.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nightc.gobuy.GoBuySDK.UserClasses.ActiveGoalHandler;
import com.example.nightc.gobuy.GoBuySDK.UserClasses.Expense;
import com.example.nightc.gobuy.GoBuySDK.UserClasses.Income;
import com.example.nightc.gobuy.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewIncome_ExpenseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_income__expense);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_new, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Spinner spinner = (Spinner) findViewById(R.id.NewIncome_ExpenseSpinner);
        EditText amount = (EditText) findViewById(R.id.SpontaneousAmount);
        EditText description = (EditText) findViewById(R.id.Description);

        switch (item.getItemId()){
            case R.id.action_save:

                if (spinner.getSelectedItem().toString().equals("Select") || amount.getText().toString().trim().equals("") || description.getText().toString().trim().equals("")){
                    Toast.makeText(this, "Please complete the required fields", Toast.LENGTH_SHORT).show();
                    return false;
                }
                else {
                    // Send data to the DB Under Days/UID/Day
                    //We have the goalHandler so the day will be in there, so it is goal independent
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Days/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/" +
                            Bottom_Tabs_Activity.goalHandler.getDay().getDate().toString());

                    ActiveGoalHandler handler = Bottom_Tabs_Activity.goalHandler;
                    //add the amount in the correct list

                    // Check if the income/expense already exists
                    //Solution instead of arraylist use hashmap<Description, Object> and putifabsent
                    if (spinner.getSelectedItem().toString().equals("Income"))
                        if (handler.getDay().getSpontaneousIncomes().putIfAbsent(description.getText().toString() ,new Income(description.getText().toString(), Double.parseDouble(amount.getText().toString()))) != null)
                            Toast.makeText(this, "Income already exists", Toast.LENGTH_SHORT).show();
                        else
                            this.finish();
                    else if (spinner.getSelectedItem().toString().equals("Expense"))
                        if (handler.getDay().getSpontaneousExpenses().putIfAbsent(description.getText().toString() ,new Expense(description.getText().toString(), Double.parseDouble(amount.getText().toString()))) != null)
                            Toast.makeText(this, "Expense already exists", Toast.LENGTH_SHORT).show();
                        else
                            this.finish();

                    //Upload the day with the new data
                    reference.setValue(handler.getDay().toHashMap());
                }

        }

        return true;
    }
}
