package com.bearneck.parking;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import static com.bearneck.parking.MainActivity.RealName;
import static com.bearneck.parking.MainActivity.UserId;
import static com.bearneck.parking.MainActivity.UserName;

public class BookingParking extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_parking);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        Bundle b = getIntent().getExtras();//获取Login活动传来的值
        String userId = b.getString(UserId);
        String userName = b.getString(UserName);
        String realName = b.getString(RealName);
        String phone="";
        String shirutime="";
        String shichutime="";
        String shiruType = "";

        final EditText name = (EditText) findViewById(R.id.nameInput);
        final EditText phoneInput = (EditText) findViewById(R.id.phoneInput);
        final EditText shiruTime = (EditText) findViewById(R.id.shiruTime);
        final EditText shichuTime = (EditText) findViewById(R.id.shichuTime);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        final RadioButton shortTimeButton = (RadioButton)findViewById(R.id.shortTime);
        final RadioButton longTimeButton = (RadioButton)findViewById(R.id.longTime);

        name.setText(realName);


        Button yuyueButton = (Button) findViewById(R.id.yuyueButton);
        yuyueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String realName = name.getText().toString();
                String phone = phoneInput.getText().toString();
                String shirutime= shiruTime.getText().toString();
                String shichutime = shichuTime.getText().toString();
                String shiruType = "";
                if (shortTimeButton.isChecked()){
                    shiruType ="临时";
                }else if (longTimeButton.isChecked()){
                    shiruType ="租用";
                }
                if (realName.equals("")|| phone.equals("")|| shirutime.equals("")|| shichutime.equals("")||shiruType.equals("")){
                    Toast.makeText(BookingParking.this,"请填入完整信息！",Toast.LENGTH_SHORT).show();
                }else {
                Toast.makeText(BookingParking.this,"预约提交成功！",Toast.LENGTH_SHORT).show();
                finish();
                }
            }
        });


    }

}
