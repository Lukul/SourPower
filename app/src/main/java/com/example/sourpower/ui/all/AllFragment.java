package com.example.sourpower.ui.all;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sourpower.R;
import com.example.sourpower.recipe.Recipe;
import com.example.sourpower.recipe.RecipeListAdapter;
import com.example.sourpower.recipe.RecipeViewModel;
import com.example.sourpower.ui.favourites.FavouritesViewModel;

import java.util.List;

import static java.lang.Math.min;

public class AllFragment extends Fragment {
    private RecipeViewModel mRecipeViewModel;
    private RecyclerView mRecyclerView;
    private RecipeListAdapter mAdapter;
    private FavouritesViewModel mFavouritesViewModel;
    //set up adapter and pass clicked listener this

    boolean swiped = false;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_all, container, false);

        // Get a handle to the RecyclerView.
        mRecyclerView = root.findViewById(R.id.recyclerview_all);
        // Create an adapter and supply the data to be displayed.
        mAdapter = new RecipeListAdapter(getContext());
        // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        // Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mRecipeViewModel = new ViewModelProvider(this).get(RecipeViewModel.class);
        mFavouritesViewModel = new ViewModelProvider(this).get(FavouritesViewModel.class);

        mRecipeViewModel.getAllRecipes().observe(getViewLifecycleOwner(), new Observer<List<Recipe>>() {
            @Override
            public void onChanged(@Nullable final List<Recipe> recipes) {
                // Update the cached copy of the words in the adapter.
                mAdapter.setRecipes(recipes);
            }
        });

        getActivity().setTitle(R.string.all_title);

        ItemTouchHelper mSwipeToLike = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                          RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView,
                                            @NonNull RecyclerView.ViewHolder viewHolder, float dX,
                                            float dY, int actionState, boolean isCurrentlyActive) {
                        DisplayMetrics displaymetrics = new DisplayMetrics();
                        requireActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
                        float maxMovementWidth = displaymetrics.widthPixels / 4f;
                        AccelerateDecelerateInterpolator interpolator = new AccelerateDecelerateInterpolator();
                        float relX = dX / maxMovementWidth;
                        dX = interpolator.getInterpolation(min(relX, 1)) * maxMovementWidth;

                        RecipeListAdapter.RecipeViewHolder recipeViewHolder = (RecipeListAdapter.RecipeViewHolder) viewHolder;

                        if (relX >= 0.85f && !swiped)
                        {
                            swiped = true;
                            animation(viewHolder);

                        }
                        if (dX == 0)
                        {
                            swiped = false;
                        }

                        final View card = ((RecipeListAdapter.RecipeViewHolder) viewHolder).getCard();
                        ViewCompat.setElevation(viewHolder.itemView, 8);
                        getDefaultUIUtil().onDraw(c, recyclerView, card, dX, dY, actionState, isCurrentlyActive);
                    }

                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                    }

                    @Override
                    public float getSwipeThreshold(@NonNull RecyclerView.ViewHolder viewHolder) {
                        return 1f;
                    }

                    @Override
                    public float getSwipeEscapeVelocity(float defaultValue) {
                        return Float.MAX_VALUE;
                    }

                    @Override
                    public long getAnimationDuration(@NonNull RecyclerView recyclerView, int animationType, float animateDx, float animateDy) {
                        return 2 * super.getAnimationDuration(recyclerView, animationType, animateDx, animateDy);
                    }
                });
        mSwipeToLike.attachToRecyclerView(mRecyclerView);
        return root;
    }
    public static void ImageViewAnimatedChange(Context c, final ImageView v, final int new_image) {
        final Animation anim_out = AnimationUtils.loadAnimation(c, android.R.anim.fade_out);
        final Animation anim_in  = AnimationUtils.loadAnimation(c, android.R.anim.fade_in);
        anim_out.setAnimationListener(new Animation.AnimationListener()
        {
            @Override public void onAnimationStart(Animation animation)
            {
                v.setImageResource(new_image);
                anim_in.setAnimationListener(new Animation.AnimationListener() {
                    @Override public void onAnimationStart(Animation animation) {}
                    @Override public void onAnimationRepeat(Animation animation) {}
                    @Override public void onAnimationEnd(Animation animation) {}
                });
                v.startAnimation(anim_in);
            }
            @Override public void onAnimationRepeat(Animation animation) {}
            @Override public void onAnimationEnd(Animation animation) {}
        });
        v.startAnimation(anim_out);
    }

    public void animation(@NonNull RecyclerView.ViewHolder viewHolder)
    {
        RecipeListAdapter.RecipeViewHolder recipeViewHolder = (RecipeListAdapter.RecipeViewHolder) viewHolder;
        ImageView heart = recipeViewHolder.getFavoriteImageView(R.id.recipe_favorite_border);
        if(!recipeViewHolder.getFavorite())
        {
            ImageViewAnimatedChange(getActivity(), recipeViewHolder.getFavoriteImageView(R.id.recipe_favorite_border), R.drawable.ic_favourites_black_24dp);
            recipeViewHolder.setFavorite(true);
            heart.animate().scaleX(1.5f).scaleY(1.5f).setInterpolator(new AccelerateDecelerateInterpolator()).setDuration(100);
            int position = viewHolder.getAdapterPosition();
            mFavouritesViewModel.addFavorite(mAdapter.getRecipes().get(position));
            mRecipeViewModel.setSelection(mFavouritesViewModel.getFavoriteRecipes());
        }
        else
        {
            ImageViewAnimatedChange(getActivity(), recipeViewHolder.getFavoriteImageView(R.id.recipe_favorite_border), R.drawable.ic_baseline_favorite_border_24);
            recipeViewHolder.setFavorite(false);
            heart.animate().scaleX(1f).scaleY(1f).setInterpolator(new AccelerateDecelerateInterpolator()).setDuration(100);
            int position = viewHolder.getAdapterPosition();
            mFavouritesViewModel.deleteFavorite(mAdapter.getRecipes().get(position));
            mRecipeViewModel.setSelection(mFavouritesViewModel.getFavoriteRecipes());
        }
    }

}