package a.itcast.mobileplayer95.bean;

import java.util.List;

/**
 * Created Magic
 * Date 2017/5/15
 * Email bonian1852@163.com
 */
public class MvListBean {

    private int totalCount;
    private List<VideoBean> videos;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<VideoBean> getVideos() {
        return videos;
    }

    public void setVideos(List<VideoBean> videos) {
        this.videos = videos;
    }
}
