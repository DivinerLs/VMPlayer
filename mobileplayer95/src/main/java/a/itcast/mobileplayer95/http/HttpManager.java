package a.itcast.mobileplayer95.http;

import android.os.Looper;

import com.google.gson.Gson;

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

    private android.os.Handler mHandler;

    private HttpManager() {
        this.okHttpClient = new OkHttpClient();
        mHandler = new android.os.Handler(Looper.getMainLooper());
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
    public void get(String url,BaseCallBack baseCallBack){

        //创建请求参数
        Request request = new Request.Builder().url(url).build();

        doRequest(request,baseCallBack);
    }

    /**
     * 发起 post 请求
     * @param url
     */
    public void post(String url, Map<String,String> params,BaseCallBack baseCallBack)
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
        doRequest(request,baseCallBack);


    }

    private void doRequest(final Request request, final BaseCallBack baseCallBack) {
        //创建请求对象
        Call call = okHttpClient.newCall(request);

        //发起异步的请求
        call.enqueue(new Callback() {
            @Override
            /**
             * 请求发生异常
             */
            public void onFailure(Call call, final IOException e) {
                //BaseCallBack.java
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        baseCallBack.onFailure(-1,e);//-1 是自定义的code码
                    }
                });
            }

            @Override
            /**
             * 获取到服务器数据,即使是 404 等错误状态 也是 获取到服务器数据了
             */
            public void onResponse(Call call, final Response response) throws IOException {

                LogUtils.e(TAG,"HttpManager.onResponse,thread="+Thread.currentThread());
                //在子线程读取服务器数据
                final String result = response.body().string();
                //在主线程更新界面
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (response.isSuccessful())
                        {
                            /*
                                String result = response.body().string();
                                这里爆了个异常 IO异常 原因:
                                调用.string()的时候 它才联网 去把数据读取出来,并且转换成String,返回.
                                做法:把这行代码放在外面执行
                             */
                            //String result = response.body().string();

                            //根据 baseCallBack 的类型的不同,做不同的数据解析
                            if (baseCallBack.type == String.class)
                            {
                                //如果传过来的是一个 String 直接返回String类型的数据
                                baseCallBack.onSuccess(result);
                            }else {
                                try {
                                    //指定了一个 Bean,直接进行 Json 转换
                                    Object obj = new Gson().fromJson(result,baseCallBack.type);
                                    //BaseCallBack.java
                                    baseCallBack.onSuccess(obj);
                                } catch (Exception e) {
                                    baseCallBack.onFailure(-1,new RuntimeException("JSON解析出错"+result));
                                    e.printStackTrace();
                                }
                            }

//                    LogUtils.e(TAG,"OkHttpTestActivity.getInChildThread,result="+result);

                        }else {
                            baseCallBack.onFailure(response.code(),new RuntimeException("获取到服务器的错误状态"));
                        }
                    }
                });
            }
        });
    }


}
