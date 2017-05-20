package a.itcast.mobileplayer95.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import a.itcast.mobileplayer95.R;
import a.itcast.mobileplayer95.bean.AreaBean;
import a.itcast.mobileplayer95.http.BaseCallBack;
import a.itcast.mobileplayer95.http.HttpManager;
import a.itcast.mobileplayer95.utils.LogUtils;
import a.itcast.mobileplayer95.utils.URLProviderUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpTestActivity extends AppCompatActivity {

    private static final String TAG = "OkHttpTestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);
        //加载数据
        loadData();
    }

    /**
     * 加载数据(测试)
     */
    private void loadData() {

//        String url = "192.168.78.21:8080/1";
//        String url = URLProviderUtil.getMainPageUrl(0,10);
        String url = URLProviderUtil.getMVareaUrl();
          LogUtils.e(TAG,"OkHttpTestActivity.loadData,url="+url);
//        getMethod(url);//自己开子线程访问网络
//        getInChildThread(url);//okhttp自带的子线程访问网络 (get)
//        postInChildThread(url);

        HttpManager.getInstance().get(url, new BaseCallBack<List<AreaBean>>() {
            @Override
            public void onFailure(int code, Exception e) {
                LogUtils.e(TAG,"OkHttpTestActivity.onFailure,e="+e);
            }

//            @Override
//            public void onSuccess(String s) {
//                LogUtils.e(TAG,"OkHttpTestActivity.onSuccess,s="+s);
//            }

            @Override
            public void onSuccess(List<AreaBean> areaBeen) {
                LogUtils.e(TAG,"OkHttpTestActivity.onSuccess,areaBeen="+areaBeen.size());
                Toast.makeText(OkHttpTestActivity.this, "哈哈", Toast.LENGTH_SHORT).show();
            }


        });
    }

    /**
     * 在子线程发起网络请求(okhttp自带的子线程(post))
     * @param url
     */
    private void postInChildThread(String url)
    {
        //1.创建请求客户端
        OkHttpClient okHttpClient = new OkHttpClient();
        //创建表单
        FormBody.Builder bodyBuilder = new FormBody.Builder();
        bodyBuilder.add("offset","0");
        bodyBuilder.add("size","10");
        //创建请求体
        RequestBody body = bodyBuilder.build();
        //创建请求参数
        Request request = new Request.Builder().url(url).post(body).build();
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

    /**
     * 在子线程发起网络请求(okhttp自带的子线程(get))
     */
    private void getInChildThread(String url) {
        //1.创建请求客户端
        OkHttpClient okHttpClient = new OkHttpClient();

        //创建请求参数
        Request request = new Request.Builder().url(url).build();

        //创建请求对象
        Call call = okHttpClient.newCall(request);

        //发起异步的请求
        call.enqueue(new Callback() {

            @Override
            //请求发生异常
            public void onFailure(Call call, IOException e) {

            }

            @Override
            //获取到服务器数据,即使是 404 等错误状态 也是 获取到服务器数据了
            public void onResponse(Call call, Response response) throws IOException {

                if (response.isSuccessful())
                {
                    String result = response.body().string();
                    LogUtils.e(TAG,"OkHttpTestActivity.getInChildThread,result="+result);
                }
            }
        });
    }

    /**
     * 访问网络的请求一定要放在子线程里面 (放在主线程里面会出错)
     * 自己开子线程访问网络
     */
    private void getMethod(final String url) {

      new Thread(){
          @Override
          public void run() {
              try {
                  //1.创建请求客户端
                  OkHttpClient okHttpClient = new OkHttpClient();

                  //创建请求参数
                  Request request = new Request.Builder().url(url).build();

                  //创建请求对象
                  Call call = okHttpClient.newCall(request);

                  //执行请求,获取服务器响应
                  Response response = call.execute();

                  //获取服务器数据
                  if (response.isSuccessful()){
                      String result = response.body().string();
                      LogUtils.e(TAG,"OkHttpTestActivity.getMethod,result="+result);
                  }


              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }.start();


    }
}
