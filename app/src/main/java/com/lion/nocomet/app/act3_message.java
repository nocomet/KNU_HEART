package com.lion.nocomet.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lion.nocomet.server.message.sendMessage;

public class act3_message extends AppCompatActivity {

    EditText edit_sendID;
    EditText edit_RevID;
    EditText content;
    Button btn;

    private sendMessage message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act3_message);

        message = new sendMessage(getApplicationContext());
        edit_RevID= (EditText)findViewById(R.id.edit_RevID);
        edit_sendID= (EditText)findViewById(R.id.edit_sendID);
        content= (EditText)findViewById(R.id.edit_content);
        btn = (Button)findViewById(R.id.btn_send);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message.send(edit_sendID.getText().toString(),
                        edit_RevID.getText().toString(),
                        content.getText().toString()
                        );

                Toast.makeText(getApplicationContext(), "등록완료", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
