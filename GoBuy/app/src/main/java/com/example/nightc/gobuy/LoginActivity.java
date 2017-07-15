package com.example.nightc.gobuy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginlayout2);


        Button SignUp = (Button) findViewById(R.id.NextButton);



    }

    public void onSignUp(View view){
        Intent i = new Intent(this,SignUp.class);
        startActivity(i);
    }

    public void OnLogin(View view){
        //check credentials
        //...

        //Start Logged in activity
        Intent i = new Intent(this,GoalSelectionActivity.class);
        startActivity(i);
    }

}
