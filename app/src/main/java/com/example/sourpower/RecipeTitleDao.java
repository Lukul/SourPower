package com.example.sourpower;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RecipeTitleDao {

    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(RecipeTitle recipeTitle);

    @Query("DELETE FROM recipe_title_table")
    void deleteAll();

    @Query("SELECT * from recipe_title_table ORDER BY recipe_title ASC")
    LiveData<List<RecipeTitle>> getAlphabetizedWords();
}
