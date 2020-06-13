package com.example.sourpower;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.LinkedList;

import jp.wasabeef.glide.transformations.BlurTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.toIntExact;

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

        NestedScrollView recipeScrollView = (NestedScrollView)findViewById(R.id.nestedScrollView_recipe);
        recipeScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                int bottomY = v.getBottom();
                // Screen will not immediately blur, but only once a third down the screen
                int scrollYDelay = scrollY - (int)(bottomY * 0.3);
                int radius = 100 * scrollYDelay / bottomY;
                radius = max(1, min(radius, 25)); // 1 < Radius < 25
                setBackground(radius);
            }
        });

        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        int screenHeight = size.y;
        LinearLayout layout = (LinearLayout)findViewById(R.id.linearLayoutRecipe_fullScreen);
        ViewGroup.LayoutParams params = layout.getLayoutParams();
        params.height = screenHeight - getStatusBarHeight() - getActionBarHeight();
    }

    public void setBackground(int blurRadius)
    {
        Glide.with(this)
                .load(R.drawable.fruit_salad)
                .apply(bitmapTransform(new BlurTransformation(blurRadius,1)))
                .into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                        findViewById(R.id.nestedScrollView_recipe).setBackground(resource);
                    }
                    @Override
                    public void onLoadCleared(Drawable placeholder) {
                        findViewById(R.id.nestedScrollView_recipe).setBackground(placeholder);
                    }
                });
    }

    public int getStatusBarHeight() {
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return getResources().getDimensionPixelSize(resourceId);
        }
        return 0;
    }

    public int getActionBarHeight() {
        TypedValue tv = new TypedValue();
        if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true))
        {
            return TypedValue.complexToDimensionPixelSize(tv.data,getResources().getDisplayMetrics());
        }
        return 0;
    }
}
