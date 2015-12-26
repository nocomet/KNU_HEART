package com.lion.nocomet.app;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lion.nocomet.foundation.groupList;
import com.lion.nocomet.server.group.getGroupInfo;

public class act2_group_info extends AppCompatActivity {

    EditText edit;
    Button btn1;
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act2_group_info);
        edit = (EditText) findViewById(R.id.edit1);
        text = (TextView)findViewById(R.id.text1);
        btn1=(Button)findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edit.getText().toString().equals("0")) {
                    getGroupInfo info = new getGroupInfo(getApplicationContext());
                    String result = info.getInfoAll();
                    Log.i("server", "act2_group_info >> result " + result);
                    //----------------------------------------------------------------------
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    groupList.getInstance().getRandomGroupSix('0');
                    Log.i("server", "act2_group_info >> done ");
                    if(!result.contains("없다")) {
                        text.setText(result);

                    }
                    else
                    {
                        text.setText(result);
                    }
                }
                else
                {
                    getGroupInfo info = new getGroupInfo(getApplicationContext());
                    String result = info.getInfobyID(edit.getText().toString());
                    if(!result.contains("없다")) {
                        text.setText(result);
                        groupList.getInstance().setArray(result);
                    }
                    else
                    {
                        text.setText(result);
                    }
                }
            }
        });
    }

}
