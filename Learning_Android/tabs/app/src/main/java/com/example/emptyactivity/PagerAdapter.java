package com.example.emptyactivity;

import android.graphics.pdf.PdfDocument;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

//keep track of number of tabs and add switch to move b/w them, return fragment each time
public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    public PagerAdapter(FragmentManager fm, int mNumOfTabs)
    {
        //refer to immediate parent class obj
        super(fm);
        this.mNumOfTabs = mNumOfTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new TabFragment1();
            case 1:
                return new TabFragment2();
            case 3:
                return new TabFragment3();
            default:
                return null;
        }
    }
    @Override
    public int getCount()
    {
        return mNumOfTabs;
    }
}
