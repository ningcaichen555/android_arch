package com.example.android_arch.fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.android_arch.R;

import java.util.List;

public class FragmentActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    private List<Fragment> fragmentList;


    private LinearLayout fragmentRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        viewPager = findViewById(R.id.viewPager);
        fragmentRoot = findViewById(R.id.fragmentRoot);

        AnalysisFragment analysisFragment = new AnalysisFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fragmentRoot, analysisFragment);
        transaction.commit();

//        pagerAdapter = new PagerAdapter() {
//            @Override
//            public int getCount() {
//                return fragmentList == null ? 0 : fragmentList.size();
//            }
//
//            @Override
//            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
//                return false;
//            }
//        };
//        viewPager.setAdapter(pagerAdapter);
    }
}