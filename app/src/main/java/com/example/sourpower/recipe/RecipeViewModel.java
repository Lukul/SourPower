package com.example.sourpower.recipe;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class RecipeViewModel extends AndroidViewModel {

    private RecipeTitleRepository mRepository;

    private LiveData<List<RecipeTitle>> mAllRecipes;

    public RecipeViewModel(Application application) {
        super(application);
        mRepository = new RecipeTitleRepository(application);
        mAllRecipes = mRepository.getAllWords();
    }

    public LiveData<List<RecipeTitle>> getAllRecipes() {
        return mAllRecipes;
    }

    public void insert(RecipeTitle recipeTitle) {
        mRepository.insert(recipeTitle);
    }
}
