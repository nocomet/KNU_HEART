package com.lion.nocomet.screen;


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
import com.lion.nocomet.foundation.group;
import com.lion.nocomet.foundation.groupList;
import com.lion.nocomet.foundation.login_Info;
import com.lion.nocomet.foundation.message;
import com.lion.nocomet.foundation.messageList;
import com.lion.nocomet.server.message.getMessage;
import com.lion.nocomet.server.message.getMessageList;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A simple {@link Fragment} subclass.
 */
public class Message_frag extends Fragment {

    private View V;
    private ListView listview;
    private ListViewAdapter mAdapter = null;

    public Message_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        V = inflater.inflate(R.layout.fragment_message_frag, container, false);

        mAdapter = new ListViewAdapter(getActivity().getApplicationContext());
        listview = (ListView) V.findViewById(R.id.message_frag_listview);

        getMessageList info = new getMessageList(getActivity().getApplicationContext());
        String result = info.getListByID(login_Info.getInstance().sid);
        if(!result.contains("없다")) {
            //text.setText(result);
            while(result.length() == 0) {
                try {
                    Thread.sleep(300);
                    Log.i("screen", "Message_frag >> slepp... result="+result+"**");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            messageList.getInstance().setList(result);
            ArrayList<group> glist = groupList.getInstance().list;
            group g;
            for(int i = 0 ; i <messageList.getInstance().list.size()-1; i ++)
            {
                message msg = messageList.getInstance().list.get(i);
                Log.i("screen", "Message_frag >> msg=" + msg.SendID);
                Log.i("screen", "Message_frag >> glist size=" + glist.size());
                for(int j = 0 ; j < glist.size() ; j++)
                {
                    if(msg.SendID.equals(glist.get(j).sid))
                    {
                        g=glist.get(j);
                        mAdapter.addItem(g.sid,g.dept, g.name1, g.name2, g.name3);
                        break;
                    }
                }

            }
        }
        else
        {
            //text.setText(result);
            Toast.makeText(getActivity().getApplicationContext(),"받은 메세지가 없습니다.",Toast.LENGTH_SHORT).show();
        }


//        mAdapter.addItem("asd", "김철수1", "zxc", "asd");
//        mAdapter.addItem("123", "김철수2", "fef", "dd");
//        mAdapter.addItem("456", "김철수3", "wewe", "asd");
//        mAdapter.addItem("7777", "김철수4", "dddd", "qqqr");

        listview.setAdapter(mAdapter);


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Message_listview_item mData = mAdapter.mListData.get(position);
                //Toast.makeText(getActivity().getApplicationContext(), mData.send_dept, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getActivity().getApplicationContext(),Chatting_act.class);
                intent.putExtra("SID",mData.send_sid);
                intent.putExtra("DEPT",mData.send_dept);
                intent.putExtra("NAME1",mData.send_name1);
                intent.putExtra("NAME2",mData.send_name2);
                intent.putExtra("NAME3",mData.send_name3);

                startActivity(intent);
            }
        });
        return V;
    }


    private class ListViewAdapter extends BaseAdapter {
        private Context mContext = null;
        private ArrayList<Message_listview_item> mListData = new ArrayList<Message_listview_item>();

        public ListViewAdapter(Context mContext) {
            super();
            this.mContext = mContext;
        }

        @Override
        public int getCount() {
            return mListData.size();
        }

        @Override
        public Object getItem(int position) {
            return mListData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public void addItem(String sid,String dept, String name1, String name2, String name3) {
            Message_listview_item addInfo = null;
            addInfo = new Message_listview_item();
            addInfo.send_sid = sid;
            addInfo.send_dept = dept;
            addInfo.send_name1 = name1;
            addInfo.send_name2 = name2;
            addInfo.send_name3 = name3;

            mListData.add(addInfo);
        }

        public void remove(int position) {
            mListData.remove(position);
            dataChange();
        }

        private class ViewHolder {
            public TextView text_dept;
            public TextView text_name1;
            public TextView text_name2;
            public TextView text_name3;
        }

        public void dataChange() {
            mAdapter.notifyDataSetChanged();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();

                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.message_list_item, null);

                holder.text_dept = (TextView) convertView.findViewById(R.id.message_list_item_text_dept);
                holder.text_name1 = (TextView) convertView.findViewById(R.id.message_list_item_text_name1);
                holder.text_name2 = (TextView) convertView.findViewById(R.id.message_list_item_text_name2);
                holder.text_name3 = (TextView) convertView.findViewById(R.id.message_list_item_text_name3);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }


            Message_listview_item mData = mListData.get(position);


            holder.text_dept.setText("" + mData.send_dept);
            holder.text_name1.setText("" + mData.send_name1);
            holder.text_name2.setText("" + mData.send_name2);
            holder.text_name3.setText("" + mData.send_name3);


            Log.i("screen", "message_list_item >> name1=" + mData.send_name1);

            return convertView;
        }
    }

}
