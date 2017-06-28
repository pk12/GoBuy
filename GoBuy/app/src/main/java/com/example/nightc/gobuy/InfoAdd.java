package com.example.nightc.gobuy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class InfoAdd extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_add);

    }

    public void StartTabbedActivity(View v){
        Intent intent = new Intent(this,Information.class);
        startActivity(intent);
    }
}
