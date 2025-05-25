package com.god.haircutmanager.Model;

import com.orm.SugarRecord;

public class HairStyle extends SugarRecord {
    private String name;
    private String imagePath;
    private double price;
    private String description;

    public HairStyle() {
    }

    public HairStyle(String name, String imagePath, double price, String description) {
        this.name = name;
        this.imagePath = imagePath;
        this.price = price;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
