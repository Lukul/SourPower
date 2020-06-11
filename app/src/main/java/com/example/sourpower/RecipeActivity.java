package com.example.sourpower;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.LinkedList;

public class RecipeActivity extends AppCompatActivity {
    private RecyclerView mIngredientsRecyclerView;
    private IngredientListAdapter mIngredientListAdapter;
    private final LinkedList<Ingredient> mIngredientList = new LinkedList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        mIngredientList.add(new Ingredient("Apple", R.drawable.apple));
        mIngredientList.add(new Ingredient("Kiwi", R.drawable.kiwi));
        mIngredientList.add(new Ingredient("Cherry", R.drawable.cherry));
        mIngredientList.add(new Ingredient("Orange", R.drawable.orange));
        mIngredientList.add(new Ingredient("Watermelon", R.drawable.watermelon));
        mIngredientList.add(new Ingredient("Strawberry", R.drawable.strawberry));
        mIngredientList.add(new Ingredient("Lemon", R.drawable.lemon));
        mIngredientsRecyclerView = findViewById(R.id.ingredients_recyclerview);
        mIngredientListAdapter = new IngredientListAdapter(this, mIngredientList);
        mIngredientsRecyclerView.setAdapter(mIngredientListAdapter);
        mIngredientsRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
    }
}
