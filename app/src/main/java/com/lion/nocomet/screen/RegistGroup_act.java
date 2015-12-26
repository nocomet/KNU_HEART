package com.lion.nocomet.screen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lion.nocomet.app.R;
import com.lion.nocomet.foundation.login_Info;
import com.lion.nocomet.server.group.registGroup;

public class RegistGroup_act extends AppCompatActivity {

    private TextView text_dept;
    private EditText edit_name1;
    private EditText edit_name2;
    private EditText edit_name3;
    private EditText edit_line;
    private LinearLayout btn_regist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist_group_act);
        settingView();
    }
    public void settingView()
    {
        text_dept=(TextView)findViewById(R.id.registGroup_act_text_dept);
        text_dept.setText(login_Info.getInstance().dept);
        edit_name1=(EditText)findViewById(R.id.registGroup_act_edit_name1);
        edit_name1.setText(login_Info.getInstance().name);
        edit_name2=(EditText)findViewById(R.id.registGroup_act_edit_name2);
        edit_name3=(EditText)findViewById(R.id.registGroup_act_edit_name3);
        edit_line=(EditText)findViewById(R.id.registGroup_act_edit_line);

        btn_regist=(LinearLayout)findViewById(R.id.registGroup_act_btn_regist);
        btn_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                registGroup regist = new registGroup(getApplicationContext());
                regist.regist(login_Info.getInstance().sid,
                        edit_name1.getText().toString(),
                        edit_name2.getText().toString(),
                        edit_name3.getText().toString(),
                        edit_line.getText().toString(),
                        login_Info.getInstance().dept,
                        "0"
                );
                Toast.makeText(getApplicationContext(),"과팅등록이 완료되었습니다.",Toast.LENGTH_SHORT).show();
                login_Info.getInstance().isRegist=true;
                finish();
            }
        });
    }
}
