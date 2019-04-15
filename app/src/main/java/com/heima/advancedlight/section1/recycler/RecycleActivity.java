package com.heima.advancedlight.section1.recycler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.heima.advancedlight.R;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecycleActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle("1.RecyclerView与分割线");

        List<String>list=Arrays.asList("a","b","c","d","e","f","g");
        RecyclerAdapter recyclerAdapter=new RecyclerAdapter(list);
        recyclerAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(view.getContext(), "短按", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(view.getContext(), "长按"+position, Toast.LENGTH_SHORT).show();
            }
        });

        //设置布局管理器
//        LinearLayoutManager layoutManager= new LinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL);
//        recyclerView.addItemDecoration( dividerItemDecoration);
        StaggeredGridLayoutManager layoutManager= new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerAdapter);
    }
}
