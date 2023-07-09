package com.makeandroid.domitorywashingmachinereservationsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth; // 파이어베이스 인증
    private DatabaseReference mDatabaseRef; //실시간 데이터 베이스
    private EditText putemail, putpassword;
    private Button loginbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("DWRS");

        putemail = findViewById(R.id.edit_ID_Login);
        putpassword = findViewById(R.id.edit_password_Login);
        loginbtn = findViewById(R.id.loginbtn_Login);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //로그인 요청
                String strEmail = putemail.getText().toString();
                String strPassword = putpassword.getText().toString();

                mFirebaseAuth.signInWithEmailAndPassword(strEmail, strPassword).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if(task.isSuccessful()){
                           //로그인 성공
                           Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                           startActivity(intent);
                           finish();//현재 엑티비티 파괴(다시 현재 엑티비티로 돌아올 일이 없을때 사용)

                            Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(LoginActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        Button btn_signup = findViewById(R.id.signupbtn_Login);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //회원가입 버튼 눌러 회원가입 화면으로 전환
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }
}