package com.bearneck.parking.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.bearneck.parking.Bean.Picture;
import com.bearneck.parking.R;
import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.makeramen.roundedimageview.RoundedImageView.TAG;


public class PictureAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<Picture> mPictureList;
    private LayoutInflater mLayoutInflater;

    private int normalType = 0;     // 第一种ViewType，正常的item
    private int footType = 1;       // 第二种ViewType，底部的提示View

    private boolean hasMore = true;   // 变量，是否有更多数据
    private boolean fadeTips = false; // 变量，是否隐藏了底部的提示

    private Handler mHandler = new Handler(Looper.getMainLooper()); //获取主线程的Handler

    public PictureAdapter(List<Picture> pictureList, Context context, boolean hasMore){
        mPictureList = pictureList;
        this.mContext = context;
        this.hasMore = hasMore;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        if (mContext == null){
//            mContext = parent.getContext();
//        }
//        View view = LayoutInflater.from(mContext).inflate(R.layout.picture_item,parent,false);
//        return new ViewHolder(view);
        // 根据返回的ViewType，绑定不同的布局文件，这里只有两种
        View view;
        if (viewType == normalType) {
            return new NormalHolder(LayoutInflater.from(mContext).inflate(R.layout.picture_item, parent,false));
        }
        else {
            return new FootHolder(LayoutInflater.from(mContext).inflate(R.layout.bottom_item, parent,false));
        }

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        // 如果是正常的imte，直接设置TextView的值
        if (holder instanceof NormalHolder) {
//            ((NormalHolder) holder).textView.setText(datas.get(position));
            final Picture picture = mPictureList.get(position);
            ((NormalHolder) holder).id.setText((position+1)+"");
            ((NormalHolder) holder).userName.setText(picture.getUserName());
            ((NormalHolder) holder).realName.setText(picture.getRealName());
            ((NormalHolder) holder).phone.setText(picture.getPhone());
            ((NormalHolder) holder).shiruType .setText(picture.getShiruType());
            ((NormalHolder) holder).shifoushenhe.setText(picture.getShifoushenhe());
            ((NormalHolder) holder).shiruTime.setText(picture.getShiruTime());
            ((NormalHolder) holder).shichuTime.setText(picture.getShichuTime());
            ((NormalHolder) holder).yuyueTime.setText(picture.getYuyueTime());
//            ((NormalHolder) holder).content.setText(picture.getContent());
//            ((NormalHolder) holder).zannum.setText(picture.getZannum() + " ");//int不能直接转换成string
//            ((NormalHolder) holder).contentnum.setText(picture.getContentnum() + " ");
//            ((NormalHolder) holder).sharenum.setText(picture.getSharenum() + " ");
//            ((NormalHolder) holder).detail.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
////                    Intent intent = new Intent(mContext, detail.class);
////                    Bundle bundle = new Bundle();
////                    bundle.putString(detail.IMAGE_URL, picture.getImageId());
////                    bundle.putString(detail.ICON_URL, picture.getIconId());
////                    bundle.putInt(detail.ZAN_URL, picture.getZannum());
////                    bundle.putInt(detail.SHARE_URL, picture.getSharenum());
////                    bundle.putString(detail.CONTEXT_URL, picture.getContent());
////                    bundle.putInt(detail.CONTENT_id, picture.getSoureid());
////                    intent.putExtras(bundle);
////                    mContext.startActivity(intent);//启动TwoActivity活动
//                }
//            });
//            Glide.with(mContext).load(picture.getIconId()).error(R.drawable.jiqiren).into(((NormalHolder) holder).iconId);

            //图片优化
//            Glide.with(mContext).load(picture.getImageId())
//                    .error(R.drawable.jiqiren)
//                    .thumbnail(0.1f)
//                    .into(((NormalHolder) holder).imageId);

            ((NormalHolder) holder).deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mPictureList.size() == 1) {
                        Snackbar.make(v, "再删就没有了", Snackbar.LENGTH_SHORT).show();
                    } else {
                        //删除自带默认动画
                        removeData(position);
                    }
                }
            });
//            ((NormalHolder) holder).upview.setOnClickListener(new View.OnClickListener() {
//                @SuppressLint({"ResourceAsColor", "ResourceType"})
//                @Override
//                public void onClick(View v) {
//                    int zannew = picture.getZannum() + 1;
//                    ((NormalHolder) holder).zannum.setText(zannew + " ");
//                    ((NormalHolder) holder).zannum.setTextColor(R.color.likeColor);
//                    ((NormalHolder) holder).zanimage.setImageResource(R.drawable.up);
//                }
//            });
//            ((NormalHolder) holder).imageId.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
////                    Intent intent = new Intent(mContext, activity_image.class);
////                    intent.putExtra(activity_image.IMAGE_URL, picture.getImageId());
////                    mContext.startActivity(intent);
//                }
//            });
//            ((NormalHolder) holder).contentview.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
////                    Intent intent = new Intent(mContext, detail.class);
////                    Bundle bundle = new Bundle();
////                    bundle.putString(detail.IMAGE_URL, picture.getImageId());
////                    bundle.putString(detail.ICON_URL, picture.getIconId());
////                    bundle.putInt(detail.ZAN_URL, picture.getZannum());
////                    bundle.putInt(detail.SHARE_URL, picture.getSharenum());
////                    bundle.putString(detail.CONTEXT_URL, picture.getContent());
////                    intent.putExtras(bundle);
////
////                    mContext.startActivity(intent);//启动TwoActivity活动
//                }
//            });
        }
//        else {
//            // 之所以要设置可见，是因为我在没有更多数据时会隐藏了这个footView
//            ((FootHolder) holder).tips.setVisibility(View.VISIBLE);
//            // 只有获取数据为空时，hasMore为false，所以当我们拉到底部时基本都会首先显示“正在加载更多...”
//            if (hasMore == true) {
//                // 不隐藏footView提示
//                fadeTips = false;
//                if (mPictureList.size() > 0) {
//                    // 如果查询数据发现增加之后，就显示正在加载更多
//                    ((FootHolder) holder).tips.setText("正在加载更多...");
//                }
//            } else {
//                if (mPictureList.size() > 0) {
//                    // 如果查询数据发现并没有增加时，就显示没有更多数据了
//                    ((FootHolder) holder).tips.setText("没有更多数据了");
//
//                    // 然后通过延时加载模拟网络请求的时间，在500ms后执行
//                    mHandler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            // 隐藏提示条
//                            ((FootHolder) holder).tips.setVisibility(View.GONE);
//                            // 将fadeTips设置true
//                            fadeTips = true;
//                            // hasMore设为true是为了让再次拉到底时，会先显示正在加载更多
//                            hasMore = true;
//                        }
//                    }, 500);
//                }
//            }
//        }
    }


    @Override
    public int getItemCount() {
        return mPictureList.size() + 1;
    }
    public void removeData(int position) {
        mPictureList.remove(position);
        notifyItemRemoved(position);
        if(position != mPictureList.size()){ // 如果移除的是最后一个，忽略
            notifyItemRangeChanged(position, mPictureList.size() - position);
        }
    }
    // 自定义方法，获取列表中数据源的最后一个位置，比getItemCount少1，因为不计上footView
    public int getRealLastPosition() {
        return mPictureList.size();
    }
    // 根据条目位置返回ViewType，以供onCreateViewHolder方法内获取不同的Holder
    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return footType;
        } else {
            return normalType;
        }
    }
    // 正常item的ViewHolder，用以缓存findView操作
    class NormalHolder extends RecyclerView.ViewHolder {
//        private TextView textView;
private CardView cardView;
private LinearLayout linearLayout;
        private TextView userName;
        private TextView realName;
        private TextView phone;
        private TextView shifoushenhe;
        private TextView shiruType;
        private TextView shiruTime;
        private TextView shichuTime;
        private TextView id;
        private TextView yuyueTime;
        private ImageButton deleteButton;


        private LinearLayout detail;
        public NormalHolder(View itemView) {
            super(itemView);
//            textView = (TextView) itemView.findViewById(R.id.tv);
            userName = (TextView) itemView.findViewById(R.id.username);
            realName = (TextView) itemView.findViewById(R.id.realname);
            phone = (TextView) itemView.findViewById(R.id.phone);
            shifoushenhe = (TextView) itemView.findViewById(R.id.shifoushenhe);
            shiruType = (TextView) itemView.findViewById(R.id.shirutype);
            shiruTime = (TextView) itemView.findViewById(R.id.shiruTime);
            shichuTime = (TextView) itemView.findViewById(R.id.shichuTime);
            id = (TextView) itemView.findViewById(R.id.id);
            yuyueTime = (TextView) itemView.findViewById(R.id.yuyueTime);
            linearLayout = (LinearLayout) itemView;
            deleteButton = (ImageButton) itemView.findViewById(R.id.delete);
////            cardView = (CardView) itemView;
//            iconId = (CircleImageView) itemView.findViewById(R.id.icon);
////            imageId = (RoundedImageView) itemView.findViewById(R.id.picture_image);
//            name = (TextView) itemView.findViewById(R.id.name);
//            content = (TextView) itemView.findViewById(R.id.content);
////            zannum = (TextView) itemView.findViewById(R.id.zan);
////            contentnum = (TextView) itemView.findViewById(R.id.comment);
////            sharenum = (TextView) itemView.findViewById(R.id.share);
//
//
////            zanimage = (ImageView)itemView.findViewById(R.id.zanimage);
//            //评论转发分享
////            upview = (LinearLayout) itemView.findViewById(R.id.upview);
////            contentview = (LinearLayout) itemView.findViewById(R.id.contentview);
////            shareview = (LinearLayout) itemView.findViewById(R.id.shareview);
////
//            detail = (LinearLayout) itemView.findViewById(R.id.detail);
        }
    }
    // // 底部footView的ViewHolder，用以缓存findView操作
    class FootHolder extends RecyclerView.ViewHolder {
        private TextView tips;

        public FootHolder(View itemView) {
            super(itemView);
            tips = (TextView) itemView.findViewById(R.id.foot_tips);
        }
    }
    // 暴露接口，改变fadeTips的方法
    public boolean isFadeTips() {
        return fadeTips;
    }

    // 暴露接口，下拉刷新时，通过暴露方法将数据源置为空
    public void resetPicturess() {
        mPictureList = new ArrayList<>();
    }

    // 暴露接口，更新数据源，并修改hasMore的值，如果有增加数据，hasMore为true，否则为false
    public void updateList(List<Picture> newPictures, boolean hasMore) {
        // 在原有的数据之上增加新数据
        if (newPictures != null) {
            mPictureList.addAll(newPictures);
        }
        this.hasMore = hasMore;
        notifyDataSetChanged();
    }

}
