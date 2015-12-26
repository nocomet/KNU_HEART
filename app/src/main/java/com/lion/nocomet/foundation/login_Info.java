package com.lion.nocomet.foundation;

/**
 * Created by user on 2015-12-17.
 */
public class login_Info {

    private static login_Info instance;
    public String dept;
    public String name;
    public String sid;
    public boolean isRegist;
    private login_Info()
    {

    }

    public static login_Info getInstance()
    {
        if(instance==null)
            instance=new login_Info();
        return instance;
    }
}
