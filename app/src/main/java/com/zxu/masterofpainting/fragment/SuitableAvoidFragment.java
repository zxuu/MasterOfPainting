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

public class SuitableAvoidFragment extends Fragment {
    private TextView suitableAvoidTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_suitable_avoid, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        suitableAvoidTextView = (TextView) view.findViewById(R.id.suitable_avoid_text_view);
        suitableAvoidTextView.setText("dfsgj\nd\nsd\nsdf\nasfd\nadf\ner\ret\nsdf\nsdf\nsdf\nasde\nsad\nasd\nsadf\nwer\naf\nqwe\n" +
                "ds\nasd\nert\nef\ndfg\nyuti\nsxzc\ndf\nsdf\nsdf\ndf\ndsfg\nsdf\newr\ncvb\ndfg\ndfg\new");
    }
}
