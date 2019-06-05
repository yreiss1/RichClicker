package com.yuval.reiss.richclicker.Leaderboard;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.yuval.reiss.richclicker.R;

public class LeaderboardViewHolder extends RecyclerView.ViewHolder {

    public TextView mUsername;
    public ImageView mImage;
    public TextView mPoints;
    public Button mButton;
    public TextView mRank;

    public LeaderboardViewHolder(View itemView) {
        super(itemView);

        mUsername = itemView.findViewById(R.id.username_textview);
        mImage = itemView.findViewById(R.id.friend_imageview);
        mPoints = itemView.findViewById(R.id.user_points_textview);
        mButton = itemView.findViewById(R.id.cheer_button);
        mRank = itemView.findViewById(R.id.rank);


    }
}
