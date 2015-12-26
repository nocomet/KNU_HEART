package com.lion.nocomet.login;

import android.content.Context;
import android.util.Log;

import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 김정섭 on 2015-12-16.
 */
public class SmtLogIn {
    public static boolean isLogged = false;
    private Context context;

    public SmtLogIn(Context c) {
        this.context = c;
    }
/*
    public boolean login() {
        if (isLogged)
            return true;

        PreferenceSetting loginSetting = new PreferenceSetting(context, "login");
        boolean autoLogin = loginSetting.getBoolean("isAutoLogin", false);

        if (autoLogin) {
            String loginNo = loginSetting.getString("user_no", "");
            String loginID = loginSetting.getString("user_id", "");
            String loginPW = loginSetting.getString("user_pw", "");
            return login(loginNo, loginID, loginPW, true);
        } else {
            //Intent activity = new Intent(context, SmtLoginActivity.class);
            //((Activity) context).startActivityForResult(activity, 0);
        }

        return false;
    }
*/

    public boolean login(String loginNo, String loginId, String loginPw, boolean isAutoLogin) {

        List<BasicNameValuePair> loginParams = new ArrayList<BasicNameValuePair>();

        loginParams.add(new BasicNameValuePair("j_username", loginId));
        loginParams.add(new BasicNameValuePair("j_password", loginPw));
        loginParams.add(new BasicNameValuePair("_spring_security_remember_me", ""));

        String loginResult = "";

        try {
            HTTPSession.getPostUrl(HTTPSession.MainUrl + "main/?site_preference=mobile");
            loginResult = HTTPSession.getPostUrl(HTTPSession.MainUrl + "/user/subLogin?currentUri=", loginParams);

            loginParams.clear();
            loginParams.add(new BasicNameValuePair("j_password", loginPw));
            loginParams.add(new BasicNameValuePair("j_username", loginNo));
            loginParams.add(new BasicNameValuePair("_spring_security_remember_me", ""));
            loginResult = HTTPSession.getPostUrl(HTTPSession.MainUrl + "login?currentUri=", loginParams);
        } catch (Exception e) {
            e.printStackTrace();
        }

        int index = loginResult.indexOf("location.replace('/main/')");

        Log.i("idx", "" + index);

        if(index != -1)
            return true;

        return false;

/*
        if (index != -1) {
            PreferenceSetting PreferenceSetting = new PreferenceSetting(context, "login");
            PreferenceSetting.putString("user_no", loginNo);
            PreferenceSetting.putString("user_id", loginId);
            PreferenceSetting.putBoolean("isAutoLogin", isAutoLogin);
            if (isAutoLogin)
                PreferenceSetting.putString("user_pw", loginPw);
            return isLogged = true;
        } else
            return isLogged = false;
            */
    }
}
