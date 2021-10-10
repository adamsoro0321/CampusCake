package com.campus.CampusCake.model;

public class productUidEtStatu {
    String pId ,pStatu ;

    public productUidEtStatu() {
    }

    public productUidEtStatu(String pId, String pStatu) {
        this.pId = pId;
        this.pStatu = pStatu;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getpStatu() {
        return pStatu;
    }

    public void setpStatu(String pStatu) {
        this.pStatu = pStatu;
    }
}
