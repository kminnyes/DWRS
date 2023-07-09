package com.makeandroid.domitorywashingmachinereservationsystem;

public class UserAccount {// 사용자가 회원가입, 로그인 할때, 객체들을 어떻게 담아 줄 것인가 -> 객체 생성(사용자 계정 정보 모델 클래스)
    //생성자

    private String idToken; // Firebase Uid (고유 토큰정보(사용자의 유일 key값))

    private String emailId;
    private String password;
    private String nickname;

    public UserAccount() { }//빈 생성자(클래스가 생성될 때 가장먼저 호출)

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
