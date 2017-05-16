package a.itcast.mobileplayer95.fargment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import a.itcast.mobileplayer95.R;

/**
 * 作者：Magic on 2017/5/13 06:06
 * 邮箱：bonian1852@163.com
 */

public class TestFragment extends Fragment {

    private static final String TAG = "TestFragment";

    public static TestFragment getInstance(String content){
        //填充初始化参数
        Bundle args = new Bundle();
        args.putString("content",content);

        TestFragment testFragment = new TestFragment();
        testFragment.setArguments(args);
        return testFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test, null);

        TextView tv_text = (TextView) view.findViewById(R.id.tv_text);

        //获取初始化参数 content:内容 Arguments:参数
        Bundle args = getArguments();
        String content = args.getString("content");
        tv_text.setText(content);
        return view;
    }
}
