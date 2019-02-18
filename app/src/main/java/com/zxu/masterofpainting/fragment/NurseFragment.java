package com.zxu.masterofpainting.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.zxu.masterofpainting.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class NurseFragment extends Fragment {
    public Button button;
    public ImageView imageView;

    public NurseFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_life,container,false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        button = (Button) view.findViewById(R.id.bmob_update_id_cancel);
    }

}
