package com.example.sourpower.ui.recipe_activity;

import java.net.URL;

public class Instruction {
    private String mImageURL;
    private String mInstructionText;
    private int mCookingTime;

    public Instruction(String text, String imageURL, int cookingTime) {
        this.mInstructionText = text;
        this.mImageURL = imageURL;
        this.mCookingTime = cookingTime;
    }

    public Instruction(String text, String imageURL) {
        this.mInstructionText = text;
        this.mImageURL = imageURL;
    }

    public String getText() {
        return mInstructionText;
    }

    public String getImageURL() {
        return mImageURL;
    }

    public int getCookingTime() {
        return mCookingTime;
    }
}
