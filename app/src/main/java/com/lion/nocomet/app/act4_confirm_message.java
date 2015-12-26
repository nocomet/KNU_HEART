package com.lion.nocomet.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lion.nocomet.foundation.groupList;
import com.lion.nocomet.foundation.messageList;
import com.lion.nocomet.server.message.getMessage;

public class act4_confirm_message extends AppCompatActivity {

    TextView text;
    EditText edit_id;
    Button btn_Send;
    Button btn_Rev;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act4_confirm_message);
        text=(TextView)findViewById(R.id.text_confirm);
        edit_id=(EditText)findViewById(R.id.edit_confirmRevID);
        btn_Send =(Button)findViewById(R.id.btn_confirm_send);
        btn_Rev =(Button)findViewById(R.id.btn_confirm_rev);
        btn_Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit_id.getText().toString().equals("0")) {
                    getMessage info = new getMessage(getApplicationContext());
                    String result = info.getMessageAll();
                    if(!result.contains("없다")) {
                        text.setText(result);
                        messageList.getInstance().setArray(result,"test");
                    }
                    else
                    {
                        text.setText(result);
                    }
                } else {
                    getMessage info = new getMessage(getApplicationContext());
                    String result = info.getSendByID(edit_id.getText().toString());
                    if(!result.contains("없다")) {
                        text.setText(result);
                        messageList.getInstance().setArray(result,"test");
                    }
                    else
                    {
                        text.setText(result);
                    }
                }
            }
        });

    }
}
