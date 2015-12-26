package com.lion.nocomet.screen;

/**
 * Created by kummi_000 on 2015-12-17.
 */
public class Listview_item_heart {

    private int recv_image;
    private String recv_message;
    public String id;

    public int getRecvimage()
    {
        return recv_image;
    }

    public String getRecvmessage() {return recv_message;}

    public Listview_item_heart(int recv_image,String recv_message, String id)
    {
        this.recv_image=recv_image;
        this.recv_message=recv_message;
        this.id=id;
    }
}