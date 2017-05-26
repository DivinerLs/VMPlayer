package a.itcast.mobileplayer95.fargment.mvpage;

import java.util.List;

import a.itcast.mobileplayer95.bean.AreaBean;
import a.itcast.mobileplayer95.http.BaseCallBack;
import a.itcast.mobileplayer95.http.HttpManager;
import a.itcast.mobileplayer95.utils.URLProviderUtil;

/**
 * Created by Administrator on 2017/5/25.
 */

public class MvPresenter implements MvMvp.Presenter {


    MvMvp.View view;

    public MvPresenter(MvMvp.View view) {
        this.view = view;
    }

    @Override
    public void loadData() {

        String url = URLProviderUtil.getMVareaUrl();

        HttpManager.getInstance().get(url, new BaseCallBack<List<AreaBean>>() {
            @Override
            public void onFailure(int code, Exception e) {
                view.onError(code,e);
            }

            @Override
            public void onSuccess(List<AreaBean> areaBeen) {
                view.setData(areaBeen);
            }

        });

    }
}
