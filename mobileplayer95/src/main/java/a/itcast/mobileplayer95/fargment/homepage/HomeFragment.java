package a.itcast.mobileplayer95.fargment.homepage;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import a.itcast.mobileplayer95.BaseFragment;
import a.itcast.mobileplayer95.R;
import a.itcast.mobileplayer95.adapter.HomeAdapter;
import a.itcast.mobileplayer95.bean.VideoBean;
import a.itcast.mobileplayer95.utils.LogUtils;
import butterknife.BindView;
import butterknife.Unbinder;

/**
 * 作者：Magic on 2017/5/17 14:51
 * 邮箱：bonian1852@163.com
 */

public class HomeFragment extends BaseFragment implements HomeMvp.View {

    private static final String TAG = "HomeFragment";

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    Unbinder unbinder;

    private HomeMvp.Presenter presenter;//HomeMVP

    private HomeAdapter homeAdapter;

    private List<VideoBean> videoBeen;

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
        LogUtils.e(TAG, "HomeFragment.initView,创建 presenter并请求数据");
        presenter = new HomePresenter(this);
        presenter.loadData(0, 10);

        //填充 recyclerView 列表
        //设置[布局管理器] 可以设置不同的行为 [列表 九宫格 瀑布流 都可以弄,方向也可以自己设置]
        LinearLayoutManager layout = new LinearLayoutManager(getContext());
        //GridLayoutManager layout = new GridLayoutManager(getContext(),2);//九宫格
        //StaggeredGridLayoutManager layout2 = new StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL);//瀑布流
        layout.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(layout);

        //设置 adapter [适配器]

        videoBeen = new ArrayList<>();
        homeAdapter = new HomeAdapter(videoBeen);
        recyclerview.setAdapter(homeAdapter);
    }

    @Override
    public void setData(List<VideoBean> videoBeen) {
        LogUtils.e(TAG, "HomeFragment.setData,videoBeen=" + videoBeen.size());
        //当获取到数据的时候 我们把 videoBeen (新的集合) 添加到 69行 videoBeen 里面去
        this.videoBeen.addAll(videoBeen);
        //通知适配器(notifyDataSetChanged)进行改变
        homeAdapter.notifyDataSetChanged();
    }

    @Override
    public void onError(int code, Exception e) {

    }





}
