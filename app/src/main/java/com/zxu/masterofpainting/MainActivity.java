package com.zxu.masterofpainting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import com.zxu.masterofpainting.bean.Tab;
import com.zxu.masterofpainting.fragment.LookFragment;
import com.zxu.masterofpainting.fragment.PhotoInfoFragment;
import com.zxu.masterofpainting.fragment.TakePhotoFragment;
import com.zxu.masterofpainting.fragment.UserInfoFragment;
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
        Tab tab_photoinfo = new Tab(PhotoInfoFragment.class,R.string.photoinfo,R.drawable.selector_icon_photoinfo);
        Tab tab_look = new Tab(LookFragment.class,R.string.look,R.drawable.selector_icon_look);
        Tab tab_user = new Tab(UserInfoFragment.class,R.string.user,R.drawable.selector_user_info);

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
