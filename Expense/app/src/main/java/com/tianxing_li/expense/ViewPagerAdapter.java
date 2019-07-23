package com.tianxing_li.expense;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment demoFragment = null;

        switch (position) {
            case 0:
                demoFragment = new DemoFragment1();
                break;
            case 1:
                demoFragment = new DemoFragment2();
                break;
            case 2:
                demoFragment = new DemoFragment3();
                break;
        }
        //DemoFragment1 demoFragment = new DemoFragment1();
        position = position + 1;
        Bundle bundle = new Bundle();
        bundle.putString("message", "Fragment: " + position);
        demoFragment.setArguments(bundle);
        return demoFragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        position = position + 1;
        return "Fragment " + position;
    }
}
