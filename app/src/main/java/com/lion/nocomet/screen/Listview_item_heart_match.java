package com.lion.nocomet.screen;

/**
 * Created by kummi_000 on 2015-12-17.
 */
public class Listview_item_heart_match {
    private int match_image;
    private String match_name1;
    private String match_name2;
    private String match_name3;
    private String match_message;
    private String match_major;

    public int getMatchimage()
    {
        return match_image;
    }

    public String getMatchmessage() {return match_message;}
    public String getMatchname1() {return match_name1;}
    public String getMatchname2() {return match_name2;}
    public String getMatchname3() {return match_name3;}
    public String getMatchmajor() {return match_major;}

    public Listview_item_heart_match(int match_image,String match_major,String match_name1,String match_name2,String match_name3)
    {
        this.match_image=match_image;
        this.match_major=match_major;
        this.match_name1=match_name1;
        this.match_name2=match_name2;
        this.match_name3=match_name3;

    }


}