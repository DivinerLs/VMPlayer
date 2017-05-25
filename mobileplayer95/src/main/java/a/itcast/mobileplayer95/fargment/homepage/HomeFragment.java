package a.itcast.mobileplayer95.fargment.homepage;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

import a.itcast.mobileplayer95.BaseFragment;
import a.itcast.mobileplayer95.R;
import a.itcast.mobileplayer95.adapter.HomeAdapter;
import a.itcast.mobileplayer95.bean.VideoBean;
import a.itcast.mobileplayer95.utils.LogUtils;
import butterknife.Bind;


/**
 * 作者：Magic on 2017/5/17 14:51
 * 邮箱：bonian1852@163.com
 */

public class HomeFragment extends BaseFragment implements HomeMvp.View {

    private static final String TAG = "HomeFragment";

    //ListView的进阶版 RecyclerView
    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    //[下拉刷新] 控件
    @Bind(R.id.refresh)
    SwipeRefreshLayout refreshLayout;

    private HomeMvp.Presenter presenter;//HomeMVP

    private HomeAdapter homeAdapter;

    private List<VideoBean> videoBeen;
    // 下拉刷新 [判断:是否刷新]
    private boolean isRefresh;
    // [hasMore] 有更多 分页加载
    private boolean hasMore = true;

    //生成代码 Command + N | Control + 回车
    public HomeFragment() {
        LogUtils.e(TAG, "HomeFragment.HomeFragment,");
    }

    @Override
    protected int getLayouId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {

        // [实现HomeMvp.View接口后] 创建 Presenter
       // LogUtils.e(TAG, "HomeFragment.initView,创建 presenter并请求数据");
        presenter = new HomePresenter(this);

        presenter.loadData(offset,SIZE);//offset的初始值为10  SIZE的常量值为10

        //填充 recyclerView 列表
        //设置[布局管理器] 可以设置不同的行为 [列表 九宫格 瀑布流 都可以弄,方向也可以自己设置]
        LinearLayoutManager layout = new LinearLayoutManager(getContext());
        //GridLayoutManager layout = new GridLayoutManager(getContext(),3);//九宫格
        //StaggeredGridLayoutManager layout = new StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL);//瀑布流
        layout.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(layout);

        //设置 adapter [适配器]
        videoBeen = new ArrayList<>();
        homeAdapter = new HomeAdapter(videoBeen);
        recyclerview.setAdapter(homeAdapter);

        //上拉刷新 [分页加载] 监听
        recyclerview.addOnScrollListener(new OnMainScrollListener());

        // 下拉刷新 监听
        refreshLayout.setOnRefreshListener(new OnMainRefreshListener());

    }

    /**
     * 设置数据
     * @param videoBeen
     */
    @Override
    public void setData(List<VideoBean> videoBeen) {
       // LogUtils.e(TAG, "HomeFragment.setData,videoBeen=" + videoBeen.size());

        /**
         * 如果下拉刷新为 true  就把现有的videoBeen给清空掉 清楚原有的数据
         */
        if (isRefresh)
        {
            this.videoBeen.clear();//清除 videoBeen 现有的 items
            isRefresh = false; //把下拉刷新的状态改成false
            refreshLayout.setRefreshing(false); //判断是否刷新  如果为 false 就代表刷新结束了.
        }
        //当数据加载成功的时候 我们才能修改 offset 的值
        offset +=videoBeen.size();
        //如果返回的数据量不等于请求的大小,则说明没有下一页数据了
        hasMore = videoBeen.size() == SIZE;

        //当获取到数据的时候 我们把 videoBeen (新的集合) 添加到 videoBeen 里面去
        this.videoBeen.addAll(videoBeen);
        //通知适配器(notifyDataSetChanged)进行改变
        homeAdapter.notifyDataSetChanged();
    }

    @Override
    public void onError(int code, Exception e) {

    }


    private class OnMainRefreshListener implements SwipeRefreshLayout.OnRefreshListener {
        @Override
        /**
         * 下拉刷新
         * 当下拉刷新时,加载最新的列表数据
         */
        public void onRefresh() {
            offset = 0;
            presenter.loadData(offset,SIZE);
            isRefresh = true;
        }
    }

    //上拉刷新
    private class OnMainScrollListener extends RecyclerView.OnScrollListener {
        /**
         * 当滚动状态发生变化的时候被调用
         * @param recyclerView
         * @param newState 为[0]说明是静止状态
         *                 为[1]说明是开始滚动
         *                 为[2]说明是惯性滚动
         */
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            LogUtils.e(TAG,"OnMainScrollListener.onScrollStateChanged,newState="+newState);

            //获取当前可见的最后一个条目item位置
            LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerview.getLayoutManager();
            int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();//查找最后一个可见的位置

            if (newState == 0 && lastVisibleItemPosition == videoBeen.size() -1 && hasMore){
                //如果状态变为静止 [0] 则准备加载下一页数据
                presenter.loadData(offset,SIZE);
            }
        }

        /**
         * 不断的获取当前滚动位置
         * @param recyclerView
         * @param dx
         * @param dy
         */
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            LogUtils.e(TAG,"OnMainScrollListener.onScrolled,");
        }
    }
}
