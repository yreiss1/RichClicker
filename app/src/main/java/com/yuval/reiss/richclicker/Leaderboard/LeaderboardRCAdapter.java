package com.yuval.reiss.richclicker.Leaderboard;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.yuval.reiss.richclicker.Friend;
import com.yuval.reiss.richclicker.MainActivity;
import com.yuval.reiss.richclicker.R;
import com.yuval.reiss.richclicker.SendNotification;

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
        holder.mRank.setText(Integer.toString(position + 1));
        holder.mPoints.setText("$" + Integer.toString(friend.getScore()));
        if (friend.getImage().equals("default")) {
            Glide.with(context).load(R.drawable.richfairbanks).into(holder.mImage);
        } else {
            Glide.with(context).load(friend.getImage()).into(holder.mImage);
        }

        Log.i("NOTIFY ID: ", friend.getNotify_id());


        holder.mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = MainActivity.UserStats.username + " has cheered you on! Make that bread!";
                new SendNotification(message,  MainActivity.UserStats.username + " is cheering you on!", friend.getNotify_id(), context);
                Toast.makeText(context, "You've cheered on " + friend.getUsername() + "!",Toast.LENGTH_SHORT).show();

            }
        });



    }

    @Override
    public int getItemCount() {
        return friendsArrayList.size();
    }
}
