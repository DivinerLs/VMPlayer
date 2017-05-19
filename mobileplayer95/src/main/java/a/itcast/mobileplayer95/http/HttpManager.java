package a.itcast.mobileplayer95.http;

import java.io.IOException;
import java.util.Map;

import a.itcast.mobileplayer95.utils.LogUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/5/19.
 */

public class HttpManager {

    private static final String TAG = "HttpManager";

    private OkHttpClient okHttpClient;
    private static HttpManager httpManager;

    private HttpManager() {
        this.okHttpClient = new OkHttpClient();
    }

    /**
     * 获取 HttpManager的单例对象
     * @return httpManager
     */
    //写个单例
    public static HttpManager getInstance(){
        if (httpManager == null)
        {
            httpManager = new HttpManager();
        }

        return httpManager;
    }

    /**
     * 发起 get 请求 (二次封装)
     * @param url
     */
    //单例对象里面方法没必要全部静态
    public void get(String url){

        //创建请求参数
        Request request = new Request.Builder().url(url).build();

        doRequest(request);
    }

    /**
     * 发起 post 请求
     * @param url
     */
    public void post(String url, Map<String,String> params)
    {
        //1.创建请求客户端
        OkHttpClient okHttpClient = new OkHttpClient();
        //创建表单
        FormBody.Builder bodyBuilder = new FormBody.Builder();

        //因为不确定有多少个 只能用个for循环来做
        for (Map.Entry<String,String> entry:params.entrySet())
        {
            bodyBuilder.add(entry.getKey(),entry.getValue());
        }

        //创建请求体
        RequestBody body = bodyBuilder.build();

        //创建请求参数
        Request request = new Request.Builder().url(url).post(body).build();
        doRequest(request);


    }

    private void doRequest(Request request) {
        //创建请求对象
        Call call = okHttpClient.newCall(request);

        //发起异步的请求
        call.enqueue(new Callback() {
            @Override
            /**
             * 请求发生异常
             */
            public void onFailure(Call call, IOException e) {

            }

            @Override
            /**
             * 获取到服务器数据,即使是 404 等错误状态 也是 获取到服务器数据了
             */
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful())
                {
                    String result = response.body().string();
                    LogUtils.e(TAG,"OkHttpTestActivity.getInChildThread,result="+result);

                }
            }
        });
    }


}
