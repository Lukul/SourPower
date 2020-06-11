package com.example.sourpower;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;

import java.util.LinkedList;

public class IngredientListAdapter extends RecyclerView.Adapter<IngredientListAdapter.IngredientViewHolder> {
    private LayoutInflater mInflater;
    private final LinkedList<Integer> mIngredientList;
    private Context mContext;

    public IngredientListAdapter(Context context, LinkedList<Integer> ingredientPictureList) {
        mInflater = LayoutInflater.from(context);
        this.mIngredientList = ingredientPictureList;
        this.mContext = context;
    }
    class IngredientViewHolder extends RecyclerView.ViewHolder {
        public final ImageView ingredientItemView;
        final IngredientListAdapter mAdapter;

        public IngredientViewHolder(View itemView, IngredientListAdapter adapter) {
            super(itemView);
            ingredientItemView = itemView.findViewById(R.id.ingredient);
            this.mAdapter = adapter;
        }
    }


    @NonNull
    @Override
    public IngredientListAdapter.IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.ingredients_item, parent, false);
        return new IngredientViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientListAdapter.IngredientViewHolder holder, int position) {
        Integer mCurrent = mIngredientList.get(position);
        Glide.with(mContext)
                .load(mCurrent)
                .into(holder.ingredientItemView);

    }

    @Override
    public int getItemCount() {
        return mIngredientList.size();
    }
}
