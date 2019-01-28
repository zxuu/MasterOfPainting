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

public class NutritionalComponentsFragment extends Fragment {
    private TextView textView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nutritional_components, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        textView = (TextView) view.findViewById(R.id.nutritional_components_textview);
        textView.setText("dfsgj\nd\nsd\nsdf\nasfd\nadf\ner\ret\nsdf\nsdf\nsdf\nasde\nsad\nasd\nsadf\nwer\naf\nqwe\n" +
                "ds\nasd\nert\nef\ndfg\nyuti\nsxzc\ndf\nsdf\nsdf\ndf\ndsfg\nsdf\newr\ncvb\ndfg\ndfg\new");
    }
}
