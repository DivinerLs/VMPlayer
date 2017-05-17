package a.itcast.mobileplayer95.fargment;

import android.widget.TextView;

import a.itcast.mobileplayer95.BaseFragment;
import a.itcast.mobileplayer95.R;
import a.itcast.mobileplayer95.utils.LogUtils;

/**
 * 作者：Magic on 2017/5/17 14:51
 * 邮箱：bonian1852@163.com
 */

public class HomeFragment extends BaseFragment {

    private static final String TAG = "HomeFragment";

    //生成代码 Command + N | Control + 回车
    public HomeFragment() {
        LogUtils.e(TAG,"HomeFragment.HomeFragment,");
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
    }
}
