package com.example.sourpower.ui.recipe_activity;

public class Ingredient {
    private Integer mImage;
    private String mName;
    private Integer mAmount;
    private String mUnit;

    public Ingredient(String name, Integer amount, String unit, Integer image) {
        this.mName = name;
        this.mAmount = amount;
        this.mImage = image;
        this.mUnit = unit;
    }

    public String getName() {
        return mName;
    }

    public Integer getImage() {
        return mImage;
    }

    public Integer getAmount() {return mAmount;}

    public String getUnit() {return mUnit;}
}
