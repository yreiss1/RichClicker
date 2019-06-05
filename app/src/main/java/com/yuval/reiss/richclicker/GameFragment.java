package com.yuval.reiss.richclicker;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.waynell.library.DropAnimationView;

import java.util.ArrayList;

public class GameFragment extends Fragment {


    private RelativeLayout relativeLayout;
    private DropAnimationView animationView;

    public static GameFragment newInstance(){
        GameFragment fragment = new GameFragment();
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.game_frag, container, false);

        animationView = (DropAnimationView) view.findViewById(R.id.drop_animation_view);

        animationView.setDrawables(R.drawable.richhead, R.drawable.money1, R.drawable.money2, R.drawable.money3);

        animationView.startAnimation();





        return view;
    }
}
