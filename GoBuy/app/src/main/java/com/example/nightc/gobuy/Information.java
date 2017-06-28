package com.example.nightc.gobuy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.nightc.gobuy.GoBuySDK.Bill;
import com.example.nightc.gobuy.GoBuySDK.Goal;
import com.example.nightc.gobuy.GoBuySDK.Income;
import com.example.nightc.gobuy.GoBuySDK.Item;

import org.joda.time.LocalDate;

import java.util.ArrayList;

import static com.example.nightc.gobuy.R.id.BillAmount;
import static com.example.nightc.gobuy.R.id.IncomeAmount;
import static com.example.nightc.gobuy.R.id.MoneySaved;

public class Information extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parametersadd);

        //code starts
        final EditText t1 = (EditText) findViewById(R.id.DateWanted);
        final EditText t2 = (EditText) findViewById(MoneySaved);
        final EditText t3 = (EditText) findViewById(R.id.ItemName);
        final EditText t4 = (EditText) findViewById(R.id.ItemPrice);
        final EditText t5 = (EditText) findViewById(IncomeAmount);
        final EditText t6 = (EditText) findViewById(R.id.IncomeName);
        final EditText t7 = (EditText) findViewById(R.id.BillName);
        final EditText t8 = (EditText) findViewById(BillAmount);



        Button b = (Button) findViewById(R.id.button3);
        b.setOnClickListener(new View.OnClickListener() {




            @Override
            public void onClick(View view) {
                final LocalDate Datewanted = new LocalDate(t1.getText().toString()); //must convert the string to xx-xx-xxxx
                final double MoneySaved = Double.parseDouble(t2.getText().toString());
                final String itemName = t3.getText().toString();
                final double itemPrice = Double.parseDouble(t4.getText().toString());
                final double IncomeAmount = Double.parseDouble(t5.getText().toString());
                final double BillAmount = Double.parseDouble(t8.getText().toString());
                final String IncomeName = t6.getText().toString();
                final String BillName = t7.getText().toString();



                //Creating arguments
                Item i = new Item(itemName,itemPrice,null);
                ArrayList<Income> in = new ArrayList<Income>();
                in.add(new Income(IncomeAmount,IncomeName,0));
                ArrayList<Bill> bill = new ArrayList<Bill>();
                bill.add(new Bill(BillName,BillAmount));

                Goal goal = new Goal(i,in,bill,Datewanted,MoneySaved);
            }
        });

    }
}
