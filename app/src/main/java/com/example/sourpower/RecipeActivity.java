package com.example.sourpower;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.LinkedList;

public class RecipeActivity extends AppCompatActivity {
    private RecyclerView mIngredientsRecyclerView;
    private IngredientListAdapter mIngredientListAdapter;
    private final LinkedList<Integer> mIngredientList = new LinkedList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        mIngredientList.add(R.drawable.apple);
        mIngredientList.add(R.drawable.cherry);
        mIngredientList.add(R.drawable.orange);
        mIngredientList.add(R.drawable.watermelon);
        mIngredientList.add(R.drawable.strawberry);
        mIngredientList.add(R.drawable.lemon);
        mIngredientList.add(R.drawable.kiwi);
        mIngredientsRecyclerView = findViewById(R.id.ingredients_recyclerview);
        mIngredientListAdapter = new IngredientListAdapter(this, mIngredientList);
        mIngredientsRecyclerView.setAdapter(mIngredientListAdapter);
        mIngredientsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
