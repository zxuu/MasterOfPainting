package com.zxu.masterofpainting.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.zxu.masterofpainting.Adapter.NutritionalAdapter;
import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.bean.Ingredients;
import com.zxu.masterofpainting.bean.IngredientsInformation;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

import static android.content.ContentValues.TAG;

public class NutritionalComponentsFragment extends Fragment implements OnChartValueSelectedListener,View.OnClickListener {
    private PieChart mPieChart;
    private List<IngredientsInformation> ingredientsInformationList = new ArrayList<>();
    String resultNutrition;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nutritional_components, container, false);
        initCircleView(view);
        initTableView(view);
        return view;
    }

    private void initTableView(View view) {

        BmobQuery<Ingredients> ingredientsBmobQuery = new BmobQuery<>("Ingredients");
        ingredientsBmobQuery.addWhereEqualTo("IngredientsName","香菇");
        ingredientsBmobQuery.findObjects(new FindListener<Ingredients>() {
            @Override
            public void done(List<Ingredients> list, BmobException e) {
                if (e == null) {
                    for (Ingredients correctIngredients : list) {
                        resultNutrition = correctIngredients.getNutrition();
                        Toast.makeText(getContext(), resultNutrition, Toast.LENGTH_SHORT).show();
                        String[] splitString = resultNutrition.split(";");
                        for (int i = 0; i < splitString.length; i++) {
                            String childsplit1 = splitString[i].split(",")[0];
                            String childsplit2 = splitString[i].split(",")[1];
                            //Toast.makeText(getContext(), childsplit[0]+childsplit[1], Toast.LENGTH_SHORT).show();
                            ingredientsInformationList.add(new IngredientsInformation(childsplit1,childsplit2));
                        }
                        break;
                    }
                } else {
                    Toast.makeText(getContext(), e.getErrorCode(), Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "done: "+e.toString());
                }
            }
        });
        //Toast.makeText(getContext(), resultNutrition, Toast.LENGTH_SHORT).show();
//        if (resultNutrition != null) {
//            String[] splitString = resultNutrition.split(";");
//            for (int i = 0; i < splitString.length; i++) {
//                String[] childsplit = splitString[i].split(",");
//                Toast.makeText(getContext(), childsplit[0]+childsplit[1], Toast.LENGTH_SHORT).show();
//                ingredientsInformationList.add(new IngredientsInformation(childsplit[0],childsplit[1]));
//            }
//        } else {
//            Toast.makeText(getContext(), "re is null", Toast.LENGTH_SHORT).show();
//        }

//        for (int i = 0; i < 20; i++) {
//            ingredientsInformationList.add(new IngredientsInformation("热量(大卡)", "10"));
//        }
        NutritionalAdapter nutritionalAdapter = new NutritionalAdapter(getContext(), R.layout.nutritional_item, ingredientsInformationList);
        ListView listView = (ListView) view.findViewById(R.id.chengfen_list);

        int height = 0;
        int count = nutritionalAdapter.getCount();
        for (int i = 0; i < count; i++) {
            View temp = nutritionalAdapter.getView(i, null, listView);
            temp.measure(0,0);
            height += temp.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = height;
        listView.setLayoutParams(params);
        listView.setAdapter(nutritionalAdapter);
    }


    private void initCircleView(View view) {
        mPieChart = (PieChart) view.findViewById(R.id.mPieChart);
        mPieChart.setUsePercentValues(true);
        mPieChart.getDescription().setEnabled(false);
        mPieChart.setExtraOffsets(5, 10, 5, 5);

        mPieChart.setDragDecelerationFrictionCoef(0.95f);
        //设置中间文件
        mPieChart.setCenterText(generateCenterSpannableText());

        mPieChart.setDrawHoleEnabled(true);
        mPieChart.setHoleColor(Color.WHITE);

        mPieChart.setTransparentCircleColor(Color.WHITE);
        mPieChart.setTransparentCircleAlpha(110);

        mPieChart.setHoleRadius(58f);
        mPieChart.setTransparentCircleRadius(61f);

        mPieChart.setDrawCenterText(true);

        mPieChart.setRotationAngle(0);
        // 触摸旋转
        mPieChart.setRotationEnabled(true);
        mPieChart.setHighlightPerTapEnabled(true);

        //变化监听
        mPieChart.setOnChartValueSelectedListener(this);

        //模拟数据
        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
        entries.add(new PieEntry(40, "优秀"));
        entries.add(new PieEntry(20, "满分"));
        entries.add(new PieEntry(30, "及格"));
        entries.add(new PieEntry(10, "不及格"));

        //设置数据
        setData(entries);

        mPieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);

        Legend l = mPieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        // 输入标签样式
        mPieChart.setEntryLabelColor(Color.WHITE);
        mPieChart.setEntryLabelTextSize(12f);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }

    //设置中间文字
    private SpannableString generateCenterSpannableText() {
        //原文：MPAndroidChart\ndeveloped by Philipp Jahoda
        SpannableString s = new SpannableString("赵某人程序员\n今天有点冷");
        //s.setSpan(new RelativeSizeSpan(1.7f), 0, 14, 0);
        //s.setSpan(new StyleSpan(Typeface.NORMAL), 14, s.length() - 15, 0);
        // s.setSpan(new ForegroundColorSpan(Color.GRAY), 14, s.length() - 15, 0);
        //s.setSpan(new RelativeSizeSpan(.8f), 14, s.length() - 15, 0);
        // s.setSpan(new StyleSpan(Typeface.ITALIC), s.length() - 14, s.length(), 0);
        // s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length() - 14, s.length(), 0);
        return s;
    }

    //设置数据
    private void setData(ArrayList<PieEntry> entries) {
        PieDataSet dataSet = new PieDataSet(entries, "三年级二班");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        //数据和颜色
        ArrayList<Integer> colors = new ArrayList<Integer>();
        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);
        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        mPieChart.setData(data);
        mPieChart.highlightValues(null);
        //刷新
        mPieChart.invalidate();
    }
}
