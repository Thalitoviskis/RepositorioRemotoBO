package com.example.AndroidBoletimOnline.datamodel;

public class TurmaDataModel {
    public static final String TABELA = "tbTurma";
    public static final String ID = "idTurma";
    public static final String SERIE = "serieTurma";
    public static final String PERIODOLETIVO = "periodoLetivo";

    private static String query;

    public static String CriarTabela() {
        query = "CREATE TABLE" +  TABELA  + " ( ";
        query +=  ID + " INTERGER PRIMARY KEY AUTOINCREMENT, ";
        query += SERIE + " VARCHAR(20), ";
        query += PERIODOLETIVO +  " VARCHAR(10), ";
        query += ")";
        return query;
    }
}
