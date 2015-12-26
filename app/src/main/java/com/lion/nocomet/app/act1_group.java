package com.lion.nocomet.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.lion.nocomet.server.group.registGroup;

public class act1_group extends AppCompatActivity {

    EditText edit1;
    EditText edit2;
    EditText edit3;
    EditText edit4;
    EditText edit5;
    EditText edit6;

    registGroup regist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act1_group);
        edit1=(EditText)findViewById(R.id.edit1);
        edit2=(EditText)findViewById(R.id.edit2);
        edit3=(EditText)findViewById(R.id.edit3);
        edit4=(EditText)findViewById(R.id.edit4);
        edit5=(EditText)findViewById(R.id.edit5);
        edit6=(EditText)findViewById(R.id.edit6);

        regist = new registGroup(getApplicationContext());
    }

    public void onClickBtn(View v)
    {
        regist.regist(edit1.getText().toString(),
                edit2.getText().toString(),
                edit3.getText().toString(),
                edit4.getText().toString(),
                edit5.getText().toString(),
                edit6.getText().toString(),
                "0" );
        Toast.makeText(this,"등록완료",Toast.LENGTH_SHORT).show();
    }
}
