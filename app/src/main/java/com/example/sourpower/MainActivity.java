package com.example.sourpower;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.sourpower.ui.all.AllFragment;
import com.example.sourpower.ui.recipe_activity.RecipeActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {
    private Fragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(ColorSchemeUtility.currentTheme(getApplicationContext()));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_dailyspec, R.id.navigation_favourites, R.id.navigation_all, R.id.navigation_profile)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);

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
        ColorSchemeUtility.setTheme(getApplicationContext(), ColorSchemeUtility.THEME_FLOUR);
        recreateActivity();
    }

    public void FlowerThemeChange(View view) {
        ColorSchemeUtility.setTheme(getApplicationContext(), ColorSchemeUtility.THEME_FLOWER);
        recreateActivity();
    }

    public void SourPowerThemeChange(View view) {
        ColorSchemeUtility.setTheme(getApplicationContext(), ColorSchemeUtility.THEME_SOUR_POWER);
        recreateActivity();
    }

    public void launchRecipeActivity(View view) {
        Intent intent = new Intent(this, RecipeActivity.class);
        startActivity(intent);
    }

    @Override
    public void setTitle(int titleId) {
        TextView allTitleTextView = findViewById(R.id.fragment_title);
        allTitleTextView.setText(titleId);
    }


}

