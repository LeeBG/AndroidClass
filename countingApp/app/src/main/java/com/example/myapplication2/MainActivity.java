package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity2";
    private Button btnAdd,btnMinus;
    private TextView tvNum;
    private Integer num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //xml에 있는 그림이 메모리에 올라오는 과정 = 인플레이트(따로 new 할 필요가 없다.)

        // pgb_loading은 실행시에 메모리에 뜸.
        // 실행전에 컴파일시에 툴이 저 친구를 R에 등록을 해줌.

        init();
        initSetting();
        initListener();

    }

    private void initSetting(){
        num=1;
        tvNum.setText(num.toString());
    }

    private void init(){
        btnAdd = findViewById(R.id.btn_add); //이제는 툴이 자동. (Button)으로 다운캐스팅할 필요 없다.
        btnMinus = findViewById(R.id.btn_minus); //최신트렌드는 데이터바인딩(나중에 배움)
        tvNum = findViewById(R.id.tv_num);
    }

    private void initListener(){ //화살표 함수의 장점: 타입 몰라도 된다.
        btnAdd.setOnClickListener(
            (v) ->{
                Log.d(TAG, "initListener: ");
                num = num+1;
                tvNum.setText(num.toString());
            }
        );

        btnMinus.setOnClickListener(v -> {
            Log.d(TAG, "initListener: btnMinus");
            num = num-1;
            tvNum.setText(num.toString());
        });
    }

}