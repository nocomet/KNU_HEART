package com.lion.nocomet.foundation;

import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by user on 2015-12-14.
 */
public class groupList {
    public ArrayList<group> list;
    private static groupList instance;
    boolean listFlag;
    private groupList()
    {
        listFlag=false;
        Log.i("foundation", "constructor create >> .. ");
    }
    public static groupList getInstance()
    {
        if(instance == null) {
            Log.i("foundation", "null null >> .. ");
            instance = new groupList();
        }

        return instance;
    }
    public ArrayList<group> getRandomGroupSix(char sex)
    {
        ArrayList<group> temp = new ArrayList<group>();
        ArrayList<group> result = new ArrayList<group>();
        while(listFlag==false)
        {
            try {
                Thread.sleep(1000);
                Log.i("foundation", "getRandomGroupSix >> sleep...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Log.i("foundation", "groupList >> 2 list arraylist size=" + list.size());
        int max = list.size();
        boolean flag[]=new boolean[max];
        int ii=0;
        group g;
        for(int k = 0 ; k < list.size() ; k++)
        {
            g = list.get(k);
            flag[k]=false;
            if(g.sex==sex)
            {
                temp.add(g);
            }
            //Log.i("foundation","groupList >> test enhanced for stmt");
        }

        Log.i("foundation", "groupList >> temp arraylist size=" + temp.size());
        Random random = new Random();
        int randNum;
        for(int i = 0 ; i < 6; i ++)
        {
             randNum=random.nextInt(temp.size());
            if(flag[randNum]==false)
            {
                result.add(temp.get(randNum));
                flag[randNum]=true;
            }
            else
            {
                i--;
            }
        }
        Log.i("foundation","groupList >> result arraylist size="+result.size());
        return result;
    }

    public void setArray(String line)
    {
        list = new ArrayList<group>();
        listFlag=false;
        line=line.trim();
        Log.i("foundation","groupList > line "+line);
        String temp[] = line.split("<br>");
        for(String str : temp)
        {
            Log.i("foundation","groupList > str "+str);
            String groupInfo[] = str.split("\\%\\^\\&");
            group g = new group(groupInfo[0].trim(),groupInfo[1].trim(),groupInfo[2].trim(),groupInfo[3].trim(),groupInfo[4].trim(),groupInfo[5].trim(),groupInfo[6].trim().charAt(0));
            list.add(g);

            Log.i("foundation","groupList > g="+g.toString());
        }

        Log.i("foundation", "groupList >> 1 list arraylist size=" + list.size());
        listFlag=true;
    }
}
