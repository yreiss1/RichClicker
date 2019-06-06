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
import android.widget.Toast;

import com.yuval.reiss.richclicker.GameFragment;
import com.yuval.reiss.richclicker.MainActivity;
import com.yuval.reiss.richclicker.R;

import java.math.BigDecimal;

public class WorkerFragment extends Fragment {

    private Button techButton;
    private Button businessButton;
    private Button tdpButton;
    private Button managerButton;
    private Button jenniferButton;
    private Button samuelButton;
    private Button fairbanksButton;
    private BigDecimal businessValue = new BigDecimal(.1f), techValue = new BigDecimal(.45f), tdpValue = new BigDecimal(.85f), managerValue = new BigDecimal(2f), jenniferaValue = new BigDecimal(7.5f), samuelValue = new BigDecimal(35f), fairbanksValue = new BigDecimal(65f);




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
                if (MainActivity.UserStats.liquid.compareTo(businessValue) > 0 ) {

                    MainActivity.UserStats.liquid = MainActivity.UserStats.liquid.subtract(businessValue);
                    MainActivity.UserStats.tapValue = MainActivity.UserStats.tapValue.add(new BigDecimal(0.01f));
                    MainActivity.UserStats.workerValue = MainActivity.UserStats.workerValue.add(businessValue);
                    MainActivity.UserStats.getInstance().business++;
                    if (MainActivity.UserStats.getInstance().business <= 8) {
                        businessLinearLayout.getChildAt(MainActivity.UserStats.getInstance().business - 1).setVisibility(View.VISIBLE);
                    }
                } else {
                    Toast.makeText(getContext(), "Not enough money!", Toast.LENGTH_SHORT).show();
                }


            }
        });
        techButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.UserStats.liquid.compareTo(techValue) > 0) {

                    MainActivity.UserStats.liquid = MainActivity.UserStats.liquid.subtract(techValue);
                    MainActivity.UserStats.tapValue = MainActivity.UserStats.tapValue.add(new BigDecimal(0.05f));
                    MainActivity.UserStats.workerValue = MainActivity.UserStats.workerValue.add(techValue);

                    MainActivity.UserStats.getInstance().tech++;
                    if (MainActivity.UserStats.getInstance().tech <= 8) {
                        techLinearLayout.getChildAt(MainActivity.UserStats.getInstance().tech - 1).setVisibility(View.VISIBLE);
                    }
                }else {
                    Toast.makeText(getContext(), "Not enough money!", Toast.LENGTH_SHORT).show();
                }

            }
        });
        tdpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (MainActivity.UserStats.liquid.compareTo(tdpValue) > 0 ) {
                    MainActivity.UserStats.liquid = MainActivity.UserStats.liquid.subtract(tdpValue);
                    MainActivity.UserStats.tapValue = MainActivity.UserStats.tapValue.add(new BigDecimal(0.1f));
                    MainActivity.UserStats.workerValue = MainActivity.UserStats.workerValue.add(tdpValue);

                    MainActivity.UserStats.getInstance().tdp++;
                    if (MainActivity.UserStats.getInstance().tdp <= 8) {
                        tdpLinearLayout.getChildAt(MainActivity.UserStats.getInstance().tdp - 1).setVisibility(View.VISIBLE);
                    }
                }else {
                    Toast.makeText(getContext(), "Not enough money!", Toast.LENGTH_SHORT).show();
                }

            }
        });
        managerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.UserStats.liquid.compareTo(managerValue) > 0){

                    MainActivity.UserStats.liquid = MainActivity.UserStats.liquid.subtract(managerValue);
                    MainActivity.UserStats.tapValue = MainActivity.UserStats.tapValue.add(new BigDecimal(0.25f));
                    MainActivity.UserStats.workerValue = MainActivity.UserStats.workerValue.add(managerValue);
                    MainActivity.UserStats.getInstance().manager++;
                    if (MainActivity.UserStats.getInstance().manager <= 8) {
                        managersLinearLayout.getChildAt(MainActivity.UserStats.getInstance().manager - 1).setVisibility(View.VISIBLE);
                    }
                }else {
                    Toast.makeText(getContext(), "Not enough money!", Toast.LENGTH_SHORT).show();
                }

            }
        });
        samuelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.UserStats.liquid.compareTo(samuelValue) > 0) {

                    MainActivity.UserStats.liquid = MainActivity.UserStats.liquid.subtract(samuelValue);
                    MainActivity.UserStats.tapValue = MainActivity.UserStats.tapValue.add(new BigDecimal(5f));
                    MainActivity.UserStats.workerValue = MainActivity.UserStats.workerValue.add(samuelValue);
                    MainActivity.UserStats.getInstance().samuel++;
                    if (MainActivity.UserStats.getInstance().samuel <= 8) {
                        samuelLJacksonLinearLayout.getChildAt(MainActivity.UserStats.getInstance().samuel - 1).setVisibility(View.VISIBLE);
                    }
                }else {
                    Toast.makeText(getContext(), "Not enough money!", Toast.LENGTH_SHORT).show();
                }

            }
        });
        jenniferButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.UserStats.liquid.compareTo(jenniferaValue) > 0) {

                    MainActivity.UserStats.liquid =  MainActivity.UserStats.liquid.subtract(jenniferaValue);
                    MainActivity.UserStats.tapValue = MainActivity.UserStats.tapValue.add(new BigDecimal(1f));
                    MainActivity.UserStats.workerValue = MainActivity.UserStats.workerValue.add(jenniferaValue);
                    MainActivity.UserStats.getInstance().jennifer++;
                    if (MainActivity.UserStats.getInstance().jennifer <= 8) {
                        jenniferGarnerLayout.getChildAt(MainActivity.UserStats.getInstance().jennifer - 1).setVisibility(View.VISIBLE);

                    }
                }else {
                    Toast.makeText(getContext(), "Not enough money!", Toast.LENGTH_SHORT).show();
                }

            }
        });
        fairbanksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.UserStats.liquid.compareTo(fairbanksValue) > 0) {

                    MainActivity.UserStats.liquid = MainActivity.UserStats.liquid.subtract(fairbanksValue);
                    MainActivity.UserStats.tapValue = MainActivity.UserStats.tapValue.add(new BigDecimal(10f));
                    MainActivity.UserStats.workerValue = MainActivity.UserStats.workerValue.add(fairbanksValue);
                    MainActivity.UserStats.getInstance().fairbanks++;
                    if (MainActivity.UserStats.getInstance().fairbanks <= 8) {
                        richFairbanksLinearLayout.getChildAt(MainActivity.UserStats.getInstance().fairbanks - 1).setVisibility(View.VISIBLE);
                    }
                }else {
                    Toast.makeText(getContext(), "Not enough money!", Toast.LENGTH_SHORT).show();
                }
            }
        });







        return view;
    }
}
