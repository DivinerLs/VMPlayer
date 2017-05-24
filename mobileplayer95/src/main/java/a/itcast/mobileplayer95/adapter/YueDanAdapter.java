package a.itcast.mobileplayer95.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import a.itcast.mobileplayer95.R;
import a.itcast.mobileplayer95.bean.YueDanBean;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：Magic on 2017/5/24 13:29
 * 邮箱：bonian1852@163.com
 */

public class YueDanAdapter extends RecyclerView.Adapter<YueDanAdapter.MyViewHolder> {

    List<YueDanBean.PlayListsBean> playLists;

    public YueDanAdapter(List<YueDanBean.PlayListsBean> playLists) {
        this.playLists = playLists;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(parent.getContext(), R.layout.fragment_item_yuedan, null);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //获取到当前条目的数据
        YueDanBean.PlayListsBean playListsBean = playLists.get(position);

        //填充文本
        holder.title.setText(playListsBean.getTitle());
        holder.author.setText(playListsBean.getCreator().getNickName());
        holder.playCount.setText("收录高清Mv"+playListsBean.getVideoCount()+" 首");

    }

    @Override
    public int getItemCount() {
        return playLists.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.iv_postimg)
        ImageView ivPostimg;
        @Bind(R.id.viewbgs)
        View viewbgs;
        @Bind(R.id.civ_img)
        de.hdodenhof.circleimageview.CircleImageView civImg;
        @Bind(R.id.title)
        TextView title;
        @Bind(R.id.author)
        TextView author;
        @Bind(R.id.play_count)
        TextView playCount;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
