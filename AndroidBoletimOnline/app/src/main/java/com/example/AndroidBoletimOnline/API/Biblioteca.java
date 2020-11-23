package com.example.AndroidBoletimOnline.API;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Patterns;
import android.view.Gravity;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.util.regex.Pattern;

public class Biblioteca {

    public static boolean validEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    public static void alert(String s, Context context) {
        Toast toast = Toast.makeText(context, s, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }



}
