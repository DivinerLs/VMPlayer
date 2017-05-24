package a.itcast.mobileplayer95.adapter;

import android.graphics.Point;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import a.itcast.mobileplayer95.R;
import a.itcast.mobileplayer95.bean.VideoBean;
import a.itcast.mobileplayer95.utils.Util;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：Magic on 2017/5/23 17:27
 * 邮箱：bonian1852@163.com
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

    private List<VideoBean> videoBeen;


    public HomeAdapter(List<VideoBean> videoBeen) {
        this.videoBeen = videoBeen;
    }

    /**
     * 创建新的 ViewHolder [视图持有], 只在没有 itemView 的时候被调用
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(parent.getContext(), R.layout.homepage_item, null);
        return new MyViewHolder(itemView);
    }

    /**
     * 为 ViewHolder 绑定数据
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //获取到当前条目的数据
        VideoBean videoBean = videoBeen.get(position);

        //填充内容
        holder.tvTitle.setText(videoBean.getTitle());//Title:标题
        holder.tvDescription.setText(videoBean.getDescription());//Description:描述

        //加载图片 用 compile 'com.github.bumptech.glide:glide:3.7.0' 这个包
        Glide.with(holder.itemView.getContext())
                .load(videoBean.getPosterPic())
                .into(holder.ivContentimg);
    }

    /**
     * 得到列表项个数
     * @return
     */
    @Override
    public int getItemCount() {
        return videoBeen.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.iv_item_logo)
        ImageView ivItemLogo;
        @Bind(R.id.iv_contentimg)
        ImageView ivContentimg;
        @Bind(R.id.iv_type)
        ImageView ivType;
        @Bind(R.id.tv_title)
        TextView tvTitle;
        @Bind(R.id.tv_description)
        TextView tvDescription;
        //这个控件是为了让视频的描述清晰显示
        @Bind(R.id.viewbg)
        View viewBg;


        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            //初始化 图片的大小 computeImgSize(原始图片宽度,原始图片高度,上下文)
            //Point:点
            Point point = Util.computeImgSize(640,540,itemView.getContext());
            ivContentimg.getLayoutParams().width = point.x;
            ivContentimg.getLayoutParams().height = point.y;
            ivContentimg.requestLayout();

            //这个控件是为了让视频的描述清晰显示
            viewBg.getLayoutParams().width = point.x;
            viewBg.getLayoutParams().height = point.y;
            viewBg.requestLayout();
        }
    }


}
