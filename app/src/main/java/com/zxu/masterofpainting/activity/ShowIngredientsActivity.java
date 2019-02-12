package com.zxu.masterofpainting.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.zxu.masterofpainting.Adapter.MyPagerAdapter;
import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.fragment.EdibleEfficacyFragment;
import com.zxu.masterofpainting.fragment.NutritionalComponentsFragment;
import com.zxu.masterofpainting.fragment.SuitableAvoidFragment;

import java.util.ArrayList;
import cn.hugeterry.coordinatortablayout.CoordinatorTabLayout;
import dmax.dialog.SpotsDialog;

public class ShowIngredientsActivity extends AppCompatActivity {
    private CoordinatorTabLayout mCoordinatorTabLayout;
    private int[] mImageArray, mColorArray;
    private ArrayList<Fragment> mFragments;
    private final String[] mTitles = {"营养成分", "食材功效", "食用宜忌"};
    private ViewPager mViewPager;
    //private SpotsDialog spotsDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_ingredients);
        initView();
        //spotsDialog.show();
        initFragments();
        initViewPager();
        mImageArray = new int[]{
                R.mipmap.bg_android,
                R.mipmap.bg_ios,
                R.mipmap.bg_js};
        mColorArray = new int[]{
                android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light};

        mCoordinatorTabLayout = (CoordinatorTabLayout) findViewById(R.id.coordinatortablayout);
        mCoordinatorTabLayout.setTranslucentStatusBar(this)
                .setTitle("食材详情")
                .setBackEnable(true)
                .setImageArray(mImageArray, mColorArray)
                .setupWithViewPager(mViewPager);

    }

    private void initView() {

    }

    private void initFragments(){
        mFragments = new ArrayList<>();
        NutritionalComponentsFragment nutritionalComponentsFragment = new NutritionalComponentsFragment();
        EdibleEfficacyFragment edibleEfficacyFragment = new EdibleEfficacyFragment();
        SuitableAvoidFragment suitableAvoidFragment = new SuitableAvoidFragment();
        mFragments.add(nutritionalComponentsFragment);
        mFragments.add(edibleEfficacyFragment);
        mFragments.add(suitableAvoidFragment);


//        for (String title : mTitles) {
//            mFragments.add(ShowDetailFragment.getInstance(title));
//        }
    }

    private void initViewPager(){
        mViewPager = (ViewPager) findViewById(R.id.vp);
        mViewPager.setOffscreenPageLimit(4);
        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), mFragments, mTitles));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
