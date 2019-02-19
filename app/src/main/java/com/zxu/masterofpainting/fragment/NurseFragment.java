package com.zxu.masterofpainting.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.zxu.masterofpainting.Adapter.LabelMenuAdapter;
import com.zxu.masterofpainting.Adapter.LabelMenuContenAdapter;
import com.zxu.masterofpainting.Constants;
import com.zxu.masterofpainting.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NurseFragment extends Fragment{
    private List<String> menuList = new ArrayList<>();
    private List<Integer> showTitle = new ArrayList<>();

    private ListView lv_menu;
    private ListView lv_home;

    private LabelMenuAdapter menuAdapter;
    private LabelMenuContenAdapter homeAdapter;
    private int currentItem;

    private TextView tv_title;

    private String[] labelNameMenu;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_test,container,false);
        initView(view);
        loadData();
        return view;
    }

    private void initView(View view) {
        lv_menu = (ListView) view.findViewById(R.id.lv_menu);
        tv_title = (TextView) view.findViewById(R.id.tv_titile);
        lv_home = (ListView) view.findViewById(R.id.lv_home);


        menuAdapter = new LabelMenuAdapter(getContext(), menuList);
        lv_menu.setAdapter(menuAdapter);
        lv_menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                menuAdapter.setSelectItem(position);
                menuAdapter.notifyDataSetInvalidated();
                tv_title.setText(menuList.get(position));
                lv_home.setSelection(showTitle.get(position));
            }
        });

        homeAdapter = new LabelMenuContenAdapter(getContext(), menuList);
        lv_home.setAdapter(homeAdapter);
        lv_home.setOnScrollListener(new AbsListView.OnScrollListener() {
            private int scrollState;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                this.scrollState = scrollState;
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    return;
                }
                int current = showTitle.indexOf(firstVisibleItem);
//				lv_home.setSelection(current);
                if (currentItem != current && current >= 0) {
                    currentItem = current;
                    tv_title.setText(menuList.get(currentItem));
                    menuAdapter.setSelectItem(currentItem);
                    menuAdapter.notifyDataSetInvalidated();
                }
            }
        });
    }

    private void loadData(){
        labelNameMenu = Constants.labelNameMenu;
        for (int i = 0; i < labelNameMenu.length; i++) {
            menuList.add(labelNameMenu[i]);
            showTitle.add(i);
        }
        tv_title.setText(Constants.labelNameMenu[0]);
        menuAdapter.notifyDataSetChanged();
        homeAdapter.notifyDataSetChanged();
    }
}
