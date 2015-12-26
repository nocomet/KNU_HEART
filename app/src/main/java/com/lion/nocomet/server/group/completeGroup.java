package com.lion.nocomet.server.group;

import android.content.Context;
import android.util.Log;
import android.webkit.WebView;

/**
 * Created by user on 2015-12-04.
 */
public class completeGroup {
    private WebView web;

    public completeGroup(Context context)
    {
        web = new WebView(context);
        web.getSettings().setJavaScriptEnabled(true);
    }

    public void registComplete(String ManID, String WomanID)
    {
        String send="http://nocomet.dothome.co.kr/s2/board/completeGroup.php?ManID="
                + ManID + "&WomanID=" + WomanID;
        web.loadUrl(send);
        Log.i("server","completeGroup >> registComplete / "+send);
    }
}
