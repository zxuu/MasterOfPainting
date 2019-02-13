package com.zxu.masterofpainting.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.zxu.masterofpainting.Contants;
import com.zxu.masterofpainting.R;

public class EdibleEfficacyFragment extends Fragment {
    private TextView edibleEffiTextView;
    private String edibleEfficacyStr;
    private String[] edibleEfficacyStrSplit;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edible_efficacy, container, false);
        initView(view);
        getData();
        setEdibleEfficacyStrData();
        return view;
    }

    private void initView(View view) {
        edibleEffiTextView = (TextView) view.findViewById(R.id.edible_efficacy_text_view);
    }

    private void getData() {
        if (Contants.ingredientsEfficiency != null) {
            edibleEfficacyStr = Contants.ingredientsEfficiency;
        } else {

        }
    }

    private void setEdibleEfficacyStrData() {
        edibleEffiTextView.setText(edibleEfficacyStr);
        edibleEfficacyStrSplit = edibleEfficacyStr.split("#");
        Toast.makeText(getContext(), edibleEfficacyStrSplit.length + "", Toast.LENGTH_SHORT).show();
    }
}
