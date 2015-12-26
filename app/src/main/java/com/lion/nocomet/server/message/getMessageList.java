package com.lion.nocomet.server.message;

import android.content.Context;
import android.util.Log;
import android.webkit.WebView;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Source;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by user on 2015-12-17.
 */
public class getMessageList {
    private WebView web;
    String result;
    String send;
    public getMessageList(Context context)
    {
        web = new WebView(context);
        web.getSettings().setJavaScriptEnabled(true);
    }
    public String getListByID(String id)
    {
        result="start";
        send="http://nocomet.dothome.co.kr/s2/board/getMessageList.html?FLAG="+id;
        web.loadUrl(send);
        Log.i("server", "getMessage >> getSendByID / " + send);

        new Thread() {
            public void run() {
                Source source = null;
                try {
                    source = new Source(new URL(send));

                    source.fullSequentialParse();
                    Element content = source.getElementById("content");
                    List<Element> sub = content.getChildElements();
                    for(Element ele : sub)                    {
                        if(result.equals("start"))
                            result="";

                        result+=ele.getContent().toString();
                    }
                    Log.i("server", "getMessageList >> getListByID / " + result);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        while(result.equals("start"))
        {
            try {
                Thread.sleep(300);
                Log.i("server", "getMessageList >> getListByID sleep... result="+result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Log.i("server", "getMessageList >> getListByID return");
        return result;
    }

}
