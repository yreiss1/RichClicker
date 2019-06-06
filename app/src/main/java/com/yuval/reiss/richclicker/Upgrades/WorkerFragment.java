package com.yuval.reiss.richclicker.Upgrades;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.yuval.reiss.richclicker.GameFragment;
import com.yuval.reiss.richclicker.MainActivity;
import com.yuval.reiss.richclicker.R;

public class WorkerFragment extends Fragment {

    private Button techButton;
    private Button businessButton;
    private Button tdpButton;
    private Button managerButton;
    private Button jenniferButton;
    private Button samuelButton;
    private Button fairbanksButton;




    public static WorkerFragment newInstance() {
        WorkerFragment fragment = new WorkerFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.workers_frag, container, false);

        businessButton = view.findViewById(R.id.business_interns_button);
        techButton = view.findViewById(R.id.tech_buy_button);
        tdpButton = view.findViewById(R.id.tdp_buy_button);
        managerButton = view.findViewById(R.id.manager_buy_button);
        jenniferButton = view.findViewById(R.id.jennifer_garner_buy_button);
        samuelButton = view.findViewById(R.id.samuel_l_jackson_buy_button);
        fairbanksButton = view.findViewById(R.id.rich_fairbanks_buy_button);

        final LinearLayout businessLinearLayout = view.findViewById(R.id.business_interns_linear_layout);
        final LinearLayout techLinearLayout = view.findViewById(R.id.tech_interns_linear_layout);
        final LinearLayout tdpLinearLayout = view.findViewById(R.id.tdp_linear_layout);
        final LinearLayout managersLinearLayout = view.findViewById(R.id.managers_interns_linear_layout);
        final LinearLayout jenniferGarnerLayout = view.findViewById(R.id.jennifer_garner_linear_layout);
        final LinearLayout samuelLJacksonLinearLayout = view.findViewById(R.id.samuel_l_jackson_linear_layout);
        final LinearLayout richFairbanksLinearLayout = view.findViewById(R.id.rich_fairbanks_linear_layout);


        for(int i = 0; i < 8; i++) {
            businessLinearLayout.getChildAt(i).setVisibility(View.INVISIBLE);
            techLinearLayout.getChildAt(i).setVisibility(View.INVISIBLE);
            tdpLinearLayout.getChildAt(i).setVisibility(View.INVISIBLE);
            managersLinearLayout.getChildAt(i).setVisibility(View.INVISIBLE);
            jenniferGarnerLayout.getChildAt(i).setVisibility(View.INVISIBLE);
            samuelLJacksonLinearLayout.getChildAt(i).setVisibility(View.INVISIBLE);
            richFairbanksLinearLayout.getChildAt(i).setVisibility(View.INVISIBLE);
        }

        businessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("LIQUID: ", Double.toString(MainActivity.UserStats.liquid));
                if (MainActivity.UserStats.liquid >= .1f) {

                    MainActivity.UserStats.liquid -= 0.1f;
                    MainActivity.UserStats.tapValue += 0.01f;
                    MainActivity.UserStats.workerValue += .1f;
                    MainActivity.UserStats.getInstance().business++;
                    if (MainActivity.UserStats.getInstance().business <= 8) {
                        businessLinearLayout.getChildAt(MainActivity.UserStats.getInstance().business - 1).setVisibility(View.VISIBLE);
                    }
                }


            }
        });
        techButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.UserStats.liquid >= .45f) {

                    MainActivity.UserStats.liquid -= 0.45f;
                    MainActivity.UserStats.tapValue += 0.05f;
                    MainActivity.UserStats.workerValue += .45f;

                    MainActivity.UserStats.getInstance().tech++;
                    if (MainActivity.UserStats.getInstance().tech <= 8) {
                        techLinearLayout.getChildAt(MainActivity.UserStats.getInstance().tech - 1).setVisibility(View.VISIBLE);
                    }
                }

            }
        });
        tdpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (MainActivity.UserStats.liquid >= .85f) {

                    MainActivity.UserStats.liquid -= 0.85f;
                    MainActivity.UserStats.tapValue += 0.1f;
                    MainActivity.UserStats.workerValue += .85f;

                    MainActivity.UserStats.getInstance().tdp++;
                    if (MainActivity.UserStats.getInstance().tdp <= 8) {
                        tdpLinearLayout.getChildAt(MainActivity.UserStats.getInstance().tdp - 1).setVisibility(View.VISIBLE);
                    }
                }

            }
        });
        managerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.UserStats.liquid >= 2f) {

                    MainActivity.UserStats.liquid -= 2f;
                    MainActivity.UserStats.tapValue += 0.25f;
                    MainActivity.UserStats.workerValue += 2f;
                    MainActivity.UserStats.getInstance().manager++;
                    if (MainActivity.UserStats.getInstance().manager <= 8) {
                        managersLinearLayout.getChildAt(MainActivity.UserStats.getInstance().manager - 1).setVisibility(View.VISIBLE);
                    }
                }

            }
        });
        samuelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.UserStats.liquid >= 35f) {

                    MainActivity.UserStats.liquid -= 35f;
                    MainActivity.UserStats.tapValue += 5f;
                    MainActivity.UserStats.workerValue += 35f;
                    MainActivity.UserStats.getInstance().samuel++;
                    if (MainActivity.UserStats.getInstance().samuel <= 8) {
                        samuelLJacksonLinearLayout.getChildAt(MainActivity.UserStats.getInstance().samuel - 1).setVisibility(View.VISIBLE);
                    }
                }

            }
        });
        jenniferButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.UserStats.liquid >= 7.5f) {

                    MainActivity.UserStats.liquid -= 7.5f;
                    MainActivity.UserStats.tapValue += 1f;
                    MainActivity.UserStats.workerValue += 7.5f;
                    MainActivity.UserStats.getInstance().jennifer++;
                    if (MainActivity.UserStats.getInstance().jennifer <= 8) {
                        jenniferGarnerLayout.getChildAt(MainActivity.UserStats.getInstance().jennifer - 1).setVisibility(View.VISIBLE);

                    }
                }

            }
        });
        fairbanksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.UserStats.liquid >= 65f) {

                    MainActivity.UserStats.liquid -= 65f;
                    MainActivity.UserStats.tapValue += 10f;
                    MainActivity.UserStats.workerValue += 65f;
                    MainActivity.UserStats.getInstance().fairbanks++;
                    if (MainActivity.UserStats.getInstance().fairbanks <= 8) {
                        richFairbanksLinearLayout.getChildAt(MainActivity.UserStats.getInstance().fairbanks - 1).setVisibility(View.VISIBLE);
                    }
                }
            }
        });







        return view;
    }
}
