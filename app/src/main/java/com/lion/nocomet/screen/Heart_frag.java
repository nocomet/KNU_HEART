package com.lion.nocomet.screen;


import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lion.nocomet.app.R;
import com.lion.nocomet.foundation.completeList;
import com.lion.nocomet.foundation.group;
import com.lion.nocomet.foundation.groupList;
import com.lion.nocomet.foundation.login_Info;
import com.lion.nocomet.server.group.acceptGroup;
import com.lion.nocomet.server.group.getComplete;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Heart_frag extends Fragment {

    private Fragment myF;
    private View V;
    ArrayList<Listview_item_heart> data_choice;
    ArrayList<Listview_item_heart_match> data_match;
    ListviewAdapter adapter;
    ListviewAdapter_match adapter_match;
    CustomDialog mCustomDialog;
    int selectNum;
    group g=null;
    public Heart_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        V=inflater.inflate(R.layout.fragment_heart_frag, container, false);




        settingView();
        myF =this;
        return V;
    }

    public void settingView() {

        getComplete getcomplete = new getComplete(getActivity().getApplicationContext());
        Log.i("screen", "Heart_frag >> getComplete sid >>" + login_Info.getInstance().sid);
        String result = getcomplete.getComplete(login_Info.getInstance().sid);
        Log.i("screen", "Heart_frag >> result >>" + result);
        completeList.getInstance().setArray(result);

        String recv_question_message="님이 과팅요청을 보냈습니다.";
        ListView listView_choice = (ListView) V.findViewById(R.id.fragment_heart_choice_listview);
        ListView listView_match = (ListView) V.findViewById(R.id.fragment_heart_match_listview);

        data_choice = new ArrayList<>();
        data_match = new ArrayList<>();

        for(int i = 0 ; i <completeList.getInstance().list.size(); i++)
        {
            if(completeList.getInstance().list.get(i).state=='0' && completeList.getInstance().list.get(i).womanID.equals(login_Info.getInstance().sid))
            {
                data_choice.add(new Listview_item_heart(R.drawable.cupidheart, completeList.getInstance().list.get(i).manID+recv_question_message,completeList.getInstance().list.get(i).manID));
            }
        }
        for(int i = 0 ; i <completeList.getInstance().list.size(); i++)
        {
            if(completeList.getInstance().list.get(i).state=='1')
            {
                group g=null;
                String parterID;
                if(login_Info.getInstance().sid.equals(completeList.getInstance().list.get(i).manID))
                    parterID=completeList.getInstance().list.get(i).womanID;
                else
                    parterID=completeList.getInstance().list.get(i).manID;

                for(int ii=0;ii< groupList.getInstance().list.size();ii++)
                {
                    if(groupList.getInstance().list.get(ii).sid.equals(parterID))
                    {
                        g=groupList.getInstance().list.get(ii);
                        break;
                    }
                }
                data_match.add(new Listview_item_heart_match(R.drawable.match_heart, g.dept,g.name1,g.name2,g.name3));
            }
        }
        if(data_choice.size()==0)
        {
            data_choice.add(new Listview_item_heart(R.drawable.cupidheart, "받은 과팅 요청이 없습니다ㅠ_ㅠ"," "));
            adapter = new ListviewAdapter(getActivity().getApplicationContext(), R.layout.heart_listitem, data_choice);
            listView_choice.setAdapter(adapter);
            listView_choice.setOnItemClickListener(null);
        }
        else
        {
            adapter = new ListviewAdapter(getActivity().getApplicationContext(), R.layout.heart_listitem, data_choice);
            listView_choice.setAdapter(adapter);
            listView_choice.setOnItemClickListener(itemClickListenerOfLanguageList);
        }

        if(data_match.size()==0)
        {
            data_match.add(new Listview_item_heart_match(R.drawable.match_heart, "연결된 과팅이 없습니다ㅠ_ㅠ", " ", " ", " "));
            adapter_match = new ListviewAdapter_match(getActivity().getApplicationContext(), R.layout.heart_match_listitem, data_match);
            listView_match.setAdapter(adapter_match);
            listView_match.setOnItemClickListener(null);
        }
        else {
            adapter_match = new ListviewAdapter_match(getActivity().getApplicationContext(), R.layout.heart_match_listitem, data_match);
            listView_match.setAdapter(adapter_match);
            listView_match.setOnItemClickListener(itemClickListenerOfLanguageList2);
        }

    }


    ////////////////밑에꺼 listview
    public class ListviewAdapter_match extends BaseAdapter {
        private LayoutInflater inflater;
        private ArrayList<Listview_item_heart_match> data;
        private int layout;

        public ListviewAdapter_match(Context context, int layout, ArrayList<Listview_item_heart_match> data_match) {
            this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            this.data = data_match;
            this.layout = layout;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public String getItem(int position) {
            return data.get(position).getMatchmajor();
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(layout, parent, false);
            }
            Listview_item_heart_match listviewitem_match = data.get(position);

            ImageView icon = (ImageView) convertView.findViewById(R.id.heart_listitem_image_match);
            icon.setImageResource(listviewitem_match.getMatchimage());

            TextView major = (TextView) convertView.findViewById(R.id.heart_listitem_text_match_major);
            major.setText(listviewitem_match.getMatchmajor());

            TextView name1 = (TextView) convertView.findViewById(R.id.heart_listitem_text_match_name1);
            name1.setText(listviewitem_match.getMatchname1());

            TextView name2 = (TextView) convertView.findViewById(R.id.heart_listitem_text_match_name2);
            name2.setText(listviewitem_match.getMatchname2());

            TextView name3 = (TextView) convertView.findViewById(R.id.heart_listitem_text_match_name3);
            name3.setText(listviewitem_match.getMatchname3());




            return convertView;
        }
    }




    public class ListviewAdapter extends BaseAdapter {
        private LayoutInflater inflater;
        private ArrayList<Listview_item_heart> data;
        private int layout;

        public ListviewAdapter(Context context, int layout, ArrayList<Listview_item_heart> data_choice) {
            this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            this.data = data_choice;
            this.layout = layout;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public String getItem(int position) {
            return data.get(position).getRecvmessage();
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(layout, parent, false);
            }
            Listview_item_heart listviewitem = data.get(position);

            ImageView icon = (ImageView) convertView.findViewById(R.id.heart_listitem_image_recv);
            icon.setImageResource(listviewitem.getRecvimage());

            TextView message = (TextView) convertView.findViewById(R.id.heart_listitem_text_recv);
            message.setText(listviewitem.getRecvmessage());



            return convertView;
        }
    }

    // 수락할껀지 리스트
    private AdapterView.OnItemClickListener itemClickListenerOfLanguageList=new AdapterView.OnItemClickListener()
    {
        public void onItemClick(AdapterView<?> adapterView,View clickedView,int pos,long id)
        {
            selectNum = pos;
            String manID=data_choice.get(selectNum).id;
            for(int i = 0 ; i < groupList.getInstance().list.size() ; i++)
            {
                if(manID.equals(groupList.getInstance().list.get(i).sid))
                {
                    g=groupList.getInstance().list.get(i);
                    break;
                }
            }
            mCustomDialog=new CustomDialog(getActivity(),leftListener,rightListener,g.dept,g.name1,g.name2,g.name3,g.line);

            mCustomDialog.show();

        }
    };
    private View.OnClickListener leftListener = new View.OnClickListener() {
        public void onClick(View v) {
            //Toast.makeText(getActivity().getApplicationContext(), "왼쪽버튼 클릭", Toast.LENGTH_SHORT).show();
            String manID=data_choice.get(selectNum).id;
            String womanID=login_Info.getInstance().sid;
            acceptGroup ag = new acceptGroup(getActivity().getApplicationContext());
            ag.accept(manID, womanID);

            Toast.makeText(getActivity(),"수락되었습니다. 상대에게 첫 쪽지를 보내보세요.",Toast.LENGTH_LONG).show();
            /*
            group g=null;
            for(int i = 0 ; i < groupList.getInstance().list.size() ; i++)
            {
                if(manID.equals(groupList.getInstance().list.get(i).sid))
                {
                    g=groupList.getInstance().list.get(i);
                    break;
                }
            }
            */
            //Message_listview_item mData = mAdapter.mListData.get(position);
            //Toast.makeText(getActivity().getApplicationContext(), mData.send_dept, Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(getActivity().getApplicationContext(),Chatting_act.class);
            intent.putExtra("SID",g.sid);
            intent.putExtra("DEPT",g.dept);
            intent.putExtra("NAME1",g.name1);
            intent.putExtra("NAME2", g.name2);
            intent.putExtra("NAME3", g.name3);

            startActivity(intent);


            mCustomDialog.dismiss();
            getActivity().finish();
        }
    };

    private View.OnClickListener rightListener = new View.OnClickListener() {
        public void onClick(View v) {
            Toast.makeText(getActivity().getApplicationContext(), "거절되었습니다.", Toast.LENGTH_SHORT).show();
            mCustomDialog.dismiss();
        }
    };


    //수락된 리스트
    private AdapterView.OnItemClickListener itemClickListenerOfLanguageList2=new AdapterView.OnItemClickListener()
    {
        public void onItemClick(AdapterView<?> adapterView,View clickedView,int pos,long id)
        {
            //String toastMessage=((TextView)clickedView.findViewById(R.id.heart_listitem_text_match_major)).getText().toString();
            //Toast.makeText(getActivity().getApplicationContext(), toastMessage, Toast.LENGTH_SHORT).show();
        }
    };


}