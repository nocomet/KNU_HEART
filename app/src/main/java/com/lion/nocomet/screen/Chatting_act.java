package com.lion.nocomet.screen;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.lion.nocomet.app.R;
import com.lion.nocomet.foundation.group;
import com.lion.nocomet.foundation.groupList;
import com.lion.nocomet.foundation.login_Info;
import com.lion.nocomet.foundation.messageList;
import com.lion.nocomet.login.UserInfo;
import com.lion.nocomet.server.group.getGroupInfo;
import com.lion.nocomet.server.message.getMessage;
import com.lion.nocomet.server.message.sendMessage;
import com.lion.nocomet.foundation.message;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Chatting_act extends AppCompatActivity {

    private EditText edit_text;
    private TextView text_dept;
    private TextView text_name1;
    private Button btn_send;
    private ListView listview;
    private ListViewAdapter mAdapter = null;
    private sendMessage message;
    private int chatNum;
    private ProcessTask task;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting_act);

        chatNum=-1;
        settingView();

        //Log.i("screen", "Chatting_act >> getIntent SID=" + getIntent().getStringExtra("SID"));
        //Log.i("screen", "Chatting_act >> getIntent DEPT=" + getIntent().getStringExtra("DEPT"));
        listview = (ListView) findViewById(R.id.chatting_act_listview);
//        mAdapter.addItem("안녕하세요",true);
//        mAdapter.addItem("ㅇㅇㅎㅇ",false);
//        mAdapter.addItem("반갑습니다.",true);
//        mAdapter.addItem("즐염",false);
//        mAdapter.addItem("야 디질래.",true);
//        mAdapter.addItem("죽고싶냐.",true);
//        mAdapter.addItem("ㄴㄴㄴㄴㄴ",false);
//        mAdapter.addItem("ㅂㅂ", false);
        task=new ProcessTask(getApplicationContext());
        task.execute(null, null, null);

    }
    public void settingView()
    {
        message = new sendMessage(getApplicationContext());
        edit_text=(EditText)findViewById(R.id.chatting_act_edit);
        text_dept=(TextView)findViewById(R.id.chatting_act_text_dept);
        text_dept.setText(getIntent().getStringExtra("DEPT"));
        text_name1=(TextView)findViewById(R.id.chatting_act_text_name_list);
        text_name1.setText(getIntent().getStringExtra("NAME1")+" "+getIntent().getStringExtra("NAME2")+" "+getIntent().getStringExtra("NAME3"));
        btn_send=(Button)findViewById(R.id.chatting_act_btn_send);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                message.send(login_Info.getInstance().sid,
                        getIntent().getStringExtra("SID"),
                        edit_text.getText().toString());
                mAdapter.addItem(edit_text.getText().toString(), false);
                edit_text.setText("");
                mAdapter.notifyDataSetChanged();
                listview.setAdapter(mAdapter);
                chatNum++;
            }
        });
    }

    private class ListViewAdapter extends BaseAdapter {
        private Context mContext = null;
        private ArrayList<Chatting_listview_item> mListData = new ArrayList<Chatting_listview_item>();

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

        public void addItem(String content,boolean isLeft) {
            Chatting_listview_item addInfo = null;
            addInfo = new Chatting_listview_item(content,isLeft);


            Log.i("screen","Chatting_act >> addItem content="+content+" isLeft="+isLeft);
            mListData.add(addInfo);
        }

        public void remove(int position) {
            mListData.remove(position);
            dataChange();
        }

        private class ViewHolder {
            public TextView text_content;

        }

        public void dataChange() {
            mAdapter.notifyDataSetChanged();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder;
            holder = new ViewHolder();
            Chatting_listview_item mData = mListData.get(position);

            if(mData.isLeft==true) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.chatting_list_item_left, null);

                holder.text_content = (TextView) convertView.findViewById(R.id.chatting_item_left_text);


                Log.i("screen", "chatting_list_item >> left content=" + mData.content);

                holder.text_content.setText("" + mData.content);

            }
            else
            {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.chatting_list_item_right, null);

                holder.text_content = (TextView) convertView.findViewById(R.id.chatting_item_right_text);


                Log.i("screen", "chatting_list_item >> right content=" + mData.content);

                holder.text_content.setText("" + mData.content);
            }



            return convertView;
        }
    }
     class ProcessTask extends AsyncTask<Void, String, Void> {
        ProcessTask(Context c) {
            mAdapter = new ListViewAdapter(getApplicationContext());
        }

        @Override
        protected void onProgressUpdate(String... values) {
            //화면 조정, main thread

            getMessage info = new getMessage(getApplicationContext());
            String result = info.getSendByID(login_Info.getInstance().sid);
            if(!result.contains("없다")) {
                messageList.getInstance().setArray(result,getIntent().getStringExtra("SID"));
                Log.i("screen", "chatting_act >> listsize=" + messageList.getInstance().list.size());
                if(chatNum!=messageList.getInstance().list.size())//추가됐다 갱신
                {
                    com.lion.nocomet.foundation.message s=null;
                    boolean isLeft;
                    int i;
                    if(chatNum == -1)
                        i=0;
                    else
                        i=chatNum;
                    Log.i("screen", "chatting_act >> chatNum=" + chatNum);
                    for( ; i<messageList.getInstance().list.size(); i ++)
                    {

                        s=messageList.getInstance().list.get(i);
                        if(s.SendID.equals(login_Info.getInstance().sid)) // 오른쪽
                            isLeft=false;
                        else
                            isLeft=true;
                        Log.i("screen", "chatting_act >> mAdapter addItem s.sid="+s.SendID+" login.sid="+login_Info.getInstance().sid);
                        Log.i("screen", "chatting_act >> mAdapter addItem isLeft="+isLeft);
                        Log.i("screen", "chatting_act >> mAdapter addItem i="+i);
                        mAdapter.addItem(s.Content, isLeft);

                    }


                    mAdapter.notifyDataSetChanged();
                    listview.setAdapter(mAdapter);
                    chatNum=messageList.getInstance().list.size();
                }


            }
            else
            {
                chatNum=0;
            }


        }

        @Override
        protected Void doInBackground(Void... params) {
            while(true) {
                try {
                    if (isCancelled())
                        break;
                    publishProgress();
                    Log.i("screen", "Chatting_act >> !!!!!!!!!TREAH!!!!!!!!!");
                    Thread.sleep(10000);


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            return null;
        }
    }

    @Override
    protected void onDestroy() {
        task.cancel(true);
        Intent intent=new Intent(getApplicationContext(),main_act.class);
        intent.putExtra("FLAG","MESSAGE");
        startActivity(intent);
        super.onDestroy();
    }
}
