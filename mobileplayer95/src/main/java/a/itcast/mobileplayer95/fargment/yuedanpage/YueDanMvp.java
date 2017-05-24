package a.itcast.mobileplayer95.fargment.yuedanpage;

import java.util.List;

import a.itcast.mobileplayer95.bean.YueDanBean;

/**
 * 作者：Magic on 2017/5/24 10:55
 * 邮箱：bonian1852@163.com
 */

public interface YueDanMvp {

    interface Presenter{
        void loadData(int size, int offset);
    }

    interface  View{
        void setData(List<YueDanBean.PlayListsBean> playLists);
        void setError(int code, Exception e);


    }
}
