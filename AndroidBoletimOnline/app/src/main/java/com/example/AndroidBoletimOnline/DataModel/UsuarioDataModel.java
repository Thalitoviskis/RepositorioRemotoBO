/*package com.example.AndroidBoletimOnline.DataModel;

import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.AndroidBoletimOnline.view.RedefinirSenhaTempAluno;

public class UsuarioDataModel  {

    public static final String TABELA = "tb_usuario";
    public static final String ID_USUARIO = "id_usuario";
    public static final String EMAIL = "email";
    public static final String SENHA = "senha";

    private static String query;

    public static String CriarTabela() {
        query = "CREATE TABLE " + TABELA + " ( ";
        query += ID_USUARIO + " INTEGER PRIMARY KEY AUTOINCREMENT, ";
        query += EMAIL + " TEXT,";
        query += SENHA + " TEXT";
        query += ")";
        return query;
    }

    public static String InserirUsuario() {
        query = "INSERT INTO tb_usuario (EMAIL, SENHA) VALUES " +
                "('thalita@professor', 123)," +
                "('thalita@aluno', 123), " +
                "('jhonathan@professor', 123)," +
                "('jhonathan@aluno', 123), " + TABELA + " (";
        return query;
    }

}*/


