package com.cos.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

// 메인쓰레드 => onCreate() =>  UI 쓰레드 실행
//                          => 이벤트 쓰레드 [ 배열 ] 확인
//                          => 이벤트 리스너를 만듦 [A버튼] 리스너는 OS가 관리
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    // alt + insert : 생성자 getter setter
    // shift Enter 바로 다음줄 내려가기

    private TextView tvTitle;

    int num = 19;         //전역변수 Destroy할 때까지 살아있다

    //Manifest에서 설정된 자바 파일이 실행될 때 가장 먼저 실행되는 함수 onCreate()
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);//그림 그리는 함수(무엇을? activity_main) => 자바
//        new Thread(new Runnable() { //UI스레드가 아님
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e){
//                    e.printStackTrace();
//                }
//                tvTitle = findViewById(R.id.tv_title);
//                tvTitle.setText("HI");
//            }
//        }).start();
        Log.d(TAG, "onCreate:" + num);
    }

    // 앱 멈출때~~!!! - 현재 상태같은 것을 저장할 때
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop:" + num);
    }

    //화면 그려지기 직전 => 데이터 다운로드
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sp = getSharedPreferences("movie",MODE_PRIVATE);
        String title = sp.getString("title", "없음");
        Log.d(TAG, "onResume:" + title);
    } // onCreate종료시에 그림 그려짐

    //앱 종료시
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy:");

        // 종료되면 다 날라갈 것
        // 1. 파일 (그림 사진 = 카메라로 찍은 사진)
        // 2. 내부 DB  = (SQL Lite) (주소록, 메모장, 달력)
        // 3. 외부 서버 = 외부 DB (인스타그램 업로드할 사진)
        // 4. 하드 Access 저장소 (제일 많이 씀) = 앱마다 달려있음. = 실제로는 한 개(키로 구분)
        // = Sheared Preference - 꺼내 쓰기가 엄청 빠름
        SharedPreferences sp = getSharedPreferences("movie",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("title","바람과 함께 사라지다.");
        editor.commit();
    }
}
