package com.example.sourpower.recipe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.sourpower.R;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder>  {
    private List<RecipeTitle> mRecipeList;
    private LayoutInflater mInflater;
    private Context mContext;

    public RecipeListAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.mContext = context;
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.recipelist_item, parent, false);
        return new RecipeViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        if (mRecipeList != null) {
            RecipeTitle current = mRecipeList.get(position);
            holder.mRecipeTitleView.setText(current.getRecipeTitle());
            holder.mRecipeSubTitleView.setText("Crunchy but also soft");

            int valueInPixels = mContext.getResources().getDimensionPixelSize(R.dimen.corner_radius);
            Glide.with(mContext)
                    .load(current.getRecipeCover())
                    .transform(new FitCenter(), new RoundedCorners(valueInPixels))
                    .into(holder.mRecipeImageView);
        } else {
            // Covers the case of data not being ready yet.
            holder.mRecipeTitleView.setText("No recipe available!");
        }
    }

    public void setWords(List<RecipeTitle> recipeTitles){
        mRecipeList = recipeTitles;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mRecipeList != null)
            return mRecipeList.size();
        else return 0;
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder {
        public final MaterialCardView mRecipeCardView;
        public final TextView mRecipeTitleView;
        public final TextView mRecipeSubTitleView;
        public final ImageView mRecipeImageView;
        final RecipeListAdapter mAdapter;

        public RecipeViewHolder(View itemView, RecipeListAdapter adapter) {
            super(itemView);
            mRecipeCardView = itemView.findViewById(R.id.card);
            mRecipeTitleView = itemView.findViewById(R.id.recipe_title);
            mRecipeSubTitleView = itemView.findViewById(R.id.recipe_subtitle);
            mRecipeImageView = itemView.findViewById(R.id.recipe_image);
            this.mAdapter = adapter;
        }

        public MaterialCardView getCard()
        {
            return mRecipeCardView;
        }
    }
}

