package com.lion.nocomet.screen;

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
import com.lion.nocomet.foundation.login_Info;
import com.lion.nocomet.server.group.completeGroup;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class TourFinal_act extends AppCompatActivity {

    String sid[];
    ArrayList<group> temp,list;
    TextView dept1,dept2,dept3;
    TextView name1_1,name1_2,name1_3;
    TextView name2_1,name2_2,name2_3;
    TextView name3_1,name3_2,name3_3;
    LinearLayout select1,select2,select3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_final_act);
        sid=new String[3];
        sid[0]=getIntent().getStringExtra("SID1");
        sid[1]=getIntent().getStringExtra("SID2");
        sid[2]=getIntent().getStringExtra("SID3");
        list = new ArrayList<group>();
        temp=groupList.getInstance().list;
        for(int i = 0 ; i < 3 ; i ++)
        {
            Log.i("screen","TourFinal_act >> sid["+i+"]="+sid[i]);
            for(int ii = 0 ; ii < temp.size() ; ii++)
            {
                if(sid[i].equals(temp.get(ii).sid))
                {
                    list.add(temp.get(ii));
                    break;
                }
            }
        }
        settingView();

    }
    public void settingView()
    {
        dept1=(TextView)findViewById(R.id.Tour_final_act_team1_title);
        dept2=(TextView)findViewById(R.id.Tour_final_act_team2_title);
        dept3=(TextView)findViewById(R.id.Tour_final_act_team3_title);
        name1_1=(TextView)findViewById(R.id.Tour_final_act_team1_name1);
        name1_2=(TextView)findViewById(R.id.Tour_final_act_team1_name2);
        name1_3=(TextView)findViewById(R.id.Tour_final_act_team1_name3);
        name2_1=(TextView)findViewById(R.id.Tour_final_act_team2_name1);
        name2_2=(TextView)findViewById(R.id.Tour_final_act_team2_name2);
        name2_3=(TextView)findViewById(R.id.Tour_final_act_team2_name3);
        name3_1=(TextView)findViewById(R.id.Tour_final_act_team3_name1);
        name3_2=(TextView)findViewById(R.id.Tour_final_act_team3_name2);
        name3_3=(TextView)findViewById(R.id.Tour_final_act_team3_name3);

        dept1.setText(list.get(0).dept);
        dept2.setText(list.get(1).dept);
        dept3.setText(list.get(2).dept);

        name1_1.setText(list.get(0).name1);
        name1_2.setText(list.get(0).name2);
        name1_3.setText(list.get(0).name3);

        name2_1.setText(list.get(1).name1);
        name2_2.setText(list.get(1).name2);
        name2_3.setText(list.get(1).name3);

        name3_1.setText(list.get(2).name1);
        name3_2.setText(list.get(2).name2);
        name3_3.setText(list.get(2).name3);

        select1=(LinearLayout)findViewById(R.id.Tour_final_act_Team1_layout);
        select2=(LinearLayout)findViewById(R.id.Tour_final_act_Team2_layout);
        select3=(LinearLayout)findViewById(R.id.Tour_final_act_Team3_layout);

        select1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"최종선택하셨습니다. 상대의 수락을 기다리세요.",Toast.LENGTH_SHORT).show();
                completeGroup cg = new completeGroup(getApplicationContext());
                cg.registComplete(login_Info.getInstance().sid, sid[0]);
                finish();
            }
        });
        select2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"최종선택하셨습니다. 상대의 수락을 기다리세요.",Toast.LENGTH_SHORT).show();
                completeGroup cg = new completeGroup(getApplicationContext());
                cg.registComplete(login_Info.getInstance().sid, sid[1]);
                finish();
            }
        });
        select3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"최종선택하셨습니다. 상대의 수락을 기다리세요.",Toast.LENGTH_SHORT).show();
                completeGroup cg = new completeGroup(getApplicationContext());
                cg.registComplete(login_Info.getInstance().sid, sid[2]);
                finish();
            }
        });
    }
}
