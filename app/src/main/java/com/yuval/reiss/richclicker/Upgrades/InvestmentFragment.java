package com.yuval.reiss.richclicker.Upgrades;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yuval.reiss.richclicker.R;

public class InvestmentFragment extends Fragment {

    public static InvestmentFragment newInstance() {
        InvestmentFragment fragment = new InvestmentFragment();
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.investments_frag, container, false);

        return view;
    }
}
