package com.example.sourpower.ui.weekly;

import android.app.Application;
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

public class WeeklySpecialsViewModel extends AndroidViewModel {
    private List<String> mWeeklySpecialsRecipes = new ArrayList<>();
    private final String key = "weekly_specials_recipes";

    public WeeklySpecialsViewModel(@NonNull Application application) {
        super(application);
    }

    public void addWeeklySpecials(String string)
    {
        //retrieveWeeklySpecialsRecipes();
        //mWeeklySpecialsRecipes.add(recipe.getRecipeTitle());
        //storeWeeklySpecialsRecipes();
        mWeeklySpecialsRecipes.add(string);
    }

    public List<String> getWeeklySpecialsRecipes()
    {
        retrieveWeeklySpecialsRecipes();
        return mWeeklySpecialsRecipes;
    }

    private void storeWeeklySpecialsRecipes()
    {
        //Set the values
        Gson gson = new Gson();
        String jsonText = gson.toJson(mWeeklySpecialsRecipes);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplication());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, jsonText);
        editor.apply();
    }

    private void retrieveWeeklySpecialsRecipes()
    {
        //Retrieve the values
        Gson gson = new Gson();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplication());
        if(preferences.contains(key))
        {
            String jsonText = preferences.getString(key, null);
            String[] text = gson.fromJson(jsonText, String[].class);  //EDIT: gso to gson
            mWeeklySpecialsRecipes = new ArrayList<>(Arrays.asList(text));
        }
    }
}