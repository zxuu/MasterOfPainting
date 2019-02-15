package com.zxu.masterofpainting.utils;

import com.zxu.masterofpainting.bean.NutritionItem;

import java.util.ArrayList;
import java.util.List;

public class NutritionUtil {
    public static List<NutritionItem> nutritionStringUtil(String nutritionString) {
        List<NutritionItem> ingredientsInformationList = new ArrayList<>();
        String[] str = nutritionString.split(";");
        
        return ingredientsInformationList;
    }
}
