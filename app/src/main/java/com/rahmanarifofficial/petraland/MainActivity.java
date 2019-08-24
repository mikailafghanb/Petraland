package com.rahmanarifofficial.petraland;

import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.rahmanarifofficial.petraland.adapter.TabPagerAdapter;
import com.rahmanarifofficial.petraland.view.fragment.BerandaFragment;
import com.rahmanarifofficial.petraland.view.fragment.ProfilFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews(){
        TabLayout tabLayout = findViewById(R.id.tablayout);
        ViewPager pager = findViewById(R.id.viewpager_main);

        setupViewPager(pager);
        tabLayout.setupWithViewPager(pager);
    }

    private void setupViewPager(ViewPager pager) {
        TabPagerAdapter tabPagerAdapter = new TabPagerAdapter(getSupportFragmentManager());
        tabPagerAdapter.addFragment(new BerandaFragment(), getString(R.string.tab_beranda));
        tabPagerAdapter.addFragment(new ProfilFragment(), getString(R.string.tab_profil));
        pager.setAdapter(tabPagerAdapter);
    }
}
