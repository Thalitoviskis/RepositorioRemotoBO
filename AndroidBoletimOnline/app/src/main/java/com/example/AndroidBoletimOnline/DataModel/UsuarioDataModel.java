package com.example.AndroidBoletimOnline.DataModel;

public class UsuarioDataModel {

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
                "(1, 'thalita@professor')," +
                "(2, 'thalita@aluno'), " +
                "(3, 'jhonathan@professor')," +
                "(4, 'jhonathan@aluno'), " + TABELA + " (";
        return query;


    }
}


