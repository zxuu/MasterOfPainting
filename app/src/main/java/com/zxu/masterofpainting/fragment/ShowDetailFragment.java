package com.zxu.masterofpainting.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zxu.masterofpainting.Adapter.RecyclerAdapter;
import com.zxu.masterofpainting.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zxu
 */
public class ShowDetailFragment extends Fragment {
    private RecyclerView mRecyclerView;

    private List<String> mDatas;
    private static final String ARG_TITLE = "title";
    private String mTitle;

    public static ShowDetailFragment getInstance(String title) {
        ShowDetailFragment fra = new ShowDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_TITLE, title);
        fra.setArguments(bundle);
        return fra;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        mTitle = bundle.getString(ARG_TITLE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        initData();
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
        mRecyclerView.setAdapter(new RecyclerAdapter(mRecyclerView.getContext(), mDatas));

        return v;
    }

    private void initData() {
        mDatas = new ArrayList<>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add(mTitle + (char) i);
        }
    }

}