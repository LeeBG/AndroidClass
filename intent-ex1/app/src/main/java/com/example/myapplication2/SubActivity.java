package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SubActivity extends AppCompatActivity {

    private static final String TAG = "SubActivity";
    private FloatingActionButton fabPop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Intent intent = getIntent();

        User user = (User)getIntent().getSerializableExtra("user");
       //Bundle bundle = getIntent().getBundleExtra("bundle");



        //Log.d(TAG, "bundle: "+(User)bundle.getSerializable("bundle"));

        String username = intent.getStringExtra("username");
        Log.d(TAG, "username: "+username);
        Log.d(TAG, "user: "+user.getUsername());


        fabPop = findViewById(R.id.fab_pop);
        fabPop.setOnClickListener(v -> {
            //인증이 성공함
            //setResult(RESULT_OK); // -1 , 나는 이 제공키값 사용 안함
            Intent newIntent = new Intent();
            newIntent.putExtra("auth","ok");
            setResult(1,newIntent);
            finish(); //pop = 내 액티비티를 스택에서 날림
        });
    }
}