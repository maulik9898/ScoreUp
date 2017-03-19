package com.maulikp.com.scoreup.database;

import com.orm.SugarRecord;

/**
 * Created by Maulik on 3/17/2017.
 */

public class Player extends SugarRecord {
    String name;
    String type;
    Boolean Onstrick;
    int four;
    int six;
    int runs;

    public Player() {
        super();
    }

    public Player(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getOnstrick() {
        return Onstrick;
    }

    public void setOnstrick(Boolean onstrick) {
        Onstrick = onstrick;
    }

    public int getFour() {
        return four;
    }

    public void setFour(int four) {
        this.four = four;
    }

    public int getSix() {
        return six;
    }

    public void setSix(int six) {
        this.six = six;
    }

    public int getRuns() {
        return runs;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }
}
