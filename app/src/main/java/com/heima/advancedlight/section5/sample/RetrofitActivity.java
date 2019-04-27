package com.heima.advancedlight.section5.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.heima.advancedlight.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActivity extends AppCompatActivity {
    //https://segmentfault.com/a/1190000015144126 github api介绍
    public static final String API_URL = "https://api.github.com";
    public static final String TAG = "HMXJ";
    @BindView(R.id.bt_test1)
    Button btTest1;
    @BindView(R.id.bt_test2)
    Button btTest2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_test);
        ButterKnife.bind(this);

    }


    @OnClick({R.id.bt_test1, R.id.bt_test2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_test1:
                test1();
                break;
            case R.id.bt_test2:
                break;
        }
    }

    private void test1()  {

        // Create a very simple REST adapter which points the GitHub API.
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an instance of our GitHub API interface.
        GitHub github = retrofit.create(GitHub.class);

        // Create a call instance for looking up Retrofit contributors.
       Call<List<Contributor>> call = github.contributors("square", "retrofit");
        call.enqueue(new Callback<List<Contributor>>() {
            @Override
            public void onResponse(Call<List<Contributor>> call, Response<List<Contributor>> response) {

           //     Log.d(TAG, response.message()+":"+response.body());
//                List<Contributor> contributors=response.body();
//                for (Contributor contributor : contributors) {
//                    Log.d(TAG, contributor.login + " (" + contributor.contributions + ")");
//                }
            }

            @Override
            public void onFailure(Call<List<Contributor>> call, Throwable t) {

            }
        });


    }
}
