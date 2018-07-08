package com.example.nightc.gobuy.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.nightc.gobuy.R;
import com.example.nightc.gobuy.SignUp;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private GoogleApiClient googleApiClient;
    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;
    private static final String TAG = "GoogleActivity";
    private static final int RC_SIGN_IN = 9001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginlayout2);
        //Create Google Sign In with firebase
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();
        //end Config Signin

        mGoogleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);
        mAuth = FirebaseAuth.getInstance();


        Button SignUp = (Button) findViewById(R.id.NextButton);
        findViewById(R.id.SignIn).setOnClickListener(this);




    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }


    public void onSignUp(View view){
        Intent i = new Intent(this,SignUp.class);
        startActivity(i);
    }

    public void Login(View view){
        //check credentials
        //...
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
        //Start Logged in activity
    }

    @Override
    //Signs in to google account to use with firebase later
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Result returned from launching Intent for login
        if (requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data); // Get the result

            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                // [START_EXCLUDE]
                //updateUI(null);
                // [END_EXCLUDE]
            }
        }

    }

    private void firebaseAuthWithGoogle(final GoogleSignInAccount account){
        Log.d(TAG, "firebaseAuthWithGoogle: " + account.getId());
        //howProgressDialog();

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success");

                    //checks if there is a data snapshot with key: UserID
                    //So it checks if it is a new user
                    if (task.getResult().getAdditionalUserInfo().isNewUser()){

                        //if it is a new user it starts the SignUp activity
                        Intent i = new Intent(LoginActivity.this, SignUp.class);
                        startActivity(i);
                        LoginActivity.this.finish();

                    }
                    else{
                        //Start the new activity

                        Intent i = new Intent(LoginActivity.this,Bottom_Tabs_Activity.class);
                        startActivity(i);
                        LoginActivity.this.finish();
                    }



                    //updateUI(user);
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.getException());
                    Toast.makeText(LoginActivity.this, "Could not Connect", Toast.LENGTH_SHORT).show();
                   // updateUI(null);
                }

                // [START_EXCLUDE]
                //hideProgressDialog();
                // [END_EXCLUDE]
            }
        });


    }






    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.SignIn: Login(v);
        }
    }
}
