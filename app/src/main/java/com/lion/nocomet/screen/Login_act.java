package com.lion.nocomet.screen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.lion.nocomet.app.R;
import com.lion.nocomet.login.SmtLogIn;

public class Login_act extends AppCompatActivity {

    SmtLogIn login;

    private ImageView btn_login;
    private EditText edit_id;
    private EditText edit_pass;
    private EditText edit_snumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_act);
        login = new SmtLogIn(this);
        settingView();
    }

    public void settingView()
    {
        edit_id=(EditText)findViewById(R.id.login_act_edit_id);
        edit_pass=(EditText)findViewById(R.id.login_act_edit_pass);
        edit_snumber=(EditText)findViewById(R.id.login_act_edit_snumber);
        btn_login=(ImageView)findViewById(R.id.login_act_btn_loginkey);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent in = new Intent(getApplicationContext(), main_act.class);
                //startActivity(in);
                //finish();
                Toast.makeText(getApplicationContext(),"로그인 중 입니다. 기다려주세요",Toast.LENGTH_LONG).show();
                new ProcessTask(getApplicationContext()).execute(null,null,null);
            }
        });
    }
    private class ProcessTask extends AsyncTask<Void, String, Void> {


        Intent intent;

        String stuId, stuPw, stuNum;

        Toast SuccessToast, FailToast;

        ProcessTask(Context c) {



            stuId = edit_id.getText().toString();
            stuPw = edit_pass.getText().toString();
            stuNum = edit_snumber.getText().toString();

            intent = new Intent(c, main_act.class);

            SuccessToast = Toast.makeText(c, "로그인", Toast.LENGTH_SHORT);
            FailToast = Toast.makeText(c, "로그인 실패", Toast.LENGTH_SHORT);
        }

        @Override
        protected void onProgressUpdate(String... values) {
            //화면 조정, main thread

            if (values[0].equals("true")) {
                SuccessToast.show();
                startActivity(intent);
                finish();
            }
            else
                FailToast.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try
            {
                boolean isTrue = login.login(stuNum, stuId, stuPw, false);

                publishProgress(String.valueOf(isTrue));
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return null;
        }
    }
}

