package com.yuval.reiss.richclicker;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import java.util.Timer;
import java.util.TimerTask;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.onesignal.OneSignal;
import com.yuval.reiss.richclicker.Leaderboard.LeaderboardFragment;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthStateListener;
    private FirebaseDatabase mFirebaseDatabase;
    private FragmentPagerAdapter fragmentPagerAdapter;
    private ImageView signOut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);




        mAuth = FirebaseAuth.getInstance();


        firebaseAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = mAuth.getCurrentUser();

                if(user == null) {
                    OneSignal.setSubscription(false);
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                    return;
                } else {
                    //TODO: Read data from Firebase
                }
            }
        };

        OneSignal.startInit(this).init();
        OneSignal.setSubscription(true);
        OneSignal.idsAvailable(new OneSignal.IdsAvailableHandler() {
            @Override
            public void idsAvailable(String userId, String registrationId) {
                FirebaseDatabase.getInstance().getReference().child("users").child(mAuth.getCurrentUser().getUid()).child("notify_id").setValue(userId);
            }
        });
        OneSignal.setInFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification);




    }


    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();



        ViewPager viewPager = findViewById(R.id.main_viewpager);
        fragmentPagerAdapter = new MainActivityPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(fragmentPagerAdapter);
        viewPager.setCurrentItem(0);





        mAuth.addAuthStateListener(firebaseAuthStateListener);

        if (currentUser == null) {


            Intent intent = new Intent( MainActivity.this, LoginActivity.class);
            startActivity(intent);
            return;

        } else {
            FirebaseDatabase.getInstance().getReference().child("users").child(mAuth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        Friend user = dataSnapshot.getValue(Friend.class);
                        UserStats.email = user.getEmail();
                        UserStats.username = user.getUsername();
                        UserStats.notify_id = user.getNotify_id();
                        UserStats.score = user.getScore();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }



    }

    public static class UserStats {

        public static String username, email, notify_id;
        public static int tech, business, tdp, manager, jennifer, samuel, fairbanks, score;
        public static BigDecimal liquid, netWorth, tapValue, workerValue, savingsAssetValue, indexAssetValue, realEstateAssetValue, stocksAssetValue, cryptoAssetValue;

        private static UserStats userStats = new UserStats();
        public static UserStats getInstance() {
            return userStats;
        }

        private UserStats() {
            liquid = BigDecimal.ZERO;
            tapValue = new BigDecimal(.01f);
            workerValue = BigDecimal.ZERO;
            netWorth = BigDecimal.ZERO;
            tech = 0;
            business = 0;
            tdp = 0;
            manager = 0;
            jennifer = 0;
            samuel = 0;
            fairbanks = 0;
            savingsAssetValue = BigDecimal.ZERO;
            indexAssetValue = BigDecimal.ZERO;
            realEstateAssetValue = BigDecimal.ZERO;
            stocksAssetValue = BigDecimal.ZERO;
            cryptoAssetValue = BigDecimal.ZERO;

        }

        private void timeUpdate() {
            class timeUpdate extends TimerTask {

                public void run() {
                    savingsAssetValue = savingsAssetValue.multiply(new BigDecimal(1.01));
                    indexAssetValue = indexAssetValue.multiply(new BigDecimal(.98 + Math.random() * (1.06 - .98)));
                    realEstateAssetValue = realEstateAssetValue.multiply(new BigDecimal(.9 + Math.random() * (1.2 - .9)));
                    savingsAssetValue = stocksAssetValue.multiply(new BigDecimal(.5 + Math.random() * (2 - .5)));
                    cryptoAssetValue = cryptoAssetValue.multiply(new BigDecimal(0 + Math.random() * (1 - 0))).signum() < .95 ? (new BigDecimal(0 + Math.random() * (1.5 - 0))) : (new BigDecimal(10 + Math.random() * (20 - 10)));
                    BigDecimal totalAssetValue = savingsAssetValue.add(indexAssetValue).add(realEstateAssetValue).add(stocksAssetValue).add(cryptoAssetValue);
                    netWorth = liquid.add(workerValue).add(totalAssetValue);

                }

            }

            Timer timer = new Timer();
            timer.schedule(new timeUpdate(), 0, 1000);
        }





    }

    public static class MainActivityPagerAdapter extends FragmentPagerAdapter {

        public MainActivityPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            switch(i) {
                case 0:
                    return GameFragment.newInstance();
                case 1:
                    return LeaderboardFragment.newInstance();
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
                    return "Game";
                case 1:
                    return "Leaderboard";
            }

            return null;

        }
    }

}
