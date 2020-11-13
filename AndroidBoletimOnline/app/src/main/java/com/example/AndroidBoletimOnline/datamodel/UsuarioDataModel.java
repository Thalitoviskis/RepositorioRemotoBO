package com.example.AndroidBoletimOnline.datamodel;

public class UsuarioDataModel {
    public static final String TABELA = "tbUsuario";
    public static final String ID = "idUsuario";
    public static final String EMAIL = "emailUsuario";
    public static final String SENHA = "senhaUsuario";

    private static String query;

    public static String CriarTabela() {
        query = "CREATE TABLE" +  TABELA  + " ( ";
        query +=  ID + " INTERGER PRIMARY KEY AUTOINCREMENT, ";
        query += EMAIL + " TEXT, ";
        query += SENHA +  " VARCHAR, ";
        query += ")";
        return query;
    }
}
