package com.lion.nocomet.screen;

import android.app.FragmentTransaction;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.lion.nocomet.app.R;
import com.lion.nocomet.foundation.group;
import com.lion.nocomet.foundation.groupList;
import com.lion.nocomet.foundation.login_Info;
import com.lion.nocomet.login.UserInfo;
import com.lion.nocomet.login.UserProfile;
import com.lion.nocomet.server.group.getGroupInfo;

import java.util.ArrayList;

public class main_act extends AppCompatActivity {

    private ImageView btn_home;
    private ImageView btn_heart;
    private ImageView btn_mail;
    private ImageView btn_setting;
    UserProfile userProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_act);

        settingView();

        userProfile = new UserProfile(getApplicationContext());
        new ProcessTask(getApplicationContext()).execute(null, null, null);


        if (getIntent().getStringExtra("FLAG") != null && getIntent().getStringExtra("FLAG").equals("MESSAGE")) {
            FragmentTransaction trans = getFragmentManager().beginTransaction();
            trans.replace(R.id.home_act_container, new Message_frag());
            //trans.addToBackStack(null);
            trans.commit();
        } else {
            FragmentTransaction trans = getFragmentManager().beginTransaction();
            trans.replace(R.id.home_act_container, new Home_frag());
            //trans.addToBackStack(null);
            trans.commit();
        }
    }

    public void settingView() {
        btn_home = (ImageView) findViewById(R.id.home_act_btn_home);
        btn_heart = (ImageView) findViewById(R.id.home_act_btn_heart);
        btn_mail = (ImageView) findViewById(R.id.home_act_btn_message);
        btn_setting = (ImageView) findViewById(R.id.home_act_btn_setting);

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction trans = getFragmentManager().beginTransaction();
                trans.replace(R.id.home_act_container, new Home_frag());
                //trans.addToBackStack(null);
                trans.commit();

            }
        });
        btn_heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction trans = getFragmentManager().beginTransaction();
                trans.replace(R.id.home_act_container, new Heart_frag());
                //trans.addToBackStack(null);
                trans.commit();
            }
        });
        btn_mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction trans = getFragmentManager().beginTransaction();
                trans.replace(R.id.home_act_container, new Message_frag());
                //trans.addToBackStack(null);
                trans.commit();
            }
        });
        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction trans = getFragmentManager().beginTransaction();
                trans.replace(R.id.home_act_container, new Setting_frag());
                //trans.addToBackStack(null);
                trans.commit();
            }
        });
    }

    private class ProcessTask extends AsyncTask<Void, String, Void> {


        UserInfo userInfo;

        ProcessTask(Context c) {
            // view = (TextView) V.findViewById(R.id.home_frag_text);
        }

        @Override
        protected void onProgressUpdate(String... values) {
            //화면 조정, main thread

            String info = userInfo.getUserName() + " : " + userInfo.getMajor() + " : " + userInfo.getStuNbr();

            //view.setText(info);
            Log.i("screen", "Home_frag >> AsyncTask login info=" + info);
            info = info.trim();

            int start = info.indexOf('(');
            if (start != -1) {
                Log.i("screen", "Home_frag >> AsyncTask login if stmt");
                String tttt = info.substring(0, start);
                int end = info.indexOf(')');
                tttt += info.substring(end + 1, info.length());
                Log.i("screen", "Home_frag >> AsyncTask login tttt=" + tttt);
                info = tttt;
                String temp[] = info.split(" ");
                for (int i = 0; i < temp.length; i++) {
                    Log.i("screen", "Home_frag >> AsyncTask temp[" + i + "]=" + temp[i]);
                }
                login_Info.getInstance().sid = temp[5];
                login_Info.getInstance().name = temp[0];
                login_Info.getInstance().dept = temp[3];

            } else {


                String temp[] = info.split(" ");
                for (int i = 0; i < temp.length; i++) {
                    Log.i("screen", "Home_frag >> AsyncTask temp[" + i + "]=" + temp[i]);
                }

//            login_Info.getInstance().sid=temp[6];
//            login_Info.getInstance().name=temp[0];
//            login_Info.getInstance().dept=temp[4];
                login_Info.getInstance().sid = temp[4];
                login_Info.getInstance().name = temp[0];
                login_Info.getInstance().dept = temp[2];

            }

            getGroupInfo ginfo = new getGroupInfo(getApplicationContext());
            String result = ginfo.getInfoAll();
            //----------------------------------------------------------------------//
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.i("screen", "Main_act >> groupLst.getInstance list size=" + groupList.getInstance().list.size());
            ArrayList<group> glist = groupList.getInstance().list;

            int i;
            Log.i("screen", "Main_act >> login_Info.getInstance std=" + login_Info.getInstance().sid);
            for (i = 0; i < glist.size(); i++) {
                if (login_Info.getInstance().sid.equals(glist.get(i).sid)) {
                    Log.i("screen", "Main_act >> already regist group.");
                    login_Info.getInstance().isRegist = true;
                    break;
                }
            }
            if (i == glist.size()) {
                Log.i("screen", "Main_act >> don't regist group.");
                login_Info.getInstance().isRegist = false;
            }
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                userProfile.user();

                userInfo = userProfile.getUserInfo();

                publishProgress();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
