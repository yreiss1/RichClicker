package com.yuval.reiss.richclicker;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class LeaderboardViewHolder extends RecyclerView.ViewHolder {

    public TextView mUsername;
    public TextView mEmail;
    public ImageView mImage;
    public Button mButton;

    public LeaderboardViewHolder(View itemView) {
        super(itemView);

        mUsername = itemView.findViewById(R.id.username_textview);
        mEmail = itemView.findViewById(R.id.user_email_textview);
        mImage = itemView.findViewById(R.id.friend_imageview);
        mButton = itemView.findViewById(R.id.poke_button);
    }
}
