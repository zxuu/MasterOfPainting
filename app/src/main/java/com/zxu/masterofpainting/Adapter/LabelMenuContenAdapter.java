package com.zxu.masterofpainting.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.paulyung.laybellayout.LaybelLayout;
import com.zxu.masterofpainting.Constants;
import com.zxu.masterofpainting.R;

import java.util.List;

public class LabelMenuContenAdapter extends BaseAdapter {
    private Context context;
    private List<String> foodDatas;

    public LabelMenuContenAdapter(Context context, List<String> foodDatas) {
        this.context = context;
        this.foodDatas = foodDatas;
    }

    @Override
    public int getCount() {
        if (foodDatas != null) {
            return foodDatas.size();
        } else {
            return 10;
        }
    }

    @Override
    public Object getItem(int position) {
        return foodDatas.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int labelContentPosition = position;
        String dataBean = foodDatas.get(position);
        ViewHold viewHold = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_label_content, null);
            viewHold = new ViewHold();
            viewHold.gridView = (LaybelLayout) convertView.findViewById(R.id.gridView);
            viewHold.blank = (TextView) convertView.findViewById(R.id.blank);
            viewHold.gridView.setOnItemClickListener(new LaybelLayout.OnItemClickListener() {
                @Override
                public void onItemClick(int p) {
                    Toast.makeText(context, "你点击了"+Constants.labelMenuContent[labelContentPosition][p], Toast.LENGTH_SHORT).show();
                }
            });
            convertView.setTag(viewHold);
        } else {
            viewHold = (ViewHold) convertView.getTag();
        }
        viewHold.blank.setText(dataBean);
        viewHold.gridView.setAdapter(new LaybelLayout.Adapter(Constants.labelMenuContent[position]));
        return convertView;
    }

    private static class ViewHold {
        private LaybelLayout gridView;
        private TextView blank;
    }
}
