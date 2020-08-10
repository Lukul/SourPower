package com.example.sourpower.ui.recipe_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.sourpower.ColorSchemeUtility;
import com.example.sourpower.R;

import java.util.LinkedList;

import jp.wasabeef.glide.transformations.BlurTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;
import static java.lang.Math.max;
import static java.lang.Math.min;

public class RecipeActivity extends AppCompatActivity {

    private RecyclerView mIngredientsRecyclerView;
    private RecyclerView mInstructionsRecyclerView;
    private IngredientListAdapter mIngredientListAdapter;
    private InstructionListAdapter mInstructionListAdapter;
    private final LinkedList<Ingredient> mIngredientList = new LinkedList<>();
    private final LinkedList<Instruction> mInstructionList = new LinkedList<>();

    private final double blurDelayScreenLengthNormalized = 0.3;
    private final int blurRadiusSpeed = 30;
    private final int maxBlurRadius = 15;
    private final int maxAddedBrightness = 100;

    private int mServings = 2;
    private TextView mServingsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(ColorSchemeUtility.currentTheme(getApplicationContext()));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        setBackground(1, 0);
        mIngredientList.add(new Ingredient("Apple", 2, "", R.drawable.apple));
        mIngredientList.add(new Ingredient("Kiwi", 1, "",  R.drawable.kiwi));
        mIngredientList.add(new Ingredient("Cherry", 500, "g", R.drawable.cherry));
        mIngredientList.add(new Ingredient("Orange", 3, "", R.drawable.orange));
        mIngredientList.add(new Ingredient("Watermelon", 2, "kg", R.drawable.watermelon));
        mIngredientList.add(new Ingredient("Strawberry", 500, "g", R.drawable.strawberry));
        mIngredientList.add(new Ingredient("Lemon", 2, "", R.drawable.lemon));

        mServingsText = (TextView)findViewById(R.id.textView_amount_servings);



        mIngredientsRecyclerView = findViewById(R.id.ingredients_recyclerview);
        mIngredientListAdapter = new IngredientListAdapter(this, mIngredientList);
        mIngredientsRecyclerView.setAdapter(mIngredientListAdapter);
        mIngredientsRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        //https://help.dropbox.com/files-folders/share/force-download
        mInstructionList.add(new Instruction("Cut the Apple in pieces.", "https://www.dropbox.com/s/yazlkl75im1pwdy/DSCN4343.JPG?dl=1"));
        mInstructionList.add(new Instruction("Cut the Banana in pieces.", "https://www.dropbox.com/s/mfqz318mm6rn5jd/DSCN4346.JPG?dl=1"));
        mInstructionList.add(new Instruction("Cut the Banana in pieces.", "https://www.dropbox.com/s/dmzdj9bumu79v6t/DSCN4355.JPG?dl=1"));


        mInstructionsRecyclerView = findViewById(R.id.instructions_recyclerview);
        mInstructionListAdapter = new InstructionListAdapter(this, mInstructionList);
        mInstructionsRecyclerView.setAdapter(mInstructionListAdapter);
        mInstructionsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        NestedScrollView recipeScrollView = (NestedScrollView)findViewById(R.id.nestedScrollView_recipe);
        recipeScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                int bottomY = v.getBottom();
                // Screen will not immediately blur, but only once a third down the screen
                int scrollYWithDelay = scrollY - (int)(bottomY * blurDelayScreenLengthNormalized);
                int radius = blurRadiusSpeed * scrollYWithDelay / bottomY;
                float whiteningIntensity = min((float)scrollYWithDelay / bottomY, 1);
                radius = max(1, min(radius, maxBlurRadius));
                setBackground(radius, whiteningIntensity);
            }
        });

        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        int screenHeight = size.y;
        LinearLayout layout = (LinearLayout)findViewById(R.id.linearLayoutRecipe_fullScreen);
        ViewGroup.LayoutParams params = layout.getLayoutParams();
        params.height = screenHeight - getStatusBarHeight() - getActionBarHeight();
    }

    public void setBackground(int blurRadius, float whiteningIntensity)
    {
        Glide.with(this)
                .load(R.drawable.fruit_salad)
                .apply(bitmapTransform(new BlurTransformation(blurRadius,1)))
                .into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                        resource.setColorFilter(getWhiteningFilter(whiteningIntensity));
                        findViewById(R.id.nestedScrollView_recipe).setBackground(resource);
                    }
                    @Override
                    public void onLoadCleared(Drawable placeholder) {
                        findViewById(R.id.nestedScrollView_recipe).setBackground(placeholder);
                    }
                });
    }

    private int getStatusBarHeight() {
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return getResources().getDimensionPixelSize(resourceId);
        }
        return 0;
    }

    private int getActionBarHeight() {
        TypedValue tv = new TypedValue();
        if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true))
        {
            return TypedValue.complexToDimensionPixelSize(tv.data,getResources().getDisplayMetrics());
        }
        return 0;
    }


    private ColorMatrixColorFilter getWhiteningFilter(float intensity)
    {
        float brightness = maxAddedBrightness * intensity;
        float brightnessMatrix[] =
                {   1 - (float)0.5 * intensity, 0, 0, 0, brightness,
                        0, 1 - (float)0.5 * intensity, 0, 0, brightness,
                        0, 0, 1- (float)0.5 * intensity, 0, brightness,
                        0, 0, 0, 1 - (float)0.5 * intensity, 0 };
        ColorMatrix colorMatrixBrightness = new ColorMatrix(brightnessMatrix);
        return new ColorMatrixColorFilter(colorMatrixBrightness);
    }

    public void recreateActivity() {
        Intent intent = getIntent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

    public void FlourThemeChange(View view) {
        ColorSchemeUtility.setTheme(getApplicationContext(), 1);
        recreateActivity();
    }

    public void FlowerThemeChange(View view) {
        ColorSchemeUtility.setTheme(getApplicationContext(), 2);
        recreateActivity();
    }

    public void decreaseServings(View view) {
        // Get the ID of the button that was clicked
        int viewID = view.getId();
        switch (viewID) {
            case R.id.button_minus_servings:
                //Decrement the score and update the TextView
                mServings--;
                if (mServings < 0) { mServings = 0;}
                mServingsText.setText(String.valueOf(mServings));
                break;
        }
    }

    public void increaseServings(View view) {
        // Get the ID of the button that was clicked
        int viewID = view.getId();
        switch (viewID) {
            case R.id.button_plus_servings:
                //Decrement the score and update the TextView
                mServings++;
                if (mServings < 0) { mServings = 0;}
                mServingsText.setText(String.valueOf(mServings));
                break;
        }
    }
}
