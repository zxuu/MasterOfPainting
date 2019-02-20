package com.zxu.masterofpainting.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.zxu.masterofpainting.R;

public class PhysiqueActivity extends AppCompatActivity {
    private String physique;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physique);

        initView();
    }

    private void initView() {
        physique = getIntent().getStringExtra("physique");
        Toast.makeText(this, physique, Toast.LENGTH_SHORT).show();
    }
}
