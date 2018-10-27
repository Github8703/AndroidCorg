package com.example.l8703.broadcastbestpractice;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends BaseActivity{
    private EditText ed_name;
    private EditText ed_pass;
    private Button login;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ed_name = findViewById(R.id.ed_name);
        ed_pass = findViewById(R.id.ed_pass);
        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ed_name.getText().toString();
                String pass = ed_pass.getText().toString();
                //如果账号是“admin”密码是“123456”则登录成功
                if(name.equals("admin") && pass.equals("123456")){
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this,"登录失败",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
