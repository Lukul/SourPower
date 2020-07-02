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
import java.util.List;

public class FavouritesViewModel extends AndroidViewModel {
    private final List<String> mFavoriteRecipes = new ArrayList<>();

    public FavouritesViewModel(@NonNull Application application) {
        super(application);
    }

    public void addFavorite(Recipe recipe) { mFavoriteRecipes.add(recipe.getRecipeTitle()); }

    public List<String> getFavoriteRecipes()
    {
        mFavoriteRecipes.add("Mein Brot");
        return mFavoriteRecipes;
    }

    public void addRecipe(String title)
    {
        //Set the values
        Gson gson = new Gson();
        String jsonText = gson.toJson(mFavoriteRecipes);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplication());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("key", jsonText);
        editor.apply();
    }

    public void removeRecipe(String title)
    {
        //Retrieve the values
        Gson gson = new Gson();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplication());
        String jsonText = preferences.getString("key", null);
        String[] text = gson.fromJson(jsonText, String[].class);  //EDIT: gso to gson
    }
}