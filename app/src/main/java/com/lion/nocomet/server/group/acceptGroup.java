package com.lion.nocomet.server.group;

import android.content.Context;
import android.util.Log;
import android.webkit.WebView;

/**
 * Created by user on 2015-12-04.
 */
public class acceptGroup {
    private WebView web;

    public acceptGroup(Context context)
    {
        web = new WebView(context);
        web.getSettings().setJavaScriptEnabled(true);
    }

    public void accept(String ManID, String WomanID)
    {
        String send="http://nocomet.dothome.co.kr/s2/board/acceptGroup.php?ManID="
                + ManID + "&WomanID=" + WomanID;
        web.loadUrl(send);
        Log.i("server","acceptGroup >> accept / "+send);
    }
}
