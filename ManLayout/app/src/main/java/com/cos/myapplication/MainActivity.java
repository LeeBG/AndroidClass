package com.cos.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

// 메인쓰레드 => onCreate() =>  UI 쓰레드 실행
//                          => 이벤트 쓰레드 [ 배열 ] 확인
//                          => 이벤트 리스너를 만듦 [A버튼] 리스너는 OS가 관리
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView tvTitle;
    private Button button;
    //Manifest에서 설정된 자바 파일이 실행될 때 가장 먼저 실행되는 함수 onCreate()
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//그림 그리는 함수(무엇을? activity_main) => 자바

    }
}
