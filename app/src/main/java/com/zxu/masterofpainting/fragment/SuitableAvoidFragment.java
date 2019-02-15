package com.zxu.masterofpainting.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.zxu.masterofpainting.Adapter.SuitableAvoidAdapter;
import com.zxu.masterofpainting.Constants;
import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.bean.SuitableAvoidItem;

import java.util.ArrayList;
import java.util.List;

public class SuitableAvoidFragment extends Fragment {
    private String suitableAvoidStr;
    private RecyclerView collocationRecyclerView;
    private List<SuitableAvoidItem> mSuitableAvoidItemList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_suitable_avoid, container, false);
        initView(view);
        getAllData();
        return view;
    }

    private void initView(View view) {
        collocationRecyclerView = (RecyclerView) view.findViewById(R.id.collocation_recyclerview);
    }

    private void getAllData(){
        if (Constants.ingredientsSuitableCollocation != null) {
            suitableAvoidStr = Constants.ingredientsSuitableCollocation;
            String[] splitString = suitableAvoidStr.split(";");
            for (int j = 0; j < splitString.length; j++) {
                String[] childsplit = splitString[j].split(",");
                mSuitableAvoidItemList.add(new SuitableAvoidItem(childsplit[0], childsplit[1]));
            }
            setSuitableAvoidData();
        } else {
            Toast.makeText(getContext(), "哎呦~没有适宜搭配呀~", Toast.LENGTH_SHORT).show();
        }
    }

    private void setSuitableAvoidData(){
        StaggeredGridLayoutManager layoutManager = new
                StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        collocationRecyclerView.setLayoutManager(layoutManager);
        SuitableAvoidAdapter suitableAvoidAdapter = new SuitableAvoidAdapter(mSuitableAvoidItemList);
        collocationRecyclerView.setAdapter(suitableAvoidAdapter);
    }
}
