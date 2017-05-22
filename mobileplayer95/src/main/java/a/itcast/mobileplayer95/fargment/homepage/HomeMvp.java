package a.itcast.mobileplayer95.fargment.homepage;

import java.util.List;

import a.itcast.mobileplayer95.bean.VideoBean;

/**
 * 作者：Magic on 2017/5/22 10:22
 * 邮箱：bonian1852@163.com
 */

public interface HomeMvp {

    /**
     * Persenter 层接口
     * 用于:加载数据
     */
    interface Presenter {
        void loadData(int offset, int size);
    }

    /**
     * view 层接口
     * 用于:更新界面的回调
     */
    interface View {
        void setData(List<VideoBean> videoBeen);

        void onError(int code, Exception e);
    }
}
