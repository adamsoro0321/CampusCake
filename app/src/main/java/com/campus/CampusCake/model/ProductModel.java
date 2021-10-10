package com.campus.CampusCake.model;

import com.google.firebase.firestore.FieldValue;

import java.util.Date;

public class ProductModel {
    String idProduct,nameproduct ,imgProduct ,vendeurUId ;
    Date date ;

    public ProductModel() {
    }

    public ProductModel(String idProduct, String nameproduct, String imgProduct, String vendeurUId) {
        this.idProduct = idProduct;
        this.nameproduct = nameproduct;
        this.imgProduct = imgProduct;
        this.vendeurUId = vendeurUId;
    }

    public ProductModel(String nameproduct, String imgProduct, String vendeurUId) {
        this.nameproduct = nameproduct;
        this.imgProduct = imgProduct;
        this.vendeurUId = vendeurUId;
    }

    public String getNameproduct() {
        return nameproduct;
    }

    public void setNameproduct(String nameproduct) {
        this.nameproduct = nameproduct;
    }

    public String getImgProduct() {
        return imgProduct;
    }

    public void setImgProduct(String imgProduct) {
        this.imgProduct = imgProduct;
    }

    public FieldValue getDate() {
        return FieldValue.serverTimestamp() ;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getVendeurUId() {
        return vendeurUId;
    }

    public void setVendeurUId(String vendeurUId) {
        this.vendeurUId = vendeurUId;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }
}
