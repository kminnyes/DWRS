package com.makeandroid.domitorywashingmachinereservationsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth; // 파이어베이스 인증
    private DatabaseReference mDatabaseRef; //실시간 데이터 베이스
    private EditText email_signup, password_signup, nickname_signup; //회원가입 입력필드
    private CheckBox agree_signup; //약관동의
    private Button btn_signup; //회원가입 버튼
    //변수 정의

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("DWRS");

        email_signup = findViewById(R.id.email_Signup);
        password_signup = findViewById(R.id.password_Signup);
        nickname_signup = findViewById(R.id.nickname_Signup);

        agree_signup = findViewById(R.id.checkBox_agree_Signup);
        //뒤로가기 버튼
        ImageButton backbtn_signup = findViewById(R.id.backbtn_Signup);

        btn_signup = findViewById(R.id.signup_btn_Signup);
        //xml파일의 id와 class의 변수 매칭

        backbtn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//객체생성
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        agree_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(agree_signup.isChecked()==true){
                    btn_signup.setVisibility(View.VISIBLE);
                }
                else
                {
                    btn_signup.setVisibility(View.INVISIBLE);
                }
            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //회원가입 처리 시작
                String strEmail = email_signup.getText().toString();//회원 가입 이메일 입력필드에서 사용자가 입력한 값을 string값으로 가져옴
                String strPassword = password_signup.getText().toString();
                String strNickname = nickname_signup.getText().toString();

                //Firebase Auth 진행
                mFirebaseAuth.createUserWithEmailAndPassword(strEmail, strPassword).addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        //가입이 이루어졌을 때 처리
                        if(task.isSuccessful()){//가입이 성공했을 때, task는 firebase가 회원가입시 결과값으로 던져주는 값
                            FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser(); //firebase 객체를 생성하여 현재 회원가입된 유저를 가져옴
                            UserAccount account = new UserAccount();
                            account.setIdToken(firebaseUser.getUid());
                            account.setEmailId(firebaseUser.getEmail());//현재 회원가입하는 유저의 이메일 가져옴(중요하기 때문에(사용자 식별))
                            account.setPassword(strPassword);
                            account.setNickname(strNickname);

                            //setValue : 데이터 베이스 insert// 데이터베이스에 현 유저의 Uid를 키값으로하여 insert(삽입)
                            mDatabaseRef.child("UserAccount").child(firebaseUser.getUid()).setValue(account);
                            //DWRS의 하위 개념으로 무언가를 넣겠다

                            Toast.makeText(SignupActivity.this, "회원가입이 완료되었습니다", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(SignupActivity.this, "회원가입 실패\0 다시 진행해주세요", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });
    }
}