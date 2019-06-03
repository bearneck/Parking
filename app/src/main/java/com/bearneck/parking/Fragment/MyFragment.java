package com.bearneck.parking.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import com.bearneck.parking.LoginActivity;
import com.bearneck.parking.R;

import static android.support.constraint.Constraints.TAG;


public class MyFragment extends Fragment {
    public static MyFragment newInstance(String param1,String userid,String username) {
        MyFragment fragment = new MyFragment();
        Bundle args = new Bundle();
        args.putString("title", param1);
        args.putString("userid",userid);
        args.putString("username",username);

        fragment.setArguments(args);
        return fragment;
    }

    public MyFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        String userid = bundle.getString("userid");
        String username = bundle.getString("username");
        Log.d(TAG, "onCreateView: "+userid+username);
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) view.findViewById(R.id.collapsing_toolbar);
//        collapsingToolbarLayout.setTitle("请登录");
//        collapsingToolbarLayout.setCollapsedTitleGravity(Gravity.CENTER_VERTICAL);//设置收缩后标题的位置
//        collapsingToolbarLayout.setExpandedTitleGravity(Gra);////设置展开后标题的位置
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);//设置展开后标题的颜色
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);//设置收缩后标题的颜色
//        CircleImageView circleImageView = (CircleImageView) view.findViewById(R.id.icon);
//        circleImageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
        Button loginbutton = (Button) view.findViewById(R.id.login);
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(),LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        TextView UserNameText = (TextView) view.findViewById(R.id.textView_title);
        UserNameText.setText(username);
        return view;
    }
}
