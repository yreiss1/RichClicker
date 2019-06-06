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
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.thekhaeng.pushdownanim.PushDownAnim;
import com.waynell.library.DropAnimationView;
import com.yuval.reiss.richclicker.Leaderboard.LeaderboardFragment;
import com.yuval.reiss.richclicker.Upgrades.InvestmentFragment;
import com.yuval.reiss.richclicker.Upgrades.WorkerFragment;

import java.math.BigDecimal;

public class GameFragment extends Fragment {

    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private RelativeLayout relativeLayout;
    private DropAnimationView animationView;
    private ImageButton richButton;
    private float count;
    private TextView score;
    private TextView pointsPerSecondTextView;
    private TextView netWorthTextView;
    private FragmentPagerAdapter fragmentPagerAdapter;
    private ImageView signOut;

    private SlidingUpPanelLayout slidingLayout;

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

        animationView.setDrawables(R.drawable.richhead, R.drawable.money1, R.drawable.money2, R.drawable.money3, R.drawable.money1, R.drawable.money2, R.drawable.money3);
        animationView.startAnimation();

        signOut = view.findViewById(R.id.signout);

        pointsPerSecondTextView = view.findViewById(R.id.points_per_second_textview);
        netWorthTextView = view.findViewById(R.id.networth_textview);

        setRetainInstance(true);

        if (savedInstanceState != null) {
            MainActivity.UserStats.liquid= new BigDecimal(savedInstanceState.getFloat("count"));
            score.setText("$" + MainActivity.UserStats.liquid);

        }

        slidingLayout = view.findViewById(R.id.sliding_layout);
        slidingLayout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                updateUI();
            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {

            }
        });

        richButton = view.findViewById(R.id.richhead_button);
        score = view.findViewById(R.id.score_count_textview);

        PushDownAnim.setPushDownAnimTo(richButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("TAP VALUE: ", MainActivity.UserStats.tapValue.toString());
                Log.i("Liquid VALUE: ", MainActivity.UserStats.liquid.toString());

                MainActivity.UserStats.tapValue = MainActivity.UserStats.tapValue.setScale(2, BigDecimal.ROUND_HALF_UP);
                MainActivity.UserStats.liquid = MainActivity.UserStats.liquid.add(MainActivity.UserStats.tapValue);
                updateUI();
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

    public void updateUI() {

        MainActivity.UserStats.liquid = MainActivity.UserStats.liquid.setScale(2, BigDecimal.ROUND_HALF_UP);
        MainActivity.UserStats.tapValue = MainActivity.UserStats.tapValue.setScale(2, BigDecimal.ROUND_HALF_UP);
        MainActivity.UserStats.netWorth = MainActivity.UserStats.netWorth.setScale(2, BigDecimal.ROUND_HALF_UP);

        score.setText("$" + MainActivity.UserStats.liquid.toString());
        pointsPerSecondTextView.setText("$" + MainActivity.UserStats.tapValue + "/s");
        netWorthTextView.setText("Net Worth: $"+ MainActivity.UserStats.netWorth);

    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onPause() {
        super.onPause();

        if (MainActivity.UserStats.score < MainActivity.UserStats.liquid.floatValue()) {
            mFirebaseDatabase.getReference().child("users").child(mAuth.getUid()).child("score").setValue(MainActivity.UserStats.liquid.intValue());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        score.setText("$" + MainActivity.UserStats.liquid);

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
        outState.putFloat("count", MainActivity.UserStats.liquid.floatValue());
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
