package com.zxu.masterofpainting.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.paulyung.laybellayout.LaybelLayout;
import com.zxu.masterofpainting.Constants;
import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.activity.LabelDetailActivity;

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
    public View getView(final int position, View convertView, ViewGroup parent) {
         //int labelContentPosition = position;
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
                    Intent intent = new Intent(context,LabelDetailActivity.class);
                    intent.putExtra("labelName", Constants.labelMenuContent[position][p]);
                    context.startActivity(intent);
                    //Toast.makeText(context, "你点击了"+Constants.labelMenuContent[labelContentPosition][p], Toast.LENGTH_SHORT).show();
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
