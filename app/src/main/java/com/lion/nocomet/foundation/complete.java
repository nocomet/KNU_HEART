package com.lion.nocomet.foundation;

/**
 * Created by user on 2015-12-20.
 */
public class complete {
    public String manID;
    public String womanID;
    public char state;

    public complete(String mySID,String parterSID, char state)
    {
        this.manID=mySID;
        this.womanID=parterSID;
        this.state=state;
    }

    public String toString()
    {
        String result="";
        result+="manID="+manID+" womanID="+womanID+" state="+state;
        return result;
    }

}
