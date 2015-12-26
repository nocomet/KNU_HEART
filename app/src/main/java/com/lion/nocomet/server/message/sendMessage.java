package com.lion.nocomet.server.message;

import android.content.Context;
import android.util.Log;
import android.webkit.WebView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by user on 2015-12-14.
 */
public class sendMessage {
    private WebView web;

    public sendMessage(Context context)
    {
        web = new WebView(context);
        web.getSettings().setJavaScriptEnabled(true);
    }

    public void send(String SendID, String RevID, String content)
    {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss");
        Log.i("screen","현재 시간 : " + dateFormat.format(calendar.getTime()).toString());
        String date=dateFormat.format(calendar.getTime()).toString();
        String send="http://nocomet.dothome.co.kr/s2/board/registMessage.php?SendID="
                + SendID + "&RevID=" + RevID
                +"&CONTENT="+content
                +"&DATE="+date;

        web.loadUrl(send);
        Log.i("server", "registGroup >> regist / " + send);
    }
}

