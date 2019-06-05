package com.yuval.reiss.richclicker.Leaderboard;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.yuval.reiss.richclicker.Friend;
import com.yuval.reiss.richclicker.R;

import java.util.ArrayList;
import java.util.Collections;

public class LeaderboardFragment extends Fragment {


    private FirebaseDatabase mFirebaseDatabase;
    private ArrayList<Friend> friendsArrayList;
    private DatabaseReference mFriendDatabaseRef;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;


    private RecyclerView leaderboardRV;
    public static LeaderboardFragment newInstance(){
        LeaderboardFragment fragment = new LeaderboardFragment();
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.leaderboard_frag, container, false);

        mFirebaseDatabase = FirebaseDatabase.getInstance();

        friendsArrayList = new ArrayList<>();

        mFriendDatabaseRef = mFirebaseDatabase.getReference().child("users");

        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh);

        leaderboardRV = view.findViewById(R.id.leaderboard_rv);

        leaderboardRV.setNestedScrollingEnabled(false);
        leaderboardRV.setHasFixedSize(false);
        mLayoutManager = new LinearLayoutManager(getContext());
        leaderboardRV.setLayoutManager(mLayoutManager);

        mAdapter = new LeaderboardRCAdapter(friendsArrayList, getContext());

        leaderboardRV.setAdapter(mAdapter);

        leaderboardRV.refreshDrawableState();
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(leaderboardRV.getContext(),
                DividerItemDecoration.VERTICAL);
        leaderboardRV.addItemDecoration(dividerItemDecoration);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                friendsArrayList.clear();
                loadFriends();
            }
        });

        loadFriends();

        return view;
    }


    private void loadFriends() {

        Query query = mFriendDatabaseRef.orderByChild("score");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Friend friend = data.getValue(Friend.class);
                    friendsArrayList.add(friend);
                }

                Collections.reverse(friendsArrayList);

                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    @Override
    public void onStop() {
        super.onStop();
        friendsArrayList.clear();
    }
}
