package com.lion.nocomet.screen;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lion.nocomet.app.R;
import com.lion.nocomet.foundation.login_Info;
import com.lion.nocomet.login.UserInfo;
import com.lion.nocomet.login.UserProfile;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home_frag extends Fragment {

    private View V;

    private ImageView imgview_1;
    private ImageView imgview_2;

    public Home_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        V=inflater.inflate(R.layout.fragment_home_frag, container, false);




        imgview_1=(ImageView)V.findViewById(R.id.image2);
        imgview_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(),Tour_act.class);
                startActivity(intent);
            }
        });
        imgview_2=(ImageView)V.findViewById(R.id.image1);
        imgview_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(login_Info.getInstance().isRegist==false) {
                    Intent intent = new Intent(getActivity().getApplicationContext(), RegistGroup_act.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getActivity().getApplicationContext(), "이미 등록하셨습니다. 관리창으로 가세요", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return V;
    }


}
