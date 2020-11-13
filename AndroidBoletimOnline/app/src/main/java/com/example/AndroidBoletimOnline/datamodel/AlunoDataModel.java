package com.example.AndroidBoletimOnline.datamodel;

public class AlunoDataModel {
    public static final String TABELA = "tbAluno";
    public static final String RM = "rmAluno";
    public static final String NOMEALUNO= "nomeAluno";
    public static final String TURMA = "idTurma";

    private static String query;

    public static String CriarTabela() {
        query = "CREATE TABLE" +  TABELA  + " ( ";
        query += RM + " INTERGER PRIMARY KEY AUTOINCREMENT, ";
        query += NOMEALUNO + " TEXT, ";
        query += TURMA +  " INTERGER, FOREIGN KEY (idTurma) REFERENCES TURMA (idTurma) ";
        query += ")";
        return query;
    }

}
