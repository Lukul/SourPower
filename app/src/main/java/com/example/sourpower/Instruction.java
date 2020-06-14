package com.example.sourpower;

public class Instruction {
    private Integer mImage;
    private String mInstructions;

    public Instruction(String instructions, Integer image) {
        this.mInstructions = instructions;
        this.mImage = image;
    }

    public String getInstructions() {
        return mInstructions;
    }

    public Integer getImage() {
        return mImage;
    }

}
