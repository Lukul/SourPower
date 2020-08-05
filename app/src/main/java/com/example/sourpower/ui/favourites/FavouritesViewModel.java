package com.example.sourpower.ui.favourites;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sourpower.recipe.Recipe;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FavouritesViewModel extends AndroidViewModel {
    private List<String> mFavoriteRecipes = new ArrayList<>();
    private final String key = "favorite_recipes";

    public FavouritesViewModel(@NonNull Application application) {
        super(application);
    }

    public void addFavorite(Recipe recipe)
    {
        retrieveFavoriteRecipes();
        mFavoriteRecipes.add(recipe.getRecipeTitle());
        storeFavoriteRecipes();
    }

    public void deleteFavoriteRecipes(Recipe recipe){
        retrieveFavoriteRecipes();
        mFavoriteRecipes.remove(recipe.getRecipeTitle());
        storeFavoriteRecipes();
    }

    public List<String> getFavoriteRecipes()
    {
        retrieveFavoriteRecipes();
        return mFavoriteRecipes;
    }

    private void storeFavoriteRecipes()
    {
        //Set the values
        Gson gson = new Gson();
        String jsonText = gson.toJson(mFavoriteRecipes);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplication());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, jsonText);
        editor.apply();
    }

    private void retrieveFavoriteRecipes()
    {
        //Retrieve the values
        Gson gson = new Gson();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplication());
        if(preferences.contains(key))
        {
            String jsonText = preferences.getString(key, null);
            String[] text = gson.fromJson(jsonText, String[].class);  //EDIT: gso to gson
            mFavoriteRecipes = new ArrayList<>(Arrays.asList(text));
        }
    }
}