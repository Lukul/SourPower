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

    @NonNull
    @ColumnInfo(name = "recipe_cover")
    private Integer recipeCover;

    public RecipeTitle(@NonNull String recipeTitle,
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
}