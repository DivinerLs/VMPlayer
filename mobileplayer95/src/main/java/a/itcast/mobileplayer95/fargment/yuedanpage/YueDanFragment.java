package a.itcast.mobileplayer95.fargment.yuedanpage;


import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import a.itcast.mobileplayer95.BaseFragment;
import a.itcast.mobileplayer95.R;
import a.itcast.mobileplayer95.adapter.YueDanAdapter;
import a.itcast.mobileplayer95.bean.YueDanBean;
import a.itcast.mobileplayer95.utils.LogUtils;
import butterknife.Bind;


/**
 * 作者：Magic on 2017/5/24 10:45
 * 邮箱：bonian1852@163.com
 */

public class YueDanFragment extends BaseFragment implements YueDanMvp.View {

    private static final String TAG = "YueDanFragment";
    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    @Bind(R.id.refresh)
    SwipeRefreshLayout refresh;


    private YueDanMvp.Presenter presenter;
    private List<YueDanBean.PlayListsBean> list;
    private YueDanAdapter yueDanAdapter;
    private boolean isRefresh;
    private boolean hasMore = true;

    @Override
    protected int getLayouId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {

        //使用 presenter 加载数据
        presenter = new YueDanPresenter(this);
        presenter.loadData(offset, SIZE);

        //初始化 RecyclerView 默认情况下就是垂直的
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));

        list = new ArrayList<>();
        yueDanAdapter = new YueDanAdapter(list);
        recyclerview.setAdapter(yueDanAdapter);

       // 悦单的 下拉刷新
        refresh.setOnRefreshListener(new OnYueDanRefreshListener());

        //上来刷新
        recyclerview.addOnScrollListener(new OnYueDanScrollListener());

    }

    @Override
    public void setData(List<YueDanBean.PlayListsBean> playLists) {
        LogUtils.e(TAG, "YueDanFragment.setData,playLists=" + playLists.size());

        if (isRefresh)
        {
            //针对下拉刷新的处理
            list.clear();
            isRefresh = false;
            refresh.setRefreshing(false);
        }

        offset += playLists.size();//修改下一页的起始位置
        hasMore = playLists.size() ==SIZE;//如果返回的数据量不等于请求的大小,则说明没有下一页数据了

        list.addAll(playLists);
        yueDanAdapter.notifyDataSetChanged();
    }

    @Override
    public void setError(int code, Exception e) {
        Toast.makeText(getActivity(), "请求数据发生错误,代码为:" + code, Toast.LENGTH_SHORT).show();
    }


    private class OnYueDanRefreshListener implements SwipeRefreshLayout.OnRefreshListener {
        @Override
        public void onRefresh() {
            isRefresh = true;
            offset = 0;
            presenter.loadData(offset,SIZE);

        }
    }

    //上拉刷新
    private class OnYueDanScrollListener extends RecyclerView.OnScrollListener {
        @Override
        /**
         * 滚动状态发生变化时
         */
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

            //获取当前可见的最后一个条目 item 的位置 这里的recyclerview是自己的,不是onScrollStateChanged里面的
            LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerview.getLayoutManager();
            int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();

            //判断是否要获取下一页的数据
            if (newState == 0 && lastVisibleItemPosition == list.size()-1 && hasMore){
                presenter.loadData(offset,SIZE);
            }
        }
    }
}
