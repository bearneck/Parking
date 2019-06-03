package com.bearneck.parking;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.bearneck.parking.Fragment.BookingParkingFragment;
import com.bearneck.parking.Fragment.HomeFragment;
import com.bearneck.parking.Fragment.MyFragment;

import java.io.Serializable;


public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {
    public static final String UserId = "UserId";//用户的唯一标志
    public static final String UserName = "UserName";//用户名
    public static final String RealName = "RealName";//真实姓名


    private BottomNavigationBar bottomNavigationBar;
    int lastSelectedPosition = 0;
    private String TAG = MainActivity.class.getSimpleName();
    //分页
    private MyFragment mMyFragment;
    private HomeFragment mHomeFragment;
    private BookingParkingFragment mBookingParkingFragment;
    private static final int UPDATE_TITLE = 0;

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_TITLE:

                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         * bottomNavigation 设置
         */
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);

        /** 导航基础设置 包括按钮选中效果 导航栏背景色等 */
        bottomNavigationBar
                .setTabSelectedListener(this)
                .setMode(BottomNavigationBar.MODE_FIXED)
                /**
                 *  setMode() 内的参数有三种模式类型：
                 *  MODE_DEFAULT 自动模式：导航栏Item的个数<=3 用 MODE_FIXED 模式，否则用 MODE_SHIFTING 模式
                 *  MODE_FIXED 固定模式：未选中的Item显示文字，无切换动画效果。
                 *  MODE_SHIFTING 切换模式：未选中的Item不显示文字，选中的显示文字，有切换动画效果。
                 */

                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                /**
                 *  setBackgroundStyle() 内的参数有三种样式
                 *  BACKGROUND_STYLE_DEFAULT: 默认样式 如果设置的Mode为MODE_FIXED，将使用BACKGROUND_STYLE_STATIC
                 *                                    如果Mode为MODE_SHIFTING将使用BACKGROUND_STYLE_RIPPLE。
                 *  BACKGROUND_STYLE_STATIC: 静态样式 点击无波纹效果
                 *  BACKGROUND_STYLE_RIPPLE: 波纹样式 点击有波纹效果
                 */

                .setActiveColor(R.color.colorPrimaryDark) //选中颜色
                .setInActiveColor(R.color.fontColor) //未选中颜色
                .setBarBackgroundColor(R.color.white);//背景

        /** 添加导航按钮 */
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.myparking, "我的车位"))
                .addItem(new BottomNavigationItem(R.drawable.parking,"预约车位"))
                .addItem(new BottomNavigationItem(R.drawable.wode, "个人"))
                .setFirstSelectedPosition(lastSelectedPosition)
                .initialise(); //initialise 一定要放在 所有设置的最后一项
        setDefaultFragment();

    }


    //设置初始界面
    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        mHomeFragment = HomeFragment.newInstance("我的车位");
        transaction.replace(R.id.tb, mHomeFragment);
        transaction.commit();
    }

    /**
     * 设置导航选中的事件
     */
    @Override
    public void onTabSelected(int position) {


        Bundle b = getIntent().getExtras();//获取Login活动传来的值
        String userId = b.getString(UserId);
        String userName = b.getString(UserName);

        Log.d(TAG, "onTabSelected() called with: " + "position = [" + position + "]");
        FragmentManager fm = this.getSupportFragmentManager();
        //开启事务
        FragmentTransaction transaction = fm.beginTransaction();
        switch (position) {
            case 0:
                if (mHomeFragment == null) {
                    mHomeFragment = HomeFragment.newInstance("我的车位");

                }
                transaction.replace(R.id.tb, mHomeFragment);
                break;
            case 1:
                if (mBookingParkingFragment == null) {
                    mBookingParkingFragment = BookingParkingFragment.newInstance("预约车位","nihao");//传参
                }
                transaction.replace(R.id.tb,mBookingParkingFragment);
                break;
            case 2:
                if (mMyFragment == null) {
                    mMyFragment = MyFragment.newInstance("个人中心",userId,userName);
                }
                transaction.replace(R.id.tb, mMyFragment);

                break;

            default:
                break;
        }

        transaction.commit();// 事务提交
    }

    /**
     * 设置未选中Fragment 事务
     */
    @Override
    public void onTabUnselected(int position) {

    }

    /**
     * 设置释放Fragment 事务
     */
    @Override
    public void onTabReselected(int position) {

    }

}
