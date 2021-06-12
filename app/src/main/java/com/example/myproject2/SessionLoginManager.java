package com.example.myproject2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class SessionLoginManager {

    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public Context context;

    private static final String PREF_NAME = "LOGIN";
    private static final String LOGIN = "IS_LOGIN";
    public static final String ID = "ID";

    public SessionLoginManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void createSession(int userId) {
        editor.putBoolean(LOGIN, true);
        editor.putInt(ID, userId);
        editor.apply();
    }

    public boolean isLoggin() {
        return sharedPreferences.getBoolean(LOGIN, false);
    }

    public void checkLogin() {
        if(!this.isLoggin()) {
            Intent i = new Intent(context, LoginActivity.class);
            context.startActivity(i);
            ((MainActivity) context).finish();
        }
    }

    public int getUserID() {
        return sharedPreferences.getInt(ID, 0);
    }

    public void logout() {
        editor.clear();
        editor.remove(ID);
        editor.commit();
        Intent i = new Intent(context, LoginActivity.class);
        context.startActivity(i);
        ((MainActivity) context).finish();
    }
}
