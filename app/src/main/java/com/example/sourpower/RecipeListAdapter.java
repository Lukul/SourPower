package com.example.sourpower;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.LinkedList;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder>  {
    private final LinkedList<String> mRecipeList;
    private LayoutInflater mInflater;
    private Context mContext;

    public RecipeListAdapter(Context context, LinkedList<String> wordList) {
        this.mRecipeList = wordList;
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
        String mCurrent = mRecipeList.get(position);
        holder.recipeHeadingView.setText(mCurrent);
        RequestOptions options = new RequestOptions();
        //options.transform(new RoundedCorners(4));
        Glide.with(mContext)
            .load(R.drawable.me_bread_wide)
            //.apply(options)
            .into(holder.recipeImageView);
    }

    @Override
    public int getItemCount() {
        return mRecipeList.size();
    }

    class RecipeViewHolder extends RecyclerView.ViewHolder {
        public final TextView recipeHeadingView;
        public final ImageView recipeImageView;
        final RecipeListAdapter mAdapter;

        public RecipeViewHolder(View itemView, RecipeListAdapter adapter) {
            super(itemView);
            recipeHeadingView = itemView.findViewById(R.id.recipe_heading);
            recipeImageView = itemView.findViewById(R.id.recipe_image);
            this.mAdapter = adapter;
        }
    }
}
