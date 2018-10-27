package com.example.l8703.broadcastbestpractice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button  offline = findViewById(R.id.offline);
        offline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //com.example.broadcastbestpractice.FORCE_OFFLINE通知用户强制下线
                Intent intent = new Intent("com.example.broadcastbestpractice.FORCE_OFFLINE");
                sendBroadcast(intent);
            }
        });
    }
}
