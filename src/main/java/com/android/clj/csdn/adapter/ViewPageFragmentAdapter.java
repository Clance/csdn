package com.android.clj.csdn.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.android.clj.csdn.fragment.NewsFragment;

/**
 * Created by Administrator on 2015/10/23.
 */
public class ViewPageFragmentAdapter extends FragmentStatePagerAdapter {
    private String[] mItems;

    public ViewPageFragmentAdapter(String[] items,FragmentManager fm) {
        super(fm);
        this.mItems = items;
    }

    @Override
    public Fragment getItem(int position) {
        return new NewsFragment(position+1);
    }

    @Override
    public int getCount() {
        return mItems.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mItems[position];
    }
}
