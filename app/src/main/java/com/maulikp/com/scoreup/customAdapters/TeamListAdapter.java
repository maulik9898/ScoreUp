package com.maulikp.com.scoreup.customAdapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maulikp.com.scoreup.CreateTeam;
import com.maulikp.com.scoreup.R;
import com.maulikp.com.scoreup.database.Team;

import java.util.List;

/**
 * Created by Maulik on 3/10/2017.
 */

public class TeamListAdapter extends RecyclerView.Adapter<TeamListAdapter.MyViewHolder> {

    private List<Team> TeamList;
    private Context mContext;

    public TeamListAdapter(Context mContext, List<Team> teamList) {
        this.mContext = mContext;
        this.TeamList = teamList;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.team_list, parent, false);
        return new MyViewHolder(v);
    }

    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Team team1 = TeamList.get(position);
        holder.TeamName.setText(team1.getTeamName());
        holder.TeamSize.setText("" + team1.getNoOfPlayer() + "");
        holder.cricket.setText("Cricket");

    }

    @Override
    public int getItemCount() {
        return TeamList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView
                cricket,
                TeamSize,
                TeamName;


        public MyViewHolder(View view) {
            super(view);
            mContext = view.getContext();
            view.setOnClickListener(this);
            TeamName = (TextView) view.findViewById(R.id.team_name_recycle);
            TeamSize = (TextView) view.findViewById(R.id.no_of_players_recycle);
            cricket = (TextView) view.findViewById(R.id.cricket);


        }

        @Override
        public void onClick(View view) {
            Log.d("ADAPTER", "onClick " + getPosition() + " ");
            Team team1 = TeamList.get(getPosition());
            Long teamid = team1.getId();
            Intent intent = new Intent(mContext, CreateTeam.class);
            intent.putExtra("TeamId", "" + teamid + "");
            mContext.startActivity(intent);
        }
    }
}