package com.heima.advancedlight.section2;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

import java.util.List;

public class FragmentAdapter extends FragmentStatePagerAdapter {
    List<Fragment>fragmentList;
    List<String>title;


    public FragmentAdapter(FragmentManager fm, List<String> title, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
        this.title = title;
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return title.get(position);
    }
}
