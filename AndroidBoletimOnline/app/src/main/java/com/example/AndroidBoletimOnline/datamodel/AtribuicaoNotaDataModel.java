package com.example.AndroidBoletimOnline.datamodel;

public class AtribuicaoNotaDataModel {
    public static final String TABELA = "tbAtribuicaoNota";
    public static final String ID = "idNota";
    public static final String IDALUNO = "idALuno";
    public static final String IDATIVIDADE = "idAtividade";

    private static String query;

    public static String CriarTabela() {
        query = "CREATE TABLE" +  TABELA  + " ( ";
        query +=  ID + " INTERGER PRIMARY KEY AUTOINCREMENT, ";
        query += IDALUNO + " FOREIGN KEY, ";
        query += IDATIVIDADE +  " INTERGER, FOREIGN KEY (idAtividade) REFERENCES TURMA (idAtividade), ";
        query += ")";
        return query;
    }
}
