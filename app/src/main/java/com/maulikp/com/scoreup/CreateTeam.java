package com.maulikp.com.scoreup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.maulikp.com.scoreup.database.Team;

public class CreateTeam extends AppCompatActivity {

    String TeamName;
    int NoOfplayer;
    Team team;
    Long teamId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        teamId = Long.parseLong(intent.getExtras().getString("TeamId"));
        team = Team.findById(Team.class, teamId);

        TeamName = team.getTeamName().toString();
        setTitle(TeamName);
        setContentView(R.layout.activity_create_team);
    }


}
