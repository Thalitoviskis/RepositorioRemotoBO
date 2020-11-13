package com.example.AndroidBoletimOnline.datamodel;

public class AtividadeDataModel {
    public static final String TABELA = "tbAtividade";
    public static final String ID = "idAtividade";
    public static final String NOMEATIVIDADE = "nomeAtividade";
    public static final String DATAENTREGA = "datEntrega";
    public static final String TIPOATIVIDADE = "tipoAtividade";

    private static String query;

    public static String CriarTabela() {
        query = "CREATE TABLE" +  TABELA  + " ( ";
        query +=  ID + " INTERGER PRIMARY KEY AUTOINCREMENT, ";
        query += NOMEATIVIDADE + " TEXT, ";
        query += DATAENTREGA  +  " DATE, ";
        query += TIPOATIVIDADE + "TEXT";
        query += ")";
        return query;
    }
}
