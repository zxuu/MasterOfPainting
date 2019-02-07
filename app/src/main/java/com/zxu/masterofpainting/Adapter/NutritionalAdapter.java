package com.zxu.masterofpainting.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.bean.IngredientsInformation;

import java.util.List;

public class NutritionalAdapter extends ArrayAdapter<IngredientsInformation> {
    private int resourceId;

    public NutritionalAdapter(@NonNull Context context, int resource, @NonNull List<IngredientsInformation> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        IngredientsInformation ingredientsInformation = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        TextView compositionName = (TextView) view.findViewById(R.id.composition_name);
        TextView compotionJiliang = (TextView) view.findViewById(R.id.composition_jiliang);
        compositionName.setText(ingredientsInformation.getCompositioNname());
        compotionJiliang.setText(ingredientsInformation.getCompositionZongliang());
        return view;
    }
}
