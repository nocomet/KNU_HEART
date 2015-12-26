package com.lion.nocomet.foundation;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by user on 2015-12-20.
 */
public class completeList {
    public ArrayList<complete> list;
    public static completeList instance;
    public boolean listFlag;
    private completeList()
    {

    }
    public static completeList getInstance()
    {
        if(instance==null)
            instance=new completeList();
        return instance;
    }
    public void setArray(String line)
    {
        Log.i("foundation", "completeList > setArray line=" + line);
        if(line.contains("없다")) {
            list = new ArrayList<complete>();
            return;
        }
        list = new ArrayList<complete>();
        listFlag=false;
        line=line.trim();
        Log.i("foundation", "completeList > line " + line);
        String temp[] = line.split("<br>");
        for(String str : temp)
        {
            Log.i("foundation", "completeList > str " + str);
            String groupInfo[] = str.split("\\%\\^\\&");


            complete c = new complete(groupInfo[0].trim(), groupInfo[1].trim(), (groupInfo[2].trim()).charAt(0));

            list.add(c);

            Log.i("foundation", "completeList > c=" + c.toString());
        }

        Log.i("foundation", "completeList >> list arraylist size=" + list.size());
        listFlag=true;
    }
}
