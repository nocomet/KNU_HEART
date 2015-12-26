package com.lion.nocomet.screen;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.app.Dialog;
import com.lion.nocomet.app.R;

public class CustomDialog extends Dialog {
    private Button mLeftButton;
    private Button mRightButton;

    private View.OnClickListener mLeftClickListener;
    private View.OnClickListener mRightClickListener;

    public String Strdept;
    public String Strname1, Strname2, Strname3;
    public String StrLine;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 다이얼로그 외부 화면 흐리게 표현
        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.8f;
        getWindow().setAttributes(lpWindow);

        setContentView(R.layout.activity_custom_dialog);

        mLeftButton = (Button) findViewById(R.id.activity_custom_dialog_btn_accpet);
        mRightButton = (Button) findViewById(R.id.activity_custom_dialog_btn_reject);

        TextView Txtsid = (TextView)findViewById(R.id.activity_custom_dialog_text_title);
        Txtsid.setText("'"+Strname1+"'님이 과팅요청을 보냈습니다.");
        TextView name1 = (TextView)findViewById(R.id.activity_custom_dialog_text_name1);
        name1.setText(Strname1);
        TextView name2 = (TextView)findViewById(R.id.activity_custom_dialog_text_name2);
        name2.setText(Strname2);
        TextView name3 = (TextView)findViewById(R.id.activity_custom_dialog_text_name3);
        name3.setText(Strname3);
        TextView dept = (TextView)findViewById(R.id.activity_custom_dialog_text_major);
        dept.setText(Strdept);
        TextView message = (TextView)findViewById(R.id.activity_custom_dialog_text_message);
        message.setText(StrLine);


        // 클릭 이벤트 셋팅
        if (mLeftClickListener != null && mRightClickListener != null) {
            mLeftButton.setOnClickListener(mLeftClickListener);
            mRightButton.setOnClickListener(mRightClickListener);
        } else if (mLeftClickListener != null && mRightClickListener == null) {
            mLeftButton.setOnClickListener(mLeftClickListener);
        } else {

        }
    }

    // 클릭버튼이 확인과 취소 두개일때 생성자 함수로 이벤트를 받는다
    public CustomDialog(Context context, View.OnClickListener leftListener, View.OnClickListener rightListener,String s1, String n1, String n2, String n3, String line) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        this.mLeftClickListener = leftListener;
        this.mRightClickListener = rightListener;

        this.Strdept=s1;
        this.Strname1=n1;
        this.Strname2=n2;
        this.Strname3=n3;
        this.StrLine=line;
    }


    /*
    // 클릭버튼이 하나일때 생성자 함수로 클릭이벤트를 받는다.
    public CustomDialog(Context context, String title,
                        View.OnClickListener singleListener) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        this.mTitle = title;
        this.mLeftClickListener = singleListener;
    }
*/

}