package a.itcast.mobileplayer95.fargment.mvpage;

import java.util.List;

import a.itcast.mobileplayer95.bean.AreaBean;

/**
 * Created by Administrator on 2017/5/25.
 */

public interface MvMvp {

    interface Presenter{
        void loadData();
    }

    interface View{
        void setData(List<AreaBean> areaBeen);
        void onError(int code, Exception e);
    }
}
