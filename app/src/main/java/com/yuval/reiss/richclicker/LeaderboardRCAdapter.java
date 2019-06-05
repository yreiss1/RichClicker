package com.yuval.reiss.richclicker;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class LeaderboardRCAdapter extends RecyclerView.Adapter<LeaderboardViewHolder> {

    private ArrayList<Friend> friendsArrayList;
    private Context context;

    public LeaderboardRCAdapter(ArrayList<Friend> friendsList, Context context) {
        this.friendsArrayList = friendsList;
        this.context = context;
    }


    @NonNull
    @Override
    public LeaderboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_leaderboard, parent,false);
        LeaderboardViewHolder leaderboardViewHolder = new LeaderboardViewHolder(layoutView);
        return leaderboardViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LeaderboardViewHolder holder, int position) {
        final Friend friend = friendsArrayList.get(position);

        holder.mUsername.setText(friend.getUsername());
        holder.mEmail.setText(friend.getEmail());
        holder.mPoints.setText(Integer.toString(friend.getScore()));
        if (friend.getImage().equals("default")) {
            Glide.with(context).load(R.drawable.richfairbanks).into(holder.mImage);
        } else {
            Glide.with(context).load(friend.getImage()).into(holder.mImage);
        }



    }

    @Override
    public int getItemCount() {
        return friendsArrayList.size();
    }
}
