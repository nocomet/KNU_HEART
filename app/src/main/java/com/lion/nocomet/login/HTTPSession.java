package com.lion.nocomet.login;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.util.List;

/**
 * Created by 김정섭 on 2015-12-16.
 */
public class HTTPSession {

    public static String MainUrl = "http://smt.knu.ac.kr:8080/";
    //로그인 정보를 저장한다.
    private static DefaultHttpClient loginHttpClient;

    //Post값을 이용해서 데이터를 전송해준다.
    public static String getPostUrl(String _URL, List<BasicNameValuePair> _parameter) throws Exception {

        createHttpClient();

        HttpPost post = new HttpPost(_URL); //post 보낼 주소 설정

        post.setHeader("User-Agent", "Mozilla/5.0 (Linux; U; Android 4.0.4; ko-kr; KM-S200 Build/IMM76I) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30");

        UrlEncodedFormEntity reqEntity = new UrlEncodedFormEntity(_parameter, HTTP.UTF_8);

        post.setEntity(reqEntity);

        HttpResponse resp = HTTPSession.loginHttpClient.execute(post);

        HttpEntity resEntity = resp.getEntity();
        
        return EntityUtils.toString(resEntity);
    }

    //Get을 이용해 데이터 전송
    public static String getPostUrl(String _URL) throws Exception {

        createHttpClient();

        HttpGet post = new HttpGet(_URL); //post 보낼 주소 설정

        HttpResponse resp = HTTPSession.loginHttpClient.execute(post);

        HttpEntity resEntity = resp.getEntity();

        return EntityUtils.toString(resEntity);
    }

    private static void createHttpClient() {
        //만약 Login을 위한 HttpClient가 생성되지 않았을 경우
        if (HTTPSession.loginHttpClient == null) {
            HTTPSession.loginHttpClient = new DefaultHttpClient();
        }
    }

    public static HttpClient getLoginHttpClient() {
        return loginHttpClient;
    }

}
