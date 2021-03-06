package com.zxu.masterofpainting.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.zxu.masterofpainting.Adapter.MyPagerAdapter;
import com.zxu.masterofpainting.Constants;
import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.bean.Ingredients;
import com.zxu.masterofpainting.fragment.EdibleEfficacyFragment;
import com.zxu.masterofpainting.fragment.NutritionalComponentsFragment;
import com.zxu.masterofpainting.fragment.SuitableAvoidFragment;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.hugeterry.coordinatortablayout.CoordinatorTabLayout;
import dmax.dialog.SpotsDialog;

public class ShowIngredientsActivity extends AppCompatActivity {
    private CoordinatorTabLayout mCoordinatorTabLayout;
    private int[] mImageArray, mColorArray;
    private ArrayList<Fragment> mFragments;
    private final String[] mTitles = {"营养成分", "食材功效", "营养搭配"};
    private ViewPager mViewPager;
    private BmobQuery<Ingredients> ingredientsBmobQuery;
    //食材名称
    private String ingredientsName;
    private SpotsDialog spotsDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_ingredients);

        initView();
        getIngredientsData();
        setCoordinatorTabLayoutData();
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.vp);
        mViewPager.setOffscreenPageLimit(4);
        spotsDialog = new SpotsDialog(this,"拼命加载中...");
        spotsDialog.show();
    }

    private void getIngredientsData(){
        ingredientsName = "银耳";
        Toast.makeText(this, ingredientsName, Toast.LENGTH_SHORT).show();
        Constants.ingredientsName = ingredientsName;
        ingredientsBmobQuery = new BmobQuery<>("Ingredients");
        ingredientsBmobQuery.addWhereEqualTo("IngredientsName", ingredientsName);
        ingredientsBmobQuery.findObjects(new FindListener<Ingredients>() {
            @Override
            public void done(List<Ingredients> list, BmobException e) {
                if (list != null) {
                    for (int i = 0; i < list.size(); i++) {
                        Ingredients correctIngredients = list.get(i);
                        if (correctIngredients.getIngredientsName().equals(ingredientsName)) {
                            Constants.ingredientsNutrution= correctIngredients.getNutrition();
                            Constants.ingredientsEfficiency = correctIngredients.getEfficiency();
                            Constants.ingredientsSuitableCollocation = correctIngredients.getSuitableCollocation();
                            initFragments();
                            initViewPager();
                            spotsDialog.dismiss();
                            break;
                        }
                    }
                } else {
                    Toast.makeText(ShowIngredientsActivity.this, "list is null", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setCoordinatorTabLayoutData(){
        mImageArray = new int[]{
                R.mipmap.yiner,
                R.mipmap.yiner,
                R.mipmap.yiner};
        mColorArray = new int[]{
                android.R.color.holo_blue_light,
                android.R.color.holo_orange_dark,
                android.R.color.holo_orange_light};

        mCoordinatorTabLayout = (CoordinatorTabLayout) findViewById(R.id.coordinatortablayout);
        mCoordinatorTabLayout.setTranslucentStatusBar(this)
                .setTitle("                  "+ingredientsName)
                .setBackEnable(true)
                .setImageArray(mImageArray, mColorArray)
                .setupWithViewPager(mViewPager);
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
