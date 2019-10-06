package com.example.hyuna.techlab20;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    Fragment[] fragment = new Fragment[3];
    public ViewPagerAdapter(FragmentManager fm){
        super(fm);
        //메인 , 월 프래그먼트
        fragment[0]= new FragmentMain();
        fragment[1]= new FragmentMonth();
        fragment[2]= new FragmentListAdd();
    }

    @Override
    public Fragment getItem(int i) {
        //어떤 프레그먼터 보여줄지 결정
        return fragment[i];
    }

    @Override
    public int getCount() {
        //보여질 프래그먼트 개수 반환
        return fragment.length;
    }

}
