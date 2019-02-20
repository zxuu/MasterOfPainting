package com.zxu.masterofpainting.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.zxu.masterofpainting.Constants;
import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.utils.MyUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import link.fls.swipestack.SwipeStack;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetectionFragment extends Fragment implements SwipeStack.SwipeStackListener, View.OnClickListener{
    private ArrayList<String> mData;
    private SwipeStack mSwipeStack;
    private SwipeStackAdapter mAdapter;
    private List<Integer> scoreList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detection, container, false);

        initView(view);
        fillWithTestData();
        return view;
    }

    private void initView(View view){
        mSwipeStack = (SwipeStack) view.findViewById(R.id.swipeStack);
        mData = new ArrayList<>();
        mAdapter = new SwipeStackAdapter(mData);
        mSwipeStack.setAdapter(mAdapter);
        mSwipeStack.setListener(this);

    }
    private void fillWithTestData() {
        for (int i = 0; i < Constants.cardQuestion.length; i++) {
            for (int j = 0; j < Constants.cardQuestion[i].length; j++) {
                mData.add(Constants.cardQuestion[i][j]);
            }
        }
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onViewSwipedToRight(int position) {
        String swipedElement = mAdapter.getItem(position);
        //Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onViewSwipedToLeft(int position) {
        String swipedElement = mAdapter.getItem(position);
        //Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStackEmpty() {
        String[] l = new String[scoreList.size()];
        for (int i = 0; i < l.length; i++) {
            l[i] = String.valueOf(scoreList.get(i));
        }
        Toast.makeText(getContext(), l.length+"", Toast.LENGTH_SHORT).show();
    }

    public class SwipeStackAdapter extends BaseAdapter {

        private List<String> mData;

        public SwipeStackAdapter(List<String> data) {
            this.mData = data;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public String getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.card_question, parent, false);
            }

            TextView textViewCard = (TextView) convertView.findViewById(R.id.textViewCard);
            RadioGroup radioGroup = (RadioGroup) convertView.findViewById(R.id.radio_group);
            textViewCard.setText(mData.get(position));
            final View finalConvertView = convertView;
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    int id = group.getCheckedRadioButtonId();
                    RadioButton choise = (RadioButton) finalConvertView.findViewById(id);
                    scoreList.add(MyUtil.getScore((String) choise.getText()));
                    //Toast.makeText(getContext(), choise.getText(), Toast.LENGTH_SHORT).show();
                }
            });

            return convertView;
        }
    }

}
