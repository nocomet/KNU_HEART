package com.lion.nocomet.login;

import android.content.Context;

import org.json.JSONObject;

/**
 * Created by 김정섭 on 2015-12-16.
 */
public class UserProfile {

    private Context context;
    private UserInfo userInfo;

    public UserProfile(Context c) {
        this.context = c;
        userInfo = new UserInfo();
    }

    public void user()
    {
        String information;

        try {
            information = HTTPSession.getPostUrl(HTTPSession.MainUrl + "knu12_lnb0_00/getForm");
            JSONObject jsonObject = new JSONObject(information);

            userInfo.setUserName(jsonObject.get("name").toString());
            userInfo.setMajor(jsonObject.get("sub_class_cde_nm").toString());
            userInfo.setGrad_suffincy(jsonObject.get("grad_suffincy_unit").toString());
            userInfo.setStuNbr(Integer.parseInt(jsonObject.get("stu_nbr").toString()));
            userInfo.setRegCnt(Integer.parseInt(jsonObject.get("reg_cnt").toString()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public UserInfo getUserInfo()
    {
        return userInfo;
    }

}
