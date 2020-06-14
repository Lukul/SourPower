package com.example.sourpower;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.TypedValue;
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

public class RecipeActivity extends AppCompatActivity {
    private RecyclerView mIngredientsRecyclerView;
    private RecyclerView mInstructionsRecyclerView;
    private IngredientListAdapter mIngredientListAdapter;
    private InstructionListAdapter mInstructionListAdapter;
    private final LinkedList<Ingredient> mIngredientList = new LinkedList<>();
    private final LinkedList<Instruction> mInstructionList = new LinkedList<>();

    private RecyclerView mInstructionsRecyclerView;
    private InstructionListAdapter mInstructionsListAdapter;
    private final LinkedList<Instruction> mInstructionList = new LinkedList<>();

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

        mInstructionList.add(new Instruction("Cut the Apple in pieces.", "https://lh3.googleusercontent.com/ZdV_LHWBOnDZ9jYUvS3Q0aCVSQo7u6AccnEB_CDIDSFbQXwInGkIR3YGeEnLoM82X1esSr8cQuSt0VowTkJFOfrpOGTRpsqcKHsiNOMD_Iv3UV0VygLDY-Irrv-qvexRWHdwvprRFV-4M9hbJ-1aBCWVovUb25zR25jeT4S-hhCbWau6NLuWK0HwoVPniJ87quWfQDdul6Rg3f7rCX4xO_bJ9uY2ba0XLpIu31if5VrxYzjLBBE5XMZBz_RSM16jhIDPZCts9OuwDn3Z48hh4f0jkU_wEZpo9xT0DFKtafLTFCoxIYda75_tTuZW7dkuRcaUg5dl379PkVu810ULE-DFSjYtIk2sqiquHZ0hlhCLOTcV78x7vdm8h4Dk0FWxQVK6ot7ObD-MMMILZOVcLVxvwBhcXT6d35lDnEzzBpOP91XlzyD4GIQ4p10b8gABt7MpxPV2faidTfJuFtQOE9ePt72tP1qFO2Ki8M_BbrAE-JhhhiF-fjNnsnPb7iUZ-GuCrje6j-yMmTQWiQoZczsgG_AGtgUkZ_kaU9W-Tu1krZce8abJy_zhB3WY0ZFHctklhdFzRemzgQMvBhwOdk8JVOPPCpRdUe4cUZlDZWvAJykl1F7nZyXkCbsJTEHjZNUWkPlt1Y1gtEizgdOSH5hfQSH12I0vDPqUN2BeOHDyb7FgsKj0fQaWC_eqOg=w1599-h900-no?authuser=0"));
        mInstructionList.add(new Instruction("Cut the Banana in pieces.", "https://drive.google.com/file/d/1aGDotsdCQ30ZcmiyBvo_d14zdsUDwlMa/view?usp=sharing"));
        mInstructionList.add(new Instruction("Cut the Banana in pieces.", "https://i.imgur.com/y6b7eZX.jpg"));


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
