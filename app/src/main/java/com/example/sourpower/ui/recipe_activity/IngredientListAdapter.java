package com.example.sourpower.ui.recipe_activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sourpower.R;

import java.util.LinkedList;

public class IngredientListAdapter extends RecyclerView.Adapter<IngredientListAdapter.IngredientViewHolder> {
    private LayoutInflater mInflater;
    private final LinkedList<Ingredient> mIngredientList;
    private Context mContext;

    public IngredientListAdapter(Context context, LinkedList<Ingredient> ingredientPictureList) {
        mInflater = LayoutInflater.from(context);
        this.mIngredientList = ingredientPictureList;
        this.mContext = context;
    }
    class IngredientViewHolder extends RecyclerView.ViewHolder {
        public final ImageView ingredientImageView;
        public final TextView ingredientNameView;
        final IngredientListAdapter mAdapter;

        public IngredientViewHolder(View itemView, IngredientListAdapter adapter) {
            super(itemView);
            ingredientImageView = itemView.findViewById(R.id.ingredient_image);
            ingredientNameView = itemView.findViewById(R.id.ingredient_name);
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
        Ingredient mCurrent = mIngredientList.get(position);
        holder.ingredientNameView.setText(mCurrent.getName());
        Glide.with(mContext)
                .load(mCurrent.getImage())
                .circleCrop()
                .into(holder.ingredientImageView);

    }

    @Override
    public int getItemCount() {
        return mIngredientList.size();
    }
}
