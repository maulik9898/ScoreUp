package com.maulikp.com.scoreup;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.maulikp.com.scoreup.database.Team;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileSettingDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.util.AbstractDrawerImageLoader;
import com.mikepenz.materialdrawer.util.DrawerImageLoader;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity
        implements GoogleApiClient.OnConnectionFailedListener {
    public FirebaseAuth mAuth;
    public Uri murl;
    public GoogleApiClient mGoogleApiClient;
    public FirebaseAuth.AuthStateListener mAuthListener;
    public String mUserName;
    public AccountHeader headerResult = null;
    public Drawer result = null;
    public FirebaseUser mUser;
    public String api = "227854326290-k7f7eron9a8u5pv221948emkb9ag4jgh.apps.googleusercontent.com";
    public String mEmail;
    int i = 001;
    String teamName = "A";
    String noOfPlayer = "b";
    View v;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final LayoutInflater inflater1 = getLayoutInflater();
        v = inflater1.inflate(R.layout.create_team_diag, null);


        Button btn = (Button) v.findViewById(R.id.create_action);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText text = (EditText) v.findViewById(R.id.team_name);
                EditText text1 = (EditText) v.findViewById(R.id.no_of_players);
                String teamName = text.getText().toString();

                String noOfPlayer = text1.getText().toString();
                Log.d("CREATE", "TeamName" + teamName + "  No of player" + noOfPlayer);
                if (teamName.isEmpty()) {
                    text.setError("Enter name Of Match");
                }
                if (noOfPlayer.isEmpty()) {
                    text1.setError("Enter No Of Player");
                }
                if (teamName != null && noOfPlayer != null) {
                    startActivity(new Intent(MainActivity.this, CreateTeam.class));
                }
            }

        });


        MatchFragment matchFragment = new MatchFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.main_frame_layout, matchFragment)
                .commit();

        //*********************************  IMAGE LOADER INITIALIZATION ****************************************//

        DrawerImageLoader.init(new AbstractDrawerImageLoader() {
            @Override
            public void set(ImageView imageView, Uri uri, Drawable placeholder) {
                Picasso.with(imageView.getContext()).load(uri).placeholder(placeholder).into(imageView);
            }

            @Override
            public void cancel(ImageView imageView) {
                Picasso.with(imageView.getContext()).cancelRequest(imageView);
            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);


        //************************************************************************************************//
        //************************************  FIREBASE STUFF START  ************************************//
        //************************************************************************************************//

        final Context context = getApplicationContext();


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(api)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                mUser = firebaseAuth.getCurrentUser();
                if (mUser == null) {
                    startActivity(new Intent(context, SignIn.class));
                    finish();
                    return;

                } else {

                }
                // ...
            }
        };
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {

            mUserName = user.getDisplayName();
            mEmail = user.getEmail();
            murl = user.getPhotoUrl();

        }


        //************************************************************************************************//
        //************************************  FIREBASE STUFF END  ************************************//
        //************************************************************************************************//


        //*******************************************************************************************************//
        //*************************************  NAVIGATION DRAWER STUFF START  **********************************//
        //*******************************************************************************************************//

        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .withTranslucentStatusBar(true)
                .build();

        IProfile profile = new ProfileDrawerItem()
                .withName(mUserName)
                .withEmail(mEmail)
                .withIcon(murl)
                .withIdentifier(i);
        headerResult.addProfiles(
                profile,
                new ProfileSettingDrawerItem()
                        .withName("Add Account")
                        .withIcon(R.drawable.ic_add_black_24dp).withIdentifier(10000)

        );
        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withAccountHeader(headerResult)
                .addDrawerItems(
                        new PrimaryDrawerItem()
                                .withName(R.string.nav_match)
                                .withDescription(R.string.nav_match_description)
                                .withIcon(R.drawable.cricket_match)
                                .withIdentifier(1)
                                .withSelectable(true),
                        new PrimaryDrawerItem()
                                .withName(R.string.team_configuration)
                                .withDescription(R.string.team_configuration_description)
                                .withIcon(R.drawable.ic_people_black_24dp)
                                .withIdentifier(2)
                                .withSelectable(true),
                        new SectionDrawerItem().withName(R.string.drawer_item_section_header),
                        new SecondaryDrawerItem().withName("About")
                                .withIcon(R.drawable.ic_info_black_24dp)
                                .withIdentifier(21),
                        new SecondaryDrawerItem()
                                .withName("Google+")
                                .withIcon(R.drawable.google_plus)
                                .withIdentifier(22)
                                .withSelectable(true),
                        new SecondaryDrawerItem()
                                .withName(R.string.sign_out)
                                .withIcon(R.drawable.ic_exit_to_app_black_24dp)
                                .withIdentifier(23))
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {

                    @Override

                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {


                        if (drawerItem != null) {


                            Intent intent = null;

                            if (drawerItem.getIdentifier() == 1) {
                                result.closeDrawer();

                                MatchFragment matchFragment = new MatchFragment();

                                FragmentManager fragmentManager = getSupportFragmentManager();
                                fragmentManager.beginTransaction()
                                        .replace(R.id.main_frame_layout, matchFragment)
                                        .commit();


                            } else if (drawerItem.getIdentifier() == 2) {
                                result.closeDrawer();

                                TeamConfigurationFragment teamConfiguration = new TeamConfigurationFragment();

                                FragmentManager fragmentManager = getSupportFragmentManager();
                                fragmentManager.beginTransaction()
                                        .replace(R.id.main_frame_layout, teamConfiguration)
                                        .commit();

                            } else if (drawerItem.getIdentifier() == 23) {
                                result.closeDrawer();
                                mAuth.signOut();
                                Auth.GoogleSignInApi.signOut(mGoogleApiClient);
                                mUser = null;
                                mUserName = "Anonymous";
                                startActivity(new Intent(MainActivity.this, SignIn.class));
                                return true;

                            }
                        }
                        return true;
                    }
                })
                .build();

        //**************************************************************************************************//
        // ****************************** NAVIGATION DRAWER STUFF END  *************************************//
        //**************************************************************************************************//


    }

    @Override

    public void onBackPressed() {

        //handle the back press :D close the drawer first and if the drawer is closed close the activity

        if (result != null && result.isDrawerOpen()) {

            result.closeDrawer();

        } else {

            super.onBackPressed();

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, "Sign In Fail", Toast.LENGTH_LONG).show();
    }

    public void createTeam(View view) {
        final MaterialDialog dialog = new MaterialDialog.Builder(this)
                .title("CREATE TEAM")
                .customView(R.layout.create_team_diag, true)
                .build();


        //noinspection ConstantConditions

        final EditText name = (EditText) dialog.getCustomView().findViewById(R.id.team_name);
        final EditText no = (EditText) dialog.getCustomView().findViewById(R.id.no_of_players);
        Button btn = (Button) dialog.getCustomView().findViewById(R.id.create_action);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teamName = name.getText().toString();
                noOfPlayer = no.getText().toString();

                if (teamName.isEmpty()) {
                    name.setError("Enter team name");
                }
                if (noOfPlayer.isEmpty()) {
                    no.setError("Enter No. Of Player ");
                }
                if (!teamName.isEmpty() && !noOfPlayer.isEmpty()) {

                    Team team = new Team(teamName, Integer.parseInt(noOfPlayer));
                    team.save();
                    Long teamid = team.getId();
                    Intent i = new Intent(MainActivity.this, CreateTeam.class);
                    i.putExtra("TeamId", "" + teamid + "");
                    startActivity(i);

                }
                if (!teamName.isEmpty() && !noOfPlayer.isEmpty())
                    dialog.dismiss();
            }
        });
        dialog.show();


    }
}

