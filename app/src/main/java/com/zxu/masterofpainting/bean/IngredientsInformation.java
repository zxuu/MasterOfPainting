package com.zxu.masterofpainting.bean;

import java.util.PriorityQueue;

public class IngredientsInformation {
    private String compositionName; //成分名字
    private String compositionZongliang; //成分总量

    public IngredientsInformation(String compositioNname, String compositionZongliang) {
        this.compositionName = compositioNname;
        this.compositionZongliang = compositionZongliang;
    }

    public String getCompositioNname() {
        return compositionName;
    }

    public void setCompositioNname(String compositioNname) {
        this.compositionName = compositioNname;
    }

    public String getCompositionZongliang() {
        return compositionZongliang;
    }

    public void setCompositionZongliang(String compositionZongliang) {
        this.compositionZongliang = compositionZongliang;
    }
}
