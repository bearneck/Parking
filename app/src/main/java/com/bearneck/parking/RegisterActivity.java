package com.bearneck.parking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        final EditText accountInput = (EditText) findViewById(R.id.accountInput);
        final EditText passwordInput = (EditText) findViewById(R.id.passwordInput);
        final EditText realNameInput = (EditText) findViewById(R.id.nameInput);
        Button registe = (Button) findViewById(R.id.Registe);
        registe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account=  accountInput.getText().toString();
                String password= passwordInput.getText().toString();
                String realname=  realNameInput.getText().toString();
                if (account.equals("")){
                    Toast.makeText(RegisterActivity.this,"账号不能为空！",Toast.LENGTH_SHORT).show();

                }else if (password.equals("")){
                    Toast.makeText(RegisterActivity.this,"密码不能为空！",Toast.LENGTH_SHORT).show();
                }else if (realname.equals("")){
                    Toast.makeText(RegisterActivity.this,"真实姓名不能为空！",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                    Intent intent1 =  new Intent();//登录页面跳转
                    Bundle bundle = new Bundle();//传值
                    bundle.putString(MainActivity.UserId,"测试id");
                    bundle.putString(MainActivity.UserName,account);
                    intent1.putExtras(bundle);
                    intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent1.setClass(RegisterActivity.this,MainActivity.class);
                    startActivity(intent1);
                }
            }
        });

    }
}
