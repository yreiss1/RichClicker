package com.yuval.reiss.richclicker;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.thekhaeng.pushdownanim.PushDownAnim;
import com.waynell.library.DropAnimationView;

import java.util.ArrayList;

public class GameFragment extends Fragment {

    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private RelativeLayout relativeLayout;
    private DropAnimationView animationView;
    private ImageButton richButton;
    private int count;
    private TextView score;

    public static GameFragment newInstance(){
        GameFragment fragment = new GameFragment();
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.game_frag, container, false);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        animationView = (DropAnimationView) view.findViewById(R.id.drop_animation_view);

        animationView.setDrawables(R.drawable.richhead, R.drawable.money1, R.drawable.money2, R.drawable.money3, R.drawable.money1);
        animationView.startAnimation();

        setRetainInstance(true);

        if (savedInstanceState != null) {
            count = savedInstanceState.getInt("count");
            score.setText("Your Points: " + count);

        }


        richButton = view.findViewById(R.id.richhead_button);
        score = view.findViewById(R.id.score_count_textview);

        PushDownAnim.setPushDownAnimTo(richButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                score.setText("Your Points: " + count);
            }
        });


        return view;
    }

    @Override
    public void onPause() {
        super.onPause();

        if (MainActivity.me.getScore() < this.count) {
            mFirebaseDatabase.getReference().child("users").child(mAuth.getUid()).child("score").setValue(count);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        score.setText("Your Points: " + count);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            Log.i("COUNT: ", Integer.toString(savedInstanceState.getInt("count")));
            score.setText("Your Points: " + savedInstanceState.getInt("count"));
        }
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i("THIS HAPPENED ", "It happened ya'll");
        outState.putInt("count", count);
    }

}
