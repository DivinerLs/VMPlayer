package a.itcast.mobileplayer95.fargment.homepage;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import a.itcast.mobileplayer95.BaseFragment;
import a.itcast.mobileplayer95.R;
import a.itcast.mobileplayer95.bean.VideoBean;
import a.itcast.mobileplayer95.utils.LogUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
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
        TextView tv_text = (TextView) rootView.findViewById(R.id.tv_text);
        //获取初始化参数 content:内容 Arguments:参数
        tv_text.setText("这是首页的界面");

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
    }

    @Override
    public void setData(List<VideoBean> videoBeen) {
        LogUtils.e(TAG, "HomeFragment.setData,videoBeen=" + videoBeen.size());
    }

    @Override
    public void onError(int code, Exception e) {

    }





}
