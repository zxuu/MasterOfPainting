package com.zxu.masterofpainting.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.bean.Tab;
import com.zxu.masterofpainting.fragment.NurseFragment;
import com.zxu.masterofpainting.fragment.DetectionFragment;
import com.zxu.masterofpainting.fragment.TakePhotoFragment;
import com.zxu.masterofpainting.fragment.MyFragment;
import com.zxu.masterofpainting.widget.FragmentTabHost;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private FragmentTabHost mTabhost;
    private LayoutInflater mInflater;
    private List<Tab> mTabs = new ArrayList<>(4);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initTab();
    }

    private void initTab() {
        Tab tab_takephoto = new Tab(TakePhotoFragment.class,R.string.takephoto,R.drawable.selector_icon_takephoto);
        Tab tab_photoinfo = new Tab(DetectionFragment.class,R.string.detection,R.drawable.selector_icon_detection);
        Tab tab_look = new Tab(NurseFragment.class,R.string.nurse,R.drawable.selector_icon_nurse);
        Tab tab_user = new Tab(MyFragment.class,R.string.user,R.drawable.selector_user_info);

        mTabs.add(tab_takephoto);
        mTabs.add(tab_photoinfo);
        mTabs.add(tab_look);
        mTabs.add(tab_user);
        mInflater = LayoutInflater.from(this);
        mTabhost = (FragmentTabHost) this.findViewById(R.id.tabhost);
        mTabhost.setup(this,getSupportFragmentManager(),R.id.realtabcontent);

        for (Tab tab : mTabs){
            TabHost.TabSpec tabSpec = mTabhost.newTabSpec(getString(tab.getTitle()));
            tabSpec.setIndicator(buildIndicator(tab));
            mTabhost.addTab(tabSpec,tab.getFragment(),null);

        }
    }

    private  View buildIndicator(Tab tab){
        View view =mInflater.inflate(R.layout.tab_indicator,null);
        ImageView img = (ImageView) view.findViewById(R.id.icon_tab);
        TextView text = (TextView) view.findViewById(R.id.txt_indicator);
        img.setBackgroundResource(tab.getIcon());
        text.setText(tab.getTitle());
        return  view;
    }


}
