package com.example.readysilience;

import android.content.Context;
import android.content.SharedPreferences;

public class AuthManager {

    private static final String PREF_NAME = "AuthPrefs";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";

    public static void setLoggedIn(Context context, boolean isLoggedIn) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit();
        editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn);
        editor.apply();
    }

    public static boolean isLoggedIn(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return prefs.getBoolean(KEY_IS_LOGGED_IN, false);
    }
}
