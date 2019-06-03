package com.bearneck.parking;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(true);
//        }
        //隐藏返回按钮
        Button register = (Button) findViewById(R.id.Registe);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        final EditText accountInput = (EditText) findViewById(R.id.accountInput);
        final EditText passwordInput = (EditText) findViewById(R.id.passwordInput);
        Button login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account=  accountInput.getText().toString();
                String password= passwordInput.getText().toString();
//                Log.d("账号和密码", "1234567"+account+password);
                if (account.equals("root")&&password.equals("root")){//匹配账号密码
                    Intent intent1 =  new Intent();//登录页面跳转
                    Bundle bundle = new Bundle();//传值
                    bundle.putString(MainActivity.UserId,"测试id");
                    bundle.putString(MainActivity.UserName,account);
                    intent1.putExtras(bundle);
                    intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent1.setClass(LoginActivity.this,MainActivity.class);
                    startActivity(intent1);
                }else if (account.equals("")){
                    Toast.makeText(LoginActivity.this,"账号不能为空！",Toast.LENGTH_SHORT).show();

                }else if (password.equals("")){
                    Toast.makeText(LoginActivity.this,"密码不能为空！",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(LoginActivity.this,"账号或密码错误，请重新输入",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
