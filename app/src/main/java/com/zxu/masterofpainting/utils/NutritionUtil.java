package com.zxu.masterofpainting.utils;

import com.zxu.masterofpainting.bean.IngredientsInformation;

import java.util.ArrayList;
import java.util.List;

public class NutritionUtil {
    public static List<IngredientsInformation> nutritionStringUtil(String nutritionString) {
        List<IngredientsInformation> ingredientsInformationList = new ArrayList<>();
        String[] str = nutritionString.split(";");
        
        return ingredientsInformationList;
    }
}
