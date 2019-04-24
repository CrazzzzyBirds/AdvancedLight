package com.heima.advancedlight.section5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.heima.advancedlight.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpActivity extends AppCompatActivity {
    private  static  String TAG="HM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
        getDataSync();
    }

    /**
     * 同步get
     */
    public void getDataSync(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象
                    Request request = new Request.Builder()
                            .url("http://www.baidu.com")//请求接口。如果需要传参拼接到接口后面。
                            .build();//创建Request 对象
                    Response response = null;
                    response = client.newCall(request).execute();//得到Response 对象
                    if (response.isSuccessful()) {
                        Log.d(TAG,"response.code() = "+response.code()); //协议200  成功
                        Log.d(TAG,"response.message() = "+response.message());
                        Log.d(TAG,"res=="+response.body().string());
                        //子线程，修改UI的操作请使用handler跳转到UI线程。
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    /**
     * 异步get
     */
    private void getDataAsync() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://www.baidu.com")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){//回调的方法执行在子线程。
                    Log.d(TAG,"获取数据成功");
                    Log.d(TAG,"response.code() = "+response.code());
                    Log.d(TAG,"response.body().string() = "+response.body().string());
                }
            }
        });
    }
}
