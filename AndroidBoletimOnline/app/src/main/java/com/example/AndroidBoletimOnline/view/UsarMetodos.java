package com.example.AndroidBoletimOnline.view;

import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

public class UsarMetodos {

    //private static Context context;

    /*public static boolean login(String usuario, String senha) {
        boolean verificacao = false; //Varíavel com Verdadeiro

        if (usuario.trim().equals("Usuario") && senha.trim().equals("999")) {//trim() apagar todos os espaços
            verificacao = true;
        } else if (usuario.trim().equals("thalita@professor") && senha.trim().equals("123")) {
            verificacao = true;
        } else if (usuario.trim().equals("jhonathan@aluno") && senha.trim().equals("123")) {
            verificacao = true;
        } else if (usuario.trim().equals("thalita@aluno") && senha.trim().equals("123")) {
            verificacao = true;
        }else if (usuario.trim().equals("teste@aluno") && senha.trim().equals("111")){
            verificacao = true;
        }else if (usuario.trim().equals("testu@aluno") && senha.trim().equals("111")){
            verificacao = true;
        }
        return verificacao;
    }*/

    public static void alert(String s, Context context) {
        Toast toast = Toast.makeText(context, s, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }
}