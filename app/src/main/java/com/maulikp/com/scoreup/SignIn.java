package com.maulikp.com.scoreup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class SignIn extends AppCompatActivity implements

        GoogleApiClient.OnConnectionFailedListener,

        View.OnClickListener {
    private static final String TAG = "GoogleActivity";

    private static final int RC_SIGN_IN = 9001;


    // [START declare_auth]

    private FirebaseAuth mAuth;

    // [END declare_auth]


    // [START declare_auth_listener]

    private FirebaseAuth.AuthStateListener mAuthListener;

    // [END declare_auth_listener]

    private SignInButton mSignInbutton;
    private Context contex;
    private GoogleApiClient mGoogleApiClient;
    private String api = "227854326290-k7f7eron9a8u5pv221948emkb9ag4jgh.apps.googleusercontent.com";
    private TextView mStatusTextView;

    private TextView mDetailTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        mSignInbutton = (SignInButton) findViewById(R.id.sign_in_button);
        mSignInbutton.setOnClickListener(this);
        contex = getApplicationContext();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(api)
                .requestEmail()
                .build();


        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        mAuth = FirebaseAuth.getInstance();


    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.sign_in_button) {
            signIn();

        }

    }

    @Override

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);


        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);

        if (requestCode == RC_SIGN_IN) {

            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            if (result.isSuccess()) {

                // Google Sign In was successful, authenticate with Firebase

                GoogleSignInAccount account = result.getSignInAccount();

                firebaseAuthWithGoogle(account);

            }

            // Google Sign In failed, update UI appropriately

            // [START_EXCLUDE]


            // [END_EXCLUDE]


        }

    }

    // [END onactivityresult]

    // [START auth_with_google]
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {

        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        // [START_EXCLUDE silent]
        final MaterialDialog dialog = new MaterialDialog.Builder(this)
                .title("Sign In..")
                .content("Please Wait...")
                .progress(true, 0)
                .show();
        // [END_EXCLUDE]


        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);

        mAuth.signInWithCredential(credential)

                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override

                    public void onComplete(@NonNull Task<AuthResult> task) {

                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());

                        startActivity(new Intent(contex, MainActivity.class));


                        // If sign in fails, display a message to the user. If sign in succeeds

                        // the auth state listener will be notified and logic to handle the

                        // signed in user can be handled in the listener.

                        if (!task.isSuccessful()) {

                            Log.w(TAG, "signInWithCredential", task.getException());

                            Toast.makeText(SignIn.this, "Authentication failed.",

                                    Toast.LENGTH_SHORT).show();

                        }

                        // [START_EXCLUDE]

                        dialog.dismiss();

                        // [END_EXCLUDE]

                    }

                });

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void signIn() {

        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);

        startActivityForResult(signInIntent, RC_SIGN_IN);

    }

    // [END signin]
}

