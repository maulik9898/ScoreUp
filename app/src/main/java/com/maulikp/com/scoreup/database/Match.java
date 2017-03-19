package com.maulikp.com.scoreup.database;

/**
 * Created by Maulik on 3/10/2017.
 */

public class Match {

    int Team1Run = 0;
    int Team2Run = 0;
    String Team1Name;
    String Team2Name;
    int Team1Wicket;
    int Team2Wicket;
    int Team1Over;
    int Team2Over;
    int Team1RemBall;
    int Team2RemBall;

    public Match(Boolean n) {
        if (n) {
            Team1Name = "PDPU";
            Team2Name = "DAIICT";
            Team1Over = 5;
            Team1Run = 160;
            Team2Run = 250;
            Team1RemBall = 3;
            Team1Wicket = 6;
            Team2Over = 20;
            Team2RemBall = 0;
            Team2Wicket = 6;
        }
    }

    public String getTeam1Run() {
        return (Team1Run + "/" + Team1Wicket);
    }

    public void setTeam1Run(int team1Run) {
        this.Team1Run = team1Run;
    }

    public String getTeam2Run() {
        return (Team2Run + "/" + Team2Wicket);
    }

    public void setTeam2Run(int team2Run) {
        this.Team2Run = team2Run;
    }

    public String getTeam1Name() {
        return Team1Name;
    }

    public void setTeam1Name(String team1Name) {
        this.Team1Name = team1Name;
    }

    public String getTeam2Name() {
        return Team2Name;
    }

    public void setTeam2Name(String team2Name) {
        this.Team2Name = team2Name;
    }

    public int getTeam1Wicket() {
        return Team1Wicket;
    }

    public void setTeam1Wicket(int team1Wicket) {
        this.Team1Wicket = team1Wicket;
    }

    public int getTeam2Wicket() {
        return Team2Wicket;
    }

    public void setTeam2Wicket(int team2Wicket) {
        this.Team2Wicket = team2Wicket;
    }

    public String getTeam1Over() {
        if (Team1RemBall != 0) {
            return Team1Over + " Over " + Team1RemBall + " Ball ";
        } else
            return Team1Over + " Over ";
    }

    public void setTeam1Over(int team1Over) {
        this.Team1Over = team1Over;
    }

    public String getTeam2Over() {
        if (Team2RemBall != 0) {
            return Team2Over + " Over " + Team2RemBall + " Ball ";
        } else
            return Team2Over + " Over ";
    }

    public void setTeam2Over(int team2Over) {
        Team2Over = team2Over;
    }

    public int getTeam1RemBall() {
        return Team1RemBall;
    }

    public void setTeam1RemBall(int team1RemBall) {
        Team1RemBall = team1RemBall;
    }

    public int getTeam2RemBall() {
        return Team2RemBall;
    }

    public void setTeam2RemBall(int team2RemBall) {
        Team2RemBall = team2RemBall;
    }
}
