package com.lion.nocomet.foundation;

/**
 * Created by user on 2015-12-15.
 */
public class message {
    public String SendID;
    public String RevID;
    public String Content;
    public String Date;
    public message(String SendID, String RevID, String content, String Date)
    {
        this.SendID=SendID;
        this.RevID=RevID;
        this.Content=content;
        this.Date=Date;
    }
}
