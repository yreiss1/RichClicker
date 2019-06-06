package com.yuval.reiss.richclicker;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.thekhaeng.pushdownanim.PushDownAnim;
import com.waynell.library.DropAnimationView;
import com.yuval.reiss.richclicker.Leaderboard.LeaderboardFragment;
import com.yuval.reiss.richclicker.Upgrades.InvestmentFragment;
import com.yuval.reiss.richclicker.Upgrades.WorkerFragment;

public class GameFragment extends Fragment {

    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private RelativeLayout relativeLayout;
    private DropAnimationView animationView;
    private ImageButton richButton;
    private int count;
    private TextView score;
    private FragmentPagerAdapter fragmentPagerAdapter;
    private ImageView signOut;


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

        signOut = view.findViewById(R.id.signout);


        setRetainInstance(true);

        if (savedInstanceState != null) {
            count = savedInstanceState.getInt("count");
            score.setText("$" + count);

        }


        richButton = view.findViewById(R.id.richhead_button);
        score = view.findViewById(R.id.score_count_textview);

        PushDownAnim.setPushDownAnimTo(richButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                score.setText("$" + count);
            }
        });


        ViewPager viewPager = view.findViewById(R.id.viewpager);
        fragmentPagerAdapter = new UpgradesPagerAdapter(getFragmentManager());
        viewPager.setAdapter(fragmentPagerAdapter);
        viewPager.setCurrentItem(0);

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

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
        score.setText("$" + count);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            Log.i("COUNT: ", Integer.toString(savedInstanceState.getInt("count")));
            score.setText("$" + savedInstanceState.getInt("count"));
        }
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i("THIS HAPPENED ", "It happened ya'll");
        outState.putInt("count", count);
    }


    public static class UpgradesPagerAdapter extends FragmentPagerAdapter {

        public UpgradesPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            switch(i) {
                case 0:
                    return WorkerFragment.newInstance();
                case 1:
                    return InvestmentFragment.newInstance();
            }

            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {

            switch(position) {
                case 0:
                    return "Workers";
                case 1:
                    return "Investments";
            }

            return null;

        }
    }

}
