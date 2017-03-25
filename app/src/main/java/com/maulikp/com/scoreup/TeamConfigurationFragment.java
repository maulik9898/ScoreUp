package com.maulikp.com.scoreup;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.maulikp.com.scoreup.customAdapters.TeamListAdapter;
import com.maulikp.com.scoreup.database.Team;

import java.util.List;

/*
 * A simple {@link Fragment} subclass.
 */
public class TeamConfigurationFragment extends Fragment {

    TeamListAdapter teamListAdapter;
    List<Team> teamList;
    View v;

    RecyclerView teamListrecycle;
    long initialCount;


    public TeamConfigurationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_team_configuration, container, false);
        // Inflate the layout for this fragment
        final Context context = getActivity();
        initialCount = Team.count(Team.class);
        update();
        teamListrecycle = (RecyclerView) v.findViewById(R.id.team_list);

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {


            @Override

            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {

                return false;

            }


            @Override

            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

                //Remove swiped item from list and notify the RecyclerView


                final int position = viewHolder.getAdapterPosition();

                final Team team1 = teamList.get(viewHolder.getAdapterPosition());

                teamList.remove(viewHolder.getAdapterPosition());

                teamListAdapter.notifyItemRemoved(position);


                team1.delete();

                initialCount -= 1;


                Snackbar.make(teamListrecycle, "Team deleted", Snackbar.LENGTH_SHORT)

                        .setAction("UNDO", new View.OnClickListener() {

                            @Override

                            public void onClick(View v) {


                                team1.save();

                                teamList.add(position, team1);

                                teamListAdapter.notifyItemInserted(position);

                                initialCount += 1;


                            }

                        })

                        .show();

            }


        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);

        itemTouchHelper.attachToRecyclerView(teamListrecycle);

        return v;
    }

    @Override
    public void onResume() {
        update();
        super.onResume();
    }


    public void update() {

        if (initialCount >= 0) {

            teamList = Team.listAll(Team.class);
            LinearLayoutManager llm = new LinearLayoutManager(getContext());
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            teamListAdapter = new TeamListAdapter(getActivity(), teamList);
            teamListrecycle = (RecyclerView) v.findViewById(R.id.team_list);
            teamListrecycle.setLayoutManager(llm);
            teamListrecycle.setAdapter(teamListAdapter);
            if (teamList.isEmpty()) {
                new MaterialDialog.Builder(getActivity())
                        .content("Create your team by pressing + FAB button")
                        .positiveText("OK")
                        .show();
            }
        }

    }
}