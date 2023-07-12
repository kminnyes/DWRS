package com.makeandroid.domitorywashingmachinereservationsystem;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FragPerson extends Fragment {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseRef;

    private View view;
    private TextView nickname_print;
    private TextView lostboard;
    private TextView breakdown_wm;
    private TextView quit_member;
    private TextView change_nickname;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_person, container, false);

        nickname_print = view.findViewById(R.id.nickname_print);
        lostboard = view.findViewById(R.id.lost_board);
        breakdown_wm = view.findViewById(R.id.breakdown_wm);
        quit_member = view.findViewById(R.id.quit_member);
        change_nickname = view.findViewById(R.id.change_nickname);

        mAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("DWRS");

        mDatabaseRef.child("UserAccount").child(mAuth.getUid()).child("nickname").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(Task<DataSnapshot> task) {
                if(task.isSuccessful())
                {
                    nickname_print.setText(task.getResult().getValue().toString() + " 님");
                }
            }
        });// 실시간 데이터베이스에서 값 받아오기

        lostboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BoardmainActivity.class);
                startActivity(intent);
            }
        });

        breakdown_wm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RegisterbreakdownActivity.class);
                startActivity(intent);
            }
        });


        return view;
    }
}
