package com.example.l8703.broadcastbestpractice;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.AlteredCharSequence;

public class BaseActivity extends AppCompatActivity {

    private ForceOfflineReceiver receiver;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.broadcastbestpractice.FORCE_OFFLINE");
        receiver = new ForceOfflineReceiver();
        registerReceiver(receiver,intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(receiver != null){
            unregisterReceiver(receiver);
            receiver = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    private class ForceOfflineReceiver  extends BroadcastReceiver {

        @Override
        public void onReceive(final Context context, Intent intent) {
            //AlertDialog.Builder构建一个 对话框
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Warning");
            builder.setMessage("You are forced to be offline,please try login again.");
            //调用 setCancelable将对话框设置为不可取消（否则按back可取消对话框）
            builder.setCancelable(false);

            //调用setPositiveButton方法来个对话框添加OK按钮
            //当按下ok按钮是调用ActivityCollector的finishAll方法来销毁所有活动，并重启LoginActivity
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCollector.finishAll();//销毁所有活动
                    Intent intent = new Intent(context,LoginActivity.class);
                    context.startActivity(intent);//重启LoginActivity
                }
            });
            builder.show();
        }
    }
}
