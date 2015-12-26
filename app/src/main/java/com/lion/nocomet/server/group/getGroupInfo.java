package com.lion.nocomet.server.group;

import android.content.Context;
import android.util.Log;
import android.webkit.WebView;

import com.lion.nocomet.foundation.groupList;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Source;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by user on 2015-12-12.
 */
public class getGroupInfo {
    private WebView web;
    String result;
    String send;
    public getGroupInfo(Context context)
    {
        web = new WebView(context);
        web.getSettings().setJavaScriptEnabled(true);
    }
    public String getInfobyID(String id)
    {
        result="start";
        send="http://nocomet.dothome.co.kr/s2/board/getGroup.html?FLAG="+id;
        web.loadUrl(send);
        Log.i("server", "getGroup >> getInfobyID / " + send);

        new Thread() {
            public void run() {
                Source source = null;
                try {
                    source = new Source(new URL(send));

                    source.fullSequentialParse();
                    Element content = source.getElementById("content");
                    List<Element> sub = content.getChildElements();
                    for(Element ele : sub)
                    {
                        if(result.equals("start"))
                            result="";
                        result+=ele.getContent().toString();
                    }
                    Log.i("server", "getGroup >> getInfobyID / " + result);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        while(result.equals("start"));

        return result;
    }
    public String getInfoAll()
    {
        result="";
        send="http://nocomet.dothome.co.kr/s2/board/getGroup.html?FLAG=0";
        web.loadUrl(send);
        Log.i("server", "getGroup >> getInfoAll / " + send);

        new Thread() {
            public void run() {
                Source source = null;
                try {
                    source = new Source(new URL(send));
                    source.fullSequentialParse();
                    Element content = source.getElementById("content");
                    List<Element> sub = content.getChildElements();
                    for(Element ele : sub)
                    {
                        if(result.equals("start"))
                            result="";
                        result+=ele.getContent().toString();
                    }
                    Log.i("server", "getGroup >> getInfoAll / " + result);
                    groupList.getInstance().setArray(result);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        while(result.equals(""));

        return result;
    }
}
