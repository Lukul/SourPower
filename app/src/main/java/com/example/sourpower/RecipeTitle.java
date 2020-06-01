package com.example.sourpower;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "recipe_title_table")
public class RecipeTitle {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "recipe_title")
    private String recipeTitle;

    public RecipeTitle(@NonNull String recipeTitle) {
        this.recipeTitle = recipeTitle;
    }

    public String getRecipeTitle() {
        return this.recipeTitle;
    }
}