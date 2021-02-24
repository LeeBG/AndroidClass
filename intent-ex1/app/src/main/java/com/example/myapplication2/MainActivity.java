package com.example.myapplication2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.io.Serializable;

//stack 생성
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity2";
    private Context mContext = MainActivity.this; //mContext로 MainActivity 함수 쓸라면 다운캐스팅하면 된다.
    private FloatingActionButton fabRoute;
    private ConstraintLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainLayout = findViewById(R.id.main_layout);
        fabRoute = findViewById(R.id.fab_route);
        fabRoute.setOnClickListener(v -> {
            //1. 현재 내 화면, 이동할 화면
            //2. 현재 내 화면, 내부앱의 이동할 화면(꼭 내 앱이 아닌 안드로이드 기본내장앱 또는 허용을 허락한 앱에 한해서 이동할 수 있음, Provider)
            //Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:01022227777")); //다른 앱에 접근할 때는 현재 내 위치를 적을 필요없음
            //인텐트 = 트럭(현재 내위치, 이동할 위치정보, 이동할 때 가져갈 박스)
            //다른 앱으로 이동 = 트럭(상대방 앱의 키, 데이터)
            User user = new User();
            user.setId(1);
            user.setUsername("cos");
            user.setPassword("1234");

            //gson으로 json변환 putExtra로 넘기고 다시 gson으로 자바 오브젝트
            //serializable
            //Bundle(택배박스)
            //Bundle bundle = new Bundle();
            //bundle.putSerializable("bundle",user);

            Intent intent = new Intent(mContext, SubActivity.class);//MainActivity.this = mContext
            intent.putExtra("username","ssar");
            intent.putExtra("user",user);
            //intent.putExtra("bundle",bundle);
            //startActivity(intent);
            startActivityForResult(intent,300); //콜백을 위해선
        });


    }


    //finish() 될때 콜백되는 함수
    //setResult() 함수의 피라미터가 전달됨
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d(TAG, "onActivityResult: 실행됨");
        Log.d(TAG, "requestCode: " + requestCode);
        Log.d(TAG, "resultcode: "+resultCode);

        //Window 가 무엇인지? AlertDialog 사용!!
        if (requestCode == 300){ //어떤 화면에서 돌아오는 지 확인(서브 액티비티에서 돌아왔다)
            if(resultCode == 1){//거기에서 어떤 로직이 성공했다
                //Log.d(TAG, "뭐야: ");
                //Toast.makeText(mContext, "인증 성공함: " + data.getStringExtra("auth"), Toast.LENGTH_SHORT).show();
                //어느 화면에서 toast(alert창)를 띄울지 알기 위해 mContext
                Snackbar.make(mainLayout,"인증 성공함", BaseTransientBottomBar.LENGTH_LONG).show(); //Toast랑 다른 점은 context 대신 view를 넣는 거.

            }else{
                Toast.makeText(mContext, "인증 실패", Toast.LENGTH_SHORT).show();
            }
        }

    }
}