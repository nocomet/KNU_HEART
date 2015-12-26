package com.lion.nocomet.screen;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.lion.nocomet.app.R;
import com.lion.nocomet.foundation.group;
import com.lion.nocomet.foundation.groupList;
import com.lion.nocomet.foundation.login_Info;

/**
 * A simple {@link Fragment} subclass.
 */
public class Setting_frag extends Fragment {

    private TextView dept, names;
    private EditText line;

    public Setting_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View V= inflater.inflate(R.layout.fragment_setting_frag, container, false);
        dept = (TextView)V.findViewById(R.id.Setting_frag_text_dept);
        dept.setText(login_Info.getInstance().dept);
        line=(EditText)V.findViewById(R.id.Setting_frag_edit_line);
        names = (TextView)V.findViewById(R.id.Setting_frag_text_names);

        group g=null;
        for(int i = 0 ; i < groupList.getInstance().list.size(); i++)
        {
            if(login_Info.getInstance().sid.equals(groupList.getInstance().list.get(i).sid))
            {
                g=groupList.getInstance().list.get(i);
                break;
            }
        }
        names.setText(g.name1+" "+g.name2+" "+g.name3);
        line.setText(g.line);

        return V;
    }


}
