package com.example.emptyactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TabLayout tablayout = findViewById(R.id.tab_layout);
        tablayout.addTab(tablayout.newTab().setText(R.string.tab_label1));
        tablayout.addTab(tablayout.newTab().setText(R.string.tab_label3));
        tablayout.addTab(tablayout.newTab().setText(R.string.tab_label2));
        tablayout.setTabGravity(tablayout.GRAVITY_FILL);
        //add PagerAdapter to manage screen page views
        //view pager allows sliding b/w fragments to make tab interface
        //rep each page as fragment via adapter, yet are stored in memory so for larger tabs
        //can use up lots of space
        final ViewPager viewpager = findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tablayout.getTabCount());
        viewpager.setAdapter(adapter);
        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));
        //curly braces indicate methods of abstract class/interface being implemented
        //call listener to wait for action, then choose method and continue
        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                viewpager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab){}
            @Override
            public void onTabReselected(TabLayout.Tab tab){}
        });
    }
}
