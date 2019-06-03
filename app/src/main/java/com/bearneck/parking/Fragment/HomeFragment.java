package com.bearneck.parking.Fragment;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.bearneck.parking.Adapters.PictureAdapter;
import com.bearneck.parking.Bean.Picture;
import com.bearneck.parking.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

public class HomeFragment extends Fragment {
    //各类全局变量
    public static final int UPDATE_ADAPTER = 1;
    RecyclerView mrecyclerView;
    private List<Picture> pictureList = new ArrayList<>();
    private PictureAdapter adapter;
    private SwipeRefreshLayout swipeRefresh;
    private LinearLayoutManager mLayoutManager;
    private static boolean hasMore = true; // 是否有下一页
    private static int currentPage ;
    // 若是上拉加载更多的网络请求 则不需要删除数据
    private boolean isLoadingMore = false;
    // 最后一个条目位置
    private static int lastVisibleItem = 0;
    // 初始化图片界面控件
    private Picture[] pictures = {
            new Picture("鹿晗","鹿晗","15340565266","否","租用","2019-06-02 00:49:33","2019-06-02 00:49:34","2019-06-02 00:49:30",1),
            new Picture("鹿晗","鹿晗","15340565266","否","租用","2019-06-02 00:49:34","2019-06-02 00:49:35","2019-06-02 00:49:31",2),
            new Picture("鹿晗","鹿晗","15340565266","否","租用","2019-06-02 00:49:35","2019-06-02 00:49:36","2019-06-02 00:49:32",3),
            new Picture("鹿晗","鹿晗","15340565266","是","租用","2019-06-02 00:49:36","2019-06-02 00:49:37","2019-06-02 00:49:33",4)
              };


    public static HomeFragment newInstance(String param1) {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initPictureView(view);
        return view;
    }

    //初始化各个部件
    @SuppressLint("ResourceAsColor")
    private void initPictureView(View view) {
        initPictures();
        mrecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mrecyclerView.setLayoutManager(layoutManager);
        adapter = new PictureAdapter(pictureList,getContext(),hasMore);

        mLayoutManager = new GridLayoutManager(getContext(), 1);
        mrecyclerView.setLayoutManager(mLayoutManager);
        mrecyclerView.setAdapter(adapter);
        mrecyclerView.setItemAnimator(new DefaultItemAnimator());
//        loadingMore();
        // 初始currentPage为1
        currentPage = 1;

// 网络请求
//        sendREquestWithOkHttp(currentPage);


        swipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeColors(R.color.likeColor);//改变刷新颜色
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pictureList.clear();//随机调取数据
                int page = 0;
//                sendREquestWithOkHttp(page);
                refreshPictures();
            }
        });
    }
    //加载更多
    private void loadingMore(){
        // 实现上拉加载重要步骤，设置滑动监听器，RecyclerView自带的ScrollListener
        mrecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(!isLoadingMore){        // 若不是加载更多 才 加载
                    // 在newState为滑到底部时
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        // 如果没有隐藏footView，那么最后一个条目的位置(带数据）就比我们的getItemCount少1
                        if (!adapter.isFadeTips() && lastVisibleItem + 1 == adapter.getItemCount()) {
                            // 然后调用updateRecyclerview方法更新RecyclerView
                            updateRecyclerView();

                        }
                        // 如果隐藏了提示条，我们又上拉加载时，那么最后一个条目(带数据）就要比getItemCount要少2
                        if (adapter.isFadeTips() && lastVisibleItem + 2  == adapter.getItemCount()) {
                            // 然后调用updateRecyclerview方法更新RecyclerView
                            updateRecyclerView();    // 要调
//                            Log.d(TAG, "onScrollStateChanged: 成功运行老子2");
                        }
                    }
                }
            }
            //滚动监听
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                // 在滑动完成后，拿到最后一个可见的item的位置
                lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
//                Log.d(TAG, "onScrolled: 成功运行老子到——》"+dy);
            }

        });
    }
    private void updateRecyclerView() {
        Log.d(TAG, "updateRecyclerView: 成功运行老子1"+hasMore);
        if(hasMore){
            // 还有下一页 网络请求 第二页 第三页
            currentPage++;    // 加1
            isLoadingMore = false;
//            sendREquestWithOkHttp(currentPage);
        }}
    private void refreshPictures() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //CONTEXT:getActivity().getApplicationContext()
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initPictures();
                        adapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }
    private void initPictures() {
            pictureList.clear();//随机调取数据
            for (int i =0;i<5;i++) {
                Random random = new Random();
                int index = random.nextInt(pictures.length);
                pictureList.add(pictures[index]);
            }

    }
}
