package com.maulikp.com.scoreup;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maulikp.com.scoreup.database.Match;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MatchFragment extends Fragment {

    View view;

    RecyclerView recyclerView;

    FloatingActionButton fab;

    CardInfoAdapter cardInfoAdapter;
    List<Match> matchList;


    public MatchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_match, container, false);


        new Thread(new Runnable() {
            @Override
            public void run() {

                recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

                matchList = new ArrayList<>();

                cardInfoAdapter = new CardInfoAdapter(getContext(), matchList);
                LinearLayoutManager llm = new LinearLayoutManager(getContext());
                llm.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(llm);
                recyclerView.setAdapter(cardInfoAdapter);

                matchList.add(new Match(true));
                matchList.add(new Match(true));
                matchList.add(new Match(true));
                matchList.add(new Match(true));
                matchList.add(new Match(true));
                matchList.add(new Match(true));
                matchList.add(new Match(true));
                matchList.add(new Match(true));

            }
        }).start();


        final FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab_create_match);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) {
                    // Scroll Down
                    if (fab.isShown()) {
                        fab.hide();
                    }
                } else if (dy < 0) {
                    // Scroll Up
                    if (!fab.isShown()) {
                        fab.show();
                    }
                }
            }
        });


        // Inflate the layout for this fragment

        return view;
    }


}
