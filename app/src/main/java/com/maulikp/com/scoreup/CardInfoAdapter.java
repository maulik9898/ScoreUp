package com.maulikp.com.scoreup;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maulikp.com.scoreup.database.Match;

import java.util.List;

/**
 * Created by Maulik on 3/10/2017.
 */

public class CardInfoAdapter extends RecyclerView.Adapter<CardInfoAdapter.MyViewHolder> {

    private List<Match> matchList;
    private Context mContext;

    public CardInfoAdapter(Context mContext, List<Match> matchList) {
        this.mContext = mContext;
        this.matchList = matchList;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cricket_info_card, parent, false);
        return new MyViewHolder(v);
    }

    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Match match1 = matchList.get(position);
        holder.TeamvsTeam.setText(match1.getTeam1Name() + " Vs " + match1.getTeam2Name());
        holder.Team1Run.setText(match1.getTeam1Run());
        holder.Team1Name.setText(match1.getTeam1Name());
        holder.Team2Name.setText(match1.getTeam2Name());
        holder.Team2Run.setText(match1.getTeam2Run());
        holder.Team1Over.setText(match1.getTeam1Over());
        holder.Team2Over.setText(match1.getTeam2Over());
    }

    @Override
    public int getItemCount() {
        return matchList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView
                Team1Run,
                Team2Run,
                Team1Name,
                Team2Name,
                TeamvsTeam,
                Team1Over,
                Team2Over;


        public MyViewHolder(View view) {
            super(view);
            Team1Run = (TextView) view.findViewById(R.id.card_view_team1_run);
            Team2Run = (TextView) view.findViewById(R.id.card_view_team2_run);
            Team1Name = (TextView) view.findViewById(R.id.card_view_team1_name);
            Team2Name = (TextView) view.findViewById(R.id.card_view_team2_name);
            Team1Over = (TextView) view.findViewById(R.id.card_view_team1_ball);
            Team2Over = (TextView) view.findViewById(R.id.card_view_team2_ball);
            TeamvsTeam = (TextView) view.findViewById(R.id.team_vs_team);


        }
    }
}
