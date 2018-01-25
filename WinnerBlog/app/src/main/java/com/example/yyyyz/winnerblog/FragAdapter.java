package com.example.yyyyz.winnerblog;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Yyyyz on 2018/1/18.
 */

public class FragAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragments;

    public FragAdapter(FragmentManager fm, List<Fragment> mFragments){
        super(fm);
        this.mFragments = mFragments;
    }
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
