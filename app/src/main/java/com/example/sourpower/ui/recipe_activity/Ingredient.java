package com.example.sourpower.ui.recipe_activity;

public class Ingredient {
    private Integer mImage;
    private String mName;

    public Ingredient(String name, Integer image) {
        this.mName = name;
        this.mImage = image;
    }

    public String getName() {
        return mName;
    }

    public Integer getImage() {
        return mImage;
    }

}
