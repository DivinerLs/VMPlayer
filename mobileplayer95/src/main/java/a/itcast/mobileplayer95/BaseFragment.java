package a.itcast.mobileplayer95;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import a.itcast.mobileplayer95.utils.LogUtils;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者：Magic on 2017/5/17 15:06
 * 邮箱：bonian1852@163.com
 */

public abstract class BaseFragment extends Fragment {

    protected  View rootView;
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogUtils.e(getClass(),"BaseFragment.onCreateView,rootView="+rootView);

        //复用 rootView
        if (rootView == null){
            rootView = inflater.inflate(getLayouId(), null);
        }

        ButterKnife.bind(this,rootView);

        initView();
        return rootView;
    }

    /**
     * 返回当前Fragment使用的布局id
     * @return
     */
    protected abstract int getLayouId();

    /**
     * 处理界面初始化
     */
    protected abstract void initView();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
