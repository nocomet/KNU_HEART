package com.lion.nocomet.server.group;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;
import android.webkit.WebView;

/**
 * Created by user on 2015-12-04.
 */
public class registGroup {
    private WebView web;

    public registGroup(Context context)
    {
        web = new WebView(context);
        web.getSettings().setJavaScriptEnabled(true);
    }

    public void regist(String id, String name1, String name2, String name3, String line,String dept,String sex)
    {
        String send="http://nocomet.dothome.co.kr/s2/board/registGroup.php?SID="
                + id + "&NAME1=" + name1
                +"&NAME2="+name2
                +"&NAME3="+name3
                +"&LINE="+line
                +"&DEPT="+dept
                +"&SEX="+sex;
        web.loadUrl(send);
        Log.i("server","registGroup >> regist / "+send);
    }
}
