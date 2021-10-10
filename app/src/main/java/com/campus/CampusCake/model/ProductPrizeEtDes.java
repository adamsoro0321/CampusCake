package com.campus.CampusCake.model;

import java.io.Serializable;

public class ProductPrizeEtDes implements Serializable {
    String name,prize ,description ,img;

    public ProductPrizeEtDes() {
    }

    public ProductPrizeEtDes(String name, String prize, String description) {
        this.name = name;
        this.prize = prize;
        this.description = description;
    }

    public ProductPrizeEtDes(String name, String prize, String description, String img) {
        this.name = name;
        this.prize = prize;
        this.description = description;
        this.img = img;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
