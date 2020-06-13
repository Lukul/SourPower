package com.example.sourpower;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.LinkedList;

import jp.wasabeef.glide.transformations.BlurTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;
import static java.lang.Math.max;

public class RecipeActivity extends AppCompatActivity {
    private RecyclerView mIngredientsRecyclerView;
    private IngredientListAdapter mIngredientListAdapter;
    private final LinkedList<Ingredient> mIngredientList = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        setBackground(1);
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

        NestedScrollView recipeScrollView = (NestedScrollView)findViewById(R.id.activity_recipe_nested_scroll_view);
        recipeScrollView.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                int scrollY = view.getScrollY();
                int bottomY = view.getBottom();
                int radius = 50 * scrollY / bottomY;
                radius = max(1, radius);
                setBackground(radius);
                return false;
            }
        });
    }

    public void setBackground(int blurRadius)
    {
        Glide.with(this)
                .load(R.drawable.fruit_salad)
                .apply(bitmapTransform(new BlurTransformation(blurRadius,1)))
                .into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                        findViewById(R.id.activity_recipe_nested_scroll_view).setBackground(resource);
                    }
                    @Override
                    public void onLoadCleared(Drawable placeholder) {
                        findViewById(R.id.activity_recipe_nested_scroll_view).setBackground(placeholder);
                    }
                });
    }
}
