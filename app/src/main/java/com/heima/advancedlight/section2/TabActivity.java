package com.heima.advancedlight.section2;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.heima.advancedlight.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TabActivity extends AppCompatActivity {

    @BindView(R.id.tb)
    Toolbar tb;
    @BindView(R.id.tl)
    TabLayout tl;
    @BindView(R.id.abl)
    AppBarLayout abl;
    @BindView(R.id.vp)
    ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        ButterKnife.bind(this);
        setSupportActionBar(tb);

        initVp();
    }

    private void initVp() {
        List<Fragment> fragmentList = new ArrayList<>();
        List<String> title = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            title.add("页面" + i);
            fragmentList.add(new RecyclerFragment());
        }
        FragmentStatePagerAdapter adapter=new FragmentAdapter(getSupportFragmentManager(), title, fragmentList);
        tl.setupWithViewPager(vp);
        vp.setAdapter(adapter);
    }


}
