package com.example.sourpower;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

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

    public static int currentTheme(Context context) {
        switch (ColorSchemeUtility.getTheme(context))
        {
            case THEME_FLOUR:
                return R.style.Theme_Flour;
            case THEME_FLOWER:
                return R.style.Theme_Flower;
            default:
                return R.style.Theme_SourPower;
        }
    }
}
