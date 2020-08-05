package com.example.sourpower.ui.weekly;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.sourpower.R;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;


import com.example.sourpower.recipe.Recipe;
import com.example.sourpower.recipe.RecipeViewModel;


import java.util.ArrayList;
import java.util.List;

public class WeeklySpecialsFragment extends Fragment {

    private RecipeViewModel mRecipeViewModel;
    private WeeklySpecialsViewModel mWeeklySpecialsViewModel;

    private ArrayList<String> mWeeklySpecials;
    private ArrayAdapter<Recipe> mArrayAdapter;
    private int i;
    private WeeklySpecialsAdapter mWeeklySpecialsAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_weekly_specials, container, false);

        mRecipeViewModel = new ViewModelProvider(this).get(RecipeViewModel.class);
        mWeeklySpecialsViewModel = new ViewModelProvider(this).get(WeeklySpecialsViewModel.class);
        mWeeklySpecialsAdapter = new WeeklySpecialsAdapter(getActivity());

        SwipeFlingAdapterView flingContainer = (SwipeFlingAdapterView) root.findViewById(R.id.frame);
        flingContainer.setAdapter(mWeeklySpecialsAdapter);

        mWeeklySpecialsViewModel.addWeeklySpecials("Mein Brot");
        mWeeklySpecialsViewModel.addWeeklySpecials("Basic Country Bread");
        mRecipeViewModel.setSelection(mWeeklySpecialsViewModel.getWeeklySpecialsRecipes());
        mRecipeViewModel.getSearchBy().observe(getViewLifecycleOwner(), new Observer<List<Recipe>>() {
            @Override
            public void onChanged(@Nullable final List<Recipe> recipes) {
                // Update the cached copy of the words in the adapter.
                mWeeklySpecialsAdapter.setRecipes(recipes);
            }
        });
        getActivity().setTitle(R.string.weekly_title);

        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                Log.d("LIST", "removed object!");
                mWeeklySpecials.remove(0);
                mArrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
                Toast.makeText(getActivity(), "Left!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                Toast.makeText(getActivity(), "Rigth!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                // Ask for more data here
                //al.add("XML ".concat(String.valueOf(i)));
                mWeeklySpecialsAdapter.notifyDataSetChanged();
                Log.d("LIST", "notified");
                i++;
            }

            @Override
            public void onScroll(float scrollProgressPercent) {
            }
        });


        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                Toast.makeText(getActivity(), "Clicked!", Toast.LENGTH_SHORT).show();
            }
        });
        return root;
    }
}
