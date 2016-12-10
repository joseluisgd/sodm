package com.example.joseluisgd.ulimarket.clases;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by joseluisgd on 11/28/16.
 */

public class SessionManager {


    public void setPreferences(Context context, String key, String value) {
        SharedPreferences.Editor editor = context.getSharedPreferences("ulimarket", Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.commit();

    }



    public  String getPreferences(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences("ulimarket",	Context.MODE_PRIVATE);
        String position = prefs.getString(key, "");
        return position;
    }
}