package com.example.readysilience.utils;

import android.widget.Toast;
import android.content.Context;

public class AndroidUtil {

    public static void showToast(Context context,String message){
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }
}
