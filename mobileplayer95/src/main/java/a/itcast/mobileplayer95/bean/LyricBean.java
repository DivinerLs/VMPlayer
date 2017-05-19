package a.itcast.mobileplayer95.bean;

/**
 * Created Magic
 * Date 2017/5/15
 * Email bonian1852@163.com
 */
public class LyricBean {

    public String content;
    public long startPoint;

    public LyricBean(String content, long startPoint) {
        this.content = content;
        this.startPoint = startPoint;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(long startPoint) {
        this.startPoint = startPoint;
    }
}
