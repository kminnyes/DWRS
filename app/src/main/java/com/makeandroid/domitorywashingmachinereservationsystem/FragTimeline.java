package com.makeandroid.domitorywashingmachinereservationsystem;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class FragTimeline extends Fragment {

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.frag_timeline, container, false);

        return view;
    }
}
