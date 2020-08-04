package com.example.sourpower.recipe;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import java.util.ArrayList;
import java.util.List;

public class RecipeViewModel extends AndroidViewModel {
    private RecipeTitleRepository mRepository;
    private LiveData<List<Recipe>> mAllRecipes;
    private LiveData<List<Recipe>> searchByLiveData;
    private MutableLiveData<List<String>> filterLiveData = new MutableLiveData<>();
    private List<String> mSelection = new ArrayList<>();

    public RecipeViewModel(Application application) {
        super(application);
        mRepository = new RecipeTitleRepository(application);
        mAllRecipes = mRepository.getAllRecipes();
        searchByLiveData = Transformations.switchMap(filterLiveData,
                selection -> mRepository.searchBy(mSelection));
    }

    public LiveData<List<Recipe>> getSearchBy() { return searchByLiveData; }
    public void setSelection(List<String> selection)
    {
        mSelection = selection;
        filterLiveData.setValue(mSelection);
    }

    public LiveData<List<Recipe>> getAllRecipes() {
        return mAllRecipes;
    }
    public void insert(Recipe recipe) { mRepository.insert(recipe); }
}
