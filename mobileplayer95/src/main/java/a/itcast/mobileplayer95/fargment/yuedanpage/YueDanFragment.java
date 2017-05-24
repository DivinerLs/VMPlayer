package a.itcast.mobileplayer95.fargment.yuedanpage;


import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import a.itcast.mobileplayer95.BaseFragment;
import a.itcast.mobileplayer95.R;
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

    @Override
    protected int getLayouId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        presenter = new YueDanPresenter(this);
        presenter.loadData(offset, SIZE);
    }

    @Override
    public void setData(List<YueDanBean.PlayListsBean> playLists) {
        LogUtils.e(TAG, "YueDanFragment.setData,playLists=" + playLists.size());
    }

    @Override
    public void setError(int code, Exception e) {
        Toast.makeText(getActivity(), "请求数据发生错误,代码为:" + code, Toast.LENGTH_SHORT).show();
    }



}
