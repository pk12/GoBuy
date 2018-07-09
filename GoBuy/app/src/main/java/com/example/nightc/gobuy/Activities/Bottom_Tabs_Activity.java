package com.example.nightc.gobuy.Activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.nightc.gobuy.Fragments.SettingsFragment;
import com.example.nightc.gobuy.Fragments.TabbedFragment;
import com.example.nightc.gobuy.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Bottom_Tabs_Activity extends AppCompatActivity {



    private TabbedFragment tabbedFragment = new TabbedFragment();
    private SettingsFragment settingsFragment = new SettingsFragment();

    private String FRAGMENT_TAG;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fm = getFragmentManager();
            android.support.v4.app.FragmentManager fmV4 = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction TransactionV4 = fmV4.beginTransaction();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();

            //Switching between fragments when a bottom tab is pressed
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if (FRAGMENT_TAG.equals("Settings")){
                        fragmentTransaction.remove(fm.findFragmentById(R.id.Bottom_Tab_Container)).commit();
                        TransactionV4.add(R.id.Bottom_Tab_Container, tabbedFragment,FRAGMENT_TAG="GSelection").commit();
                        getSupportFragmentManager().executePendingTransactions();

                    }
                    else if (!FRAGMENT_TAG.equals("GSelection")){
                        TransactionV4.replace(R.id.Bottom_Tab_Container, tabbedFragment,FRAGMENT_TAG="GSelection").commit();
                    }


                case R.id.navigation_trends:

                    return true;
                case R.id.navigation_settings:
                    if (FRAGMENT_TAG.equals("Settings")){
                        return true;
                    }
                    else {
                        TransactionV4.remove(fmV4.findFragmentById(R.id.Bottom_Tab_Container)).commit();
                        fragmentTransaction.replace(R.id.Bottom_Tab_Container,settingsFragment,FRAGMENT_TAG="Settings").commit();
                        getSupportFragmentManager().executePendingTransactions();
                    }


            }


                return true;

        }

    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_tabs_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.LogOut:
                //WORKS
                FirebaseAuth.getInstance().signOut();
                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(getString(R.string.default_web_client_id))
                        .requestEmail()
                        .build();
                GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

                mGoogleSignInClient.signOut().addOnCompleteListener(this,
                        new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Intent intent = new Intent(Bottom_Tabs_Activity.this, LoginActivity.class);
                                startActivity(intent);
                                Bottom_Tabs_Activity.this.finish();
                            }
                        });
                return true;
            default: return false;
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom__tabs_);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setElevation(0);
        actionBar.setTitle("Hello " + FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
        actionBar.setSubtitle("Save Today: ");
        getSupportFragmentManager().beginTransaction().replace(R.id.Bottom_Tab_Container, tabbedFragment,FRAGMENT_TAG="GSelection").commit();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }






}
