package com.lion.nocomet.screen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lion.nocomet.app.R;
import com.lion.nocomet.foundation.group;
import com.lion.nocomet.foundation.groupList;
import com.lion.nocomet.server.group.getGroupInfo;

import java.util.ArrayList;
import java.util.Random;

public class Tour_act extends AppCompatActivity {

    private ArrayList<group> list;
    private boolean listFlag[];
    private TextView txt_team1_name1;
    private TextView txt_team1_name2;
    private TextView txt_team1_name3;
    private TextView txt_team1_dept;

    private TextView txt_team2_name1;
    private TextView txt_team2_name2;
    private TextView txt_team2_name3;
    private TextView txt_team2_dept;

    private LinearLayout layout_up;
    private LinearLayout layout_down;

    private TextView txt_round;
    private int CurrentRound;
    private ArrayList<group> FinalList;
    private group up, down;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_act);
        getListInfo();
        CurrentRound=1;

        listFlag = new boolean[list.size()];
        FinalList = new ArrayList<group>();
        for(int i =0; i< list.size(); i++)
        {
            group gg=list.get(i);
            Log.i("screen","list array content "+gg.dept+" "+gg.name1+" "+gg.name2+" "+gg.name3);
        }
        for(int i = 0 ; i < listFlag.length; i++)
        {
            listFlag[i]=false;
        }
        settingView();

        //맨처음 한번
        setTournament();
    }
    public void settingView()
    {
        txt_team1_name1=(TextView)findViewById(R.id.Tournament_frag_team1_name1);
        txt_team1_name2=(TextView)findViewById(R.id.Tournament_frag_team1_name2);
        txt_team1_name3=(TextView)findViewById(R.id.Tournament_frag_team1_name3);
        txt_team1_dept = (TextView)findViewById(R.id.Tournament_frag_team1_title);

        txt_team2_name1=(TextView)findViewById(R.id.Tournament_frag_team2_name1);
        txt_team2_name2=(TextView)findViewById(R.id.Tournament_frag_team2_name2);
        txt_team2_name3=(TextView)findViewById(R.id.Tournament_frag_team2_name3);
        txt_team2_dept = (TextView)findViewById(R.id.Tournament_frag_team2_title);

        txt_round=(TextView)findViewById(R.id.Tournament_frag_text_Round);

        layout_up =(LinearLayout)findViewById(R.id.Tournament_frag_Team1_layout);
        layout_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CurrentRound!=3)
                {
                    CurrentRound++;
                    FinalList.add(up);
                    txt_round.setText("Round "+CurrentRound);
                    setTournament();
                }
                else
                {
                    FinalList.add(up);
                    Toast.makeText(getApplicationContext(),"파이널선택하세요",Toast.LENGTH_SHORT).show();
                    Log.i("screen", "Tour_act >> final -------for start--------");
                    for(int i = 0 ;  i<FinalList.size();i++) {
                        Log.i("screen", "Tour_act >> final i="+i+" dept="+FinalList.get(i).dept+" name1="+FinalList.get(i).name1+" sid="+FinalList.get(i).sid);
                    }
                    Log.i("screen", "Tour_act >> final -------for end----------");
                    Intent intent = new Intent(getApplicationContext(), TourFinal_act.class);
                    intent.putExtra("SID1", FinalList.get(0).sid);
                    intent.putExtra("SID2", FinalList.get(1).sid);
                    intent.putExtra("SID3", FinalList.get(2).sid);
                    startActivity(intent);
                    finish();
                }
            }
        });
        layout_down =(LinearLayout)findViewById(R.id.Tournament_frag_Team2_layout);
        layout_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CurrentRound!=3)
                {
                    CurrentRound++;
                    FinalList.add(down);
                    setTournament();
                    txt_round.setText("Round " + CurrentRound);
                }
                else
                {
                    FinalList.add(down);
                    Toast.makeText(getApplicationContext(),"파이널선택하세요",Toast.LENGTH_SHORT).show();
                    Log.i("screen", "Tour_act >> final -------for start--------");
                    for(int i = 0 ;  i<FinalList.size();i++) {
                        Log.i("screen", "Tour_act >> final i="+i+" dept="+FinalList.get(i).dept+" name1="+FinalList.get(i).name1);
                    }
                    Log.i("screen", "Tour_act >> final -------for end----------");

                    Intent intent = new Intent(getApplicationContext(), TourFinal_act.class);
                    startActivity(intent);
                    intent.putExtra("SID1",FinalList.get(0).sid);
                    intent.putExtra("SID2",FinalList.get(1).sid);
                    intent.putExtra("SID3",FinalList.get(2).sid);
                    finish();
                }
            }
        });
    }

    public void setTournament()//3번만 불려야한다.
    {
        Log.i("screen","Tour_act >> setTournament run cur="+CurrentRound);
        Random random = new Random();
        int num;
        group g;
        for(int i = 0 ; i<2; i ++)
        {
            num = random.nextInt(list.size());
            Log.i("screen","Tour_act >> setTournament for stmt i="+i+" num="+num);
            if(listFlag[num]==false)
            {
                listFlag[num]=true;
                if(i==0)
                {
                    g=list.get(num);
                    txt_team1_name1.setText(g.name1);
                    txt_team1_name2.setText(g.name2);
                    txt_team1_name3.setText(g.name3);
                    txt_team1_dept.setText(g.dept);
                    up = g;
                }
                else
                {
                    g=list.get(num);
                    txt_team2_name1.setText(g.name1);
                    txt_team2_name2.setText(g.name2);
                    txt_team2_name3.setText(g.name3);
                    txt_team2_dept.setText(g.dept);
                    down = g;

                }
            }
            else
            {
                i--;
            }
        }
    }

    public void getListInfo()
    {
        getGroupInfo info = new getGroupInfo(getApplicationContext());
        String result = info.getInfoAll();
        //----------------------------------------------------------------------//
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //★수정
        //로그인한 사람이 남자인지 여자인지 구분해서 '0' 인지, '1' 인지 넣어주기
        //'0'을 넣으면 남자그룹들이 후보에 나온다.
        list=groupList.getInstance().getRandomGroupSix('1');
        Log.i("server", "Tour_act >> list arraylist size=" + list.size());
    }


}
