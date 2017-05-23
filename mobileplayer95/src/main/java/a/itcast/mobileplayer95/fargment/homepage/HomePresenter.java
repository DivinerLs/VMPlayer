package a.itcast.mobileplayer95.fargment.homepage;

import java.util.List;

import a.itcast.mobileplayer95.bean.VideoBean;
import a.itcast.mobileplayer95.http.BaseCallBack;
import a.itcast.mobileplayer95.http.HttpManager;
import a.itcast.mobileplayer95.utils.LogUtils;
import a.itcast.mobileplayer95.utils.URLProviderUtil;


/**
 * 作者：Magic on 2017/5/22 10:28
 * 邮箱：bonian1852@163.com
 */

public class HomePresenter implements HomeMvp.Presenter {

    private static final String TAG = "HomePresenter";
    HomeMvp.View view;

    public HomePresenter(HomeMvp.View view) {
        this.view = view;
    }

    @Override
    public void loadData(int offset, int size) {

        String url = URLProviderUtil.getMainPageUrl(offset,size);

        LogUtils.e(TAG,"HomePresenter.loadData,开始加载数据,url="+url);

        HttpManager.getInstance().get(url, new BaseCallBack<List<VideoBean>>() {
            @Override
            public void onFailure(int code, Exception e) {
                view.onError(code,e);
            }

            @Override
            public void onSuccess(List<VideoBean> videoBeen) {
                LogUtils.e(TAG,"HomePresenter.onSuccess,成功获取到数据");
                view.setData(videoBeen);
            }


        });
    }
}
