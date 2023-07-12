package com.makeandroid.domitorywashingmachinereservationsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

public class FragHome extends Fragment {

    private View view;
    private AppCompatButton booking_btn;
    private AppCompatButton register_btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_home, container, false);

        booking_btn = view.findViewById(R.id.booking_btn);//fragment안에서는 view.findViewById로 해줘야함
        register_btn = view.findViewById(R.id.register_btn);

        booking_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //Fragment에서는 FragHome.this가 아닌 getActivity로 화면전환 처리
                Intent intent = new Intent(getActivity(),booking1Activity.class);
                startActivity(intent);
            }
        });

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //Fragment에서는 Toast도 getActivity로!
                Toast.makeText(getActivity(), "예약이 확정되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        return view;


    }
}
