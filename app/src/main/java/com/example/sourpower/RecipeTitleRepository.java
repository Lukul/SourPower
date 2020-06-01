package com.example.sourpower;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

class RecipeTitleRepository {

    private RecipeTitleDao mRecipeTitleDao;
    private LiveData<List<RecipeTitle>> mAllRecipes;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    RecipeTitleRepository(Application application) {
        RecipeRoomDatabase db = RecipeRoomDatabase.getDatabase(application);
        mRecipeTitleDao = db.wordDao();
        mAllRecipes = mRecipeTitleDao.getAlphabetizedWords();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<RecipeTitle>> getAllWords() {
        return mAllRecipes;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(RecipeTitle recipeTitle) {
        RecipeRoomDatabase.databaseWriteExecutor.execute(() -> {
            mRecipeTitleDao.insert(recipeTitle);
        });
    }
}