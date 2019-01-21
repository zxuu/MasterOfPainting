package com.zxu.masterofpainting.bean;

import cn.bmob.v3.BmobObject;

public class Ingredients extends BmobObject {
    private String IngredientsName;
    private String Nutrition;
    private String Efficiency;
    private String SuitableCollocation;

    public String getIngredientsName() {
        return IngredientsName;
    }

    public void setIngredientsName(String ingredientsName) {
        IngredientsName = ingredientsName;
    }

    public String getNutrition() {
        return Nutrition;
    }

    public void setNutrition(String nutrition) {
        Nutrition = nutrition;
    }

    public String getEfficiency() {
        return Efficiency;
    }

    public void setEfficiency(String efficiency) {
        Efficiency = efficiency;
    }

    public String getSuitableCollocation() {
        return SuitableCollocation;
    }

    public void setSuitableCollocation(String suitableCollocation) {
        SuitableCollocation = suitableCollocation;
    }


}
