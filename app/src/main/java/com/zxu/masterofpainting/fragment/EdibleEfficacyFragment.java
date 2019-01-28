package com.zxu.masterofpainting.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zxu.masterofpainting.R;

public class EdibleEfficacyFragment extends Fragment {
    private TextView edibleEffiTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edible_efficacy, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        edibleEffiTextView = (TextView) view.findViewById(R.id.edible_efficacy_text_view);
        edibleEffiTextView.setText("dfsgj\nd\nsd\nsdf\nasfd\nadf\ner\ret\nsdf\nsdf\nsdf\nasde\nsad\nasd\nsadf\nwer\naf\nqwe\n" +
                "ds\nasd\nert\nef\ndfg\nyuti\nsxzc\ndf\nsdf\nsdf\ndf\ndsfg\nsdf\newr\ncvb\ndfg\ndfg\new");
    }
}
