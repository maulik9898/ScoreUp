package com.maulikp.com.scoreup;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.maulikp.com.scoreup.database.Team;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MatchInfo extends AppCompatActivity {


    @BindView(R.id.team2_spinner)
    Spinner team2Spinner;
    @BindView(R.id.team1_spinner)
    Spinner team1Spinner;
    @BindView(R.id.match_name_matchinfo)
    EditText matchNameMatchinfo;
    @BindView(R.id.textInputLayout3)
    TextInputLayout textInputLayout3;
    @BindView(R.id.team1_radioButton)
    RadioButton team1RadioButton;
    @BindView(R.id.team2_radioButton)
    RadioButton team2RadioButton;
    @BindView(R.id.over_matchinfo)
    EditText overMatchinfo;
    @BindView(R.id.extra_noball_run)
    CheckBox extraNoballRun;
    @BindView(R.id.extra_wide_run)
    CheckBox extraWideRun;
    @BindView(R.id.create_matchinfo)
    Button createMatchinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_info);
        ButterKnife.bind(this);

        List<Team> teamList = Team.listAll(Team.class);
        List<String> teamStringList = new ArrayList<>();
        for (int i = 0; i < teamList.size(); i++) {
            teamStringList.add(teamList.get(i).getTeamName());
        }
        ArrayAdapter<String> teamArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, teamStringList);
        team1Spinner.setAdapter(teamArrayAdapter);
        team2Spinner.setAdapter(teamArrayAdapter);


    }
}
