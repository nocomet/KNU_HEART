package com.lion.nocomet.app;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.lion.nocomet.screen.Login_act;

public class MainActivity extends Activity {

    //private Button btn1;
    //private Button btn2;
    //private Button btn3;
    //private Button btn4;
    private RelativeLayout btn4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
//        btn1 = (Button)findViewById(R.id.btn1);
//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent1 = new Intent(getApplicationContext(), act1_group.class);
//                startActivity(intent1);
//            }
//        });
//        btn2 = (Button)findViewById(R.id.btn2);
//        btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent1 = new Intent(getApplicationContext(), act2_group_info.class);
//                startActivity(intent1);
//
//            }
//        });
//        btn3 = (Button)findViewById(R.id.btn3);
//        btn3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent1 = new Intent(getApplicationContext(), act3_message.class);
//                startActivity(intent1);
//
//            }
//        });
        //btn4 = (Button)findViewById(R.id.btn4);
        btn4 = (RelativeLayout)findViewById(R.id.btn4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), Login_act.class);
                startActivity(intent1);

            }
        });
    }
}
