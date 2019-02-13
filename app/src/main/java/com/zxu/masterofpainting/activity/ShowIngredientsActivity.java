package com.zxu.masterofpainting.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.zxu.masterofpainting.Adapter.MyPagerAdapter;
import com.zxu.masterofpainting.Contants;
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
    private final String[] mTitles = {"营养成分", "食材功效", "食用宜忌"};
    private ViewPager mViewPager;
    private BmobQuery<Ingredients> ingredientsBmobQuery;
    private String ingredientsName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_ingredients);
        initView();
        getIngredientsData();
        //initFragments();
        //initViewPager();
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
        mViewPager = (ViewPager) findViewById(R.id.vp);
        mViewPager.setOffscreenPageLimit(4);
    }

    private void getIngredientsData(){
        ingredientsName = "香菇";
        Contants.ingredientsName = ingredientsName;
        mFragments = new ArrayList<>();
        ingredientsBmobQuery = new BmobQuery<>("Ingredients");
        ingredientsBmobQuery.addWhereEqualTo("IngredientsName", ingredientsName);
        ingredientsBmobQuery.findObjects(new FindListener<Ingredients>() {
            @Override
            public void done(List<Ingredients> list, BmobException e) {
                if (list != null) {
                    for (int i = 0; i < list.size(); i++) {
                        Ingredients correctIngredients = list.get(i);
                        if (correctIngredients.getIngredientsName().equals(ingredientsName)) {
                            Contants.ingredientsNutrution= correctIngredients.getNutrition();
                            Contants.ingredientsEfficiency = correctIngredients.getEfficiency();
                            Contants.ingredientsSuitableCollocation = correctIngredients.getSuitableCollocation();
//                            NutritionalComponentsFragment nutritionalComponentsFragment = new NutritionalComponentsFragment();
//                            Bundle bundle1 = new Bundle();
//                            bundle1.putString("ingredientsNutrution",correctIngredients.getNutrition());
//                            nutritionalComponentsFragment.setArguments(bundle1);
//                            EdibleEfficacyFragment edibleEfficacyFragment = new EdibleEfficacyFragment();
//                            Bundle bundle2 = new Bundle();
//                            bundle1.putString("ingredientsEfficiency",correctIngredients.getEfficiency());
//                            edibleEfficacyFragment.setArguments(bundle2);
//                            SuitableAvoidFragment suitableAvoidFragment = new SuitableAvoidFragment();
//                            Bundle bundle3 = new Bundle();
//                            bundle1.putString("ingredientsSuitableCollocation",correctIngredients.getSuitableCollocation());
//                            suitableAvoidFragment.setArguments(bundle3);
//                            mFragments.add(nutritionalComponentsFragment);
//                            mFragments.add(edibleEfficacyFragment);
//                            mFragments.add(suitableAvoidFragment);
                            initFragments();
                            initViewPager();
                            break;
                        }
                    }
                } else {
                    Toast.makeText(ShowIngredientsActivity.this, "list is null", Toast.LENGTH_SHORT).show();
                }
            }
        });
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
