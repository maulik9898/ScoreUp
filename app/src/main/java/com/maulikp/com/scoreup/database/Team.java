package com.maulikp.com.scoreup.database;

import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maulik on 3/17/2017.
 */

public class Team extends SugarRecord {
    String teamName;
    int noOfPlayer;
    List<Player> playerList = new ArrayList<>();

    public Team() {
        super();
    }

    public Team(String teamName, int noOfPlayer) {
        this.teamName = teamName;
        this.noOfPlayer = noOfPlayer;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getNoOfPlayer() {
        return noOfPlayer;
    }

    public void setNoOfPlayer(int noOfPlayer) {
        this.noOfPlayer = noOfPlayer;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }
}
