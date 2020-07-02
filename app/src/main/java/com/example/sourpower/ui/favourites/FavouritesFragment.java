package com.example.sourpower.ui.favourites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sourpower.R;
import com.example.sourpower.recipe.RecipeListAdapter;
import com.example.sourpower.recipe.Recipe;
import com.example.sourpower.recipe.RecipeViewModel;

import java.util.List;

public class FavouritesFragment extends Fragment {
    private RecipeViewModel mRecipeViewModel;
    private RecyclerView mRecyclerView;
    private RecipeListAdapter mAdapter;
    private FavouritesViewModel mFavouritesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_favourites, container, false);

        // Get a handle to the RecyclerView.
        mRecyclerView = root.findViewById(R.id.recyclerview_favourites);
        // Create an adapter and supply the data to be displayed.
        mAdapter = new RecipeListAdapter(getContext());
        // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        // Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecipeViewModel = new ViewModelProvider(this).get(RecipeViewModel.class);
        mFavouritesViewModel = new ViewModelProvider(this).get(FavouritesViewModel.class);
        mRecipeViewModel.setFavoriteSelection(mFavouritesViewModel.getFavoriteRecipes());
        mRecipeViewModel.getSearchBy().observe(getViewLifecycleOwner(), new Observer<List<Recipe>>() {
            @Override
            public void onChanged(@Nullable final List<Recipe> recipes) {
                // Update the cached copy of the words in the adapter.
                mAdapter.setRecipes(recipes);
            }
        });

        getActivity().setTitle(R.string.favorites_title);
        return root;
    }
}
