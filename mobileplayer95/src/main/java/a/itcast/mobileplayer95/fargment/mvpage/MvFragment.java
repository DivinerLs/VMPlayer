package a.itcast.mobileplayer95.fargment.mvpage;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import a.itcast.mobileplayer95.BaseFragment;
import a.itcast.mobileplayer95.R;
import a.itcast.mobileplayer95.adapter.MvPageAdapter;
import a.itcast.mobileplayer95.bean.AreaBean;
import a.itcast.mobileplayer95.fargment.TestFragment;
import a.itcast.mobileplayer95.utils.LogUtils;
import butterknife.Bind;

/**
 * Created by Administrator on 2017/5/25.
 */

public class MvFragment extends BaseFragment implements MvMvp.View {

    private static final String TAG = "MvFragment";


    @Bind(R.id.viewpager)
    ViewPager viewpager;
    private MvMvp.Presenter presenter;

    @Override
    protected int getLayouId() {
        return R.layout.fragment_mv;
    }

    @Override
    protected void initView() {
        presenter = new MvPresenter(this);
        presenter.loadData();
    }

    @Override
    public void setData(List<AreaBean> areaBeen) {
        LogUtils.e(TAG,"MvFragment.setData,areaBeen="+areaBeen.size());
        List<Fragment> fragmentList = new ArrayList<>();

        for (AreaBean areaBean :areaBeen) {
            fragmentList.add(TestFragment.newInstance(areaBean.getCode()));
        }
        
        viewpager.setAdapter(new MvPageAdapter(getFragmentManager(),fragmentList));
    }

    @Override
    public void onError(int code, Exception e) {
        Toast.makeText(getContext(), "错误代码:"+code, Toast.LENGTH_SHORT).show();
    }
}
