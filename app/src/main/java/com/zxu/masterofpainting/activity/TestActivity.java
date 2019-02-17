package com.zxu.masterofpainting.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.bean.Collocation;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class TestActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initView();
        getAllData();
    }

    private void getAllData() {
        BmobQuery<Collocation> collocationBmobQuery = new BmobQuery<>("CollocationActivity");
        collocationBmobQuery.setLimit(1000);
        collocationBmobQuery.findObjects(new FindListener<Collocation>() {
            @Override
            public void done(List<Collocation> list, BmobException e) {
                for (int i = 0; i < list.size(); i++) {
                    textView.append(list.get(i).getCollocationName()+",");
                }
            }
        });
    }

    private void initView() {
        textView = (TextView) findViewById(R.id.text_view);
    }
}
