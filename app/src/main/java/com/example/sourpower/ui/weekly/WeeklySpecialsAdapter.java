package com.example.sourpower.ui.weekly;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sourpower.R;
import com.example.sourpower.recipe.Recipe;

import java.util.List;

public class WeeklySpecialsAdapter extends ArrayAdapter<Recipe> {
    private List<Recipe> mRecipeList;
    private Context mContext;

    public WeeklySpecialsAdapter(Context context) {
        super(context, 0);
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Recipe recipe = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_weekly_specials, parent, false);
        }
        // Lookup view for data population
        TextView title = (TextView) convertView.findViewById(R.id.weekly_specials_title);
        //TextView subtitle = (TextView) convertView.findViewById(R.id.weekly_specials_subtitle);
        ImageView cover = (ImageView) convertView.findViewById(R.id.weekly_specials_image);
        // Populate the data into the template view using the data object
        title.setText(recipe.getRecipeTitle());
        //subtitle.setText(recipe.hometown);
        cover.setImageResource(recipe.getRecipeCover());
        // Return the completed view to render on screen

        notifyDataSetChanged();
        return convertView;
    }
    public void setRecipes(List<Recipe> recipes){
        mRecipeList = recipes;
        notifyDataSetChanged();
    }
}