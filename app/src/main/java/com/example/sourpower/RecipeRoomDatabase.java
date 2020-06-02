package com.example.sourpower;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {RecipeTitle.class}, version = 2, exportSchema = false)
public abstract class RecipeRoomDatabase extends RoomDatabase {

    public abstract RecipeTitleDao wordDao();

    private static volatile RecipeRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static RecipeRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (RecipeRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RecipeRoomDatabase.class, "recipe_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                RecipeTitleDao dao = INSTANCE.wordDao();
                dao.deleteAll();

                RecipeTitle recipeTitle =  new RecipeTitle("Mein Brot", R.drawable.me_bread_wide);
                dao.insert(recipeTitle);
                recipeTitle = new RecipeTitle("Basic Country Bread", R.drawable.basic_country_bread_wide);
                dao.insert(recipeTitle);
                recipeTitle = new RecipeTitle("Bread", R.drawable.bread);
                dao.insert(recipeTitle);
                recipeTitle = new RecipeTitle("Cinnamon rolls", R.drawable.cinammon_rolls);
                dao.insert(recipeTitle);
                recipeTitle = new RecipeTitle("Granola", R.drawable.granola);
                dao.insert(recipeTitle);
            });
        }
    };
}