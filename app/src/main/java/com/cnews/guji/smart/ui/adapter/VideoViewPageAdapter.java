package com.cnews.guji.smart.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @author JSYL-DCL
 * @date 2018/10/29 10:44
 * @des 页签适配器
 */
public class VideoViewPageAdapter extends FragmentPagerAdapter {
    private List<Fragment>  mFragments;
    private String[]  taskTabTitles;
    public VideoViewPageAdapter(FragmentManager fm,List<Fragment>  fragments,String[] titles) {
        super(fm);
        this.mFragments = fragments;
        this.taskTabTitles = titles;
    }


    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return taskTabTitles[position];
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }
}