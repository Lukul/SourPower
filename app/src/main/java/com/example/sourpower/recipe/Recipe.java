package com.example.sourpower.recipe;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "recipe_table")
public class Recipe {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "recipe_title")
    private String recipeTitle;

    @NonNull
    @ColumnInfo(name = "recipe_cover")
    private Integer recipeCover;

    public Recipe(@NonNull String recipeTitle,
                  @NonNull Integer recipeCover) {
        this.recipeTitle = recipeTitle;
        this.recipeCover = recipeCover;
    }

    public String getRecipeTitle(){
        return this.recipeTitle;
    }

    public Integer getRecipeCover() {
        return this.recipeCover;
    }

    @Override
    public boolean equals(Object obj) {
        Recipe e = (Recipe) obj;
        return this.recipeTitle == e.recipeTitle;
    }
}