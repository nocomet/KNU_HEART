package com.lion.nocomet.foundation;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by user on 2015-12-15.
 */
public class messageList {
    public ArrayList<message> list;
    private static messageList instance;

    private messageList()
    {

    }
    public static messageList getInstance()
    {
        if(instance == null)
            instance=new messageList();
        return instance;
    }

    public void setArray(String line, String id)
    {
        list = new ArrayList<message>();
        line=line.trim();
        Log.i("foundation", "messageList > line " + line);
        String temp[] = line.split("<br>");
        for(String str : temp)
        {
            Log.i("foundation","messageList > str "+str);
            String groupInfo[] = str.split("\\%\\^\\&");
            if((groupInfo[0].trim().equals(login_Info.getInstance().sid) && groupInfo[1].trim().equals(id))
                    ||(groupInfo[1].trim().equals(login_Info.getInstance().sid) && groupInfo[0].trim().equals(id))) {
                message m = new message(groupInfo[0].trim(), groupInfo[1].trim(), groupInfo[2].trim(), groupInfo[3].trim());
                list.add(m);
                Log.i("foundation", "messageList > groupInfo[0]=" + groupInfo[0] + "***");
                Log.i("foundation", "messageList > groupInfo[1]=" + groupInfo[1] + "***");
                Log.i("foundation", "messageList > groupInfo[2]=" + groupInfo[2] + "***");
                Log.i("foundation", "messageList > groupInfo[3]=" + groupInfo[3] + "***");
            }
        }
    }
    public void setList(String line)
    {
        list = new ArrayList<message>();
        //line=line.trim();
        Log.i("foundation", "messageList > setList input line=" + line);
        String temp[] = line.split("<br>");
        for(String str : temp)
        {
            str=str.trim();
            Log.i("foundation","messageList > setList for string="+str+"***");
            String groupInfo[] = str.split(" ");
            message m = new message(groupInfo[0],"aa","bb","cc");
            list.add(m);
            Log.i("foundation","messageList > result="+groupInfo[0]+" ++ ");
        }
    }
}

