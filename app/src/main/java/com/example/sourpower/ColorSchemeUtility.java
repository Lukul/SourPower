package com.example.sourpower;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.preference.PreferenceManager;
import android.util.TypedValue;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.ColorInt;

public class ColorSchemeUtility {
    public final static int THEME_SOUR_POWER = 0;
    public final static int THEME_FLOUR = 1;
    public final static int THEME_FLOWER = 2;

    public static void setTheme(Context context, int theme) {
    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
    prefs.edit().putInt(context.getString(R.string.prefs_theme_key), theme).apply();
}
    public static int getTheme(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getInt(context.getString(R.string.prefs_theme_key), -1);
    }

    public static void updateTheme(Context context, Window window) {
        switch (ColorSchemeUtility.getTheme(context))
        {
            case THEME_FLOUR:
                setTheme(context, R.style.AppThemeFlour);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(context.getResources().getColor(R.color.colorPrimaryDarkFlour));
                }
                break;
            case THEME_FLOWER:
                setTheme(context, R.style.AppThemeFlower);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(context.getResources().getColor(R.color.colorPrimaryDarkFlower));
                }
                break;
            default:
                setTheme(context, R.style.Theme_MyApp);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

                    TypedValue typedValue = new TypedValue();
                    Resources.Theme theme = context.getTheme();
                    theme.resolveAttribute(R.attr.colorPrimaryDark, typedValue, true);
                    @ColorInt int color = typedValue.data;
                    window.setStatusBarColor(color);
                }
        }
    }
}
