package com.zxu.masterofpainting.activity;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zxu.masterofpainting.R;

public class CollocationActivity extends AppCompatActivity {
    //识别的食材名字
    private String collocationname1;
    //搭配的食材
    private String collocationname2;
    //搭配图片的URL
    private String urlstr;
    private SimpleDraweeView simpleDraweeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collocation);
        initView();
        getAllData();
    }

    private void initView() {
        simpleDraweeView = (SimpleDraweeView) findViewById(R.id.fruit_image_view);
    }

    private void getAllData() {
        Intent intent = getIntent();
        collocationname1 = intent.getStringExtra("collocationName1");
        collocationname2 = intent.getStringExtra("collocationName2");
        urlstr = intent.getStringExtra("urlstring");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout)
                findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("         " + collocationname1 + "+" + collocationname2);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        simpleDraweeView.setImageURI(urlstr);
    }
}
