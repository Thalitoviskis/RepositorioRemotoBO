package com.example.AndroidBoletimOnline.API;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import androidx.annotation.Nullable;

import com.example.AndroidBoletimOnline.datamodel.AlunoDataModel;
import com.example.AndroidBoletimOnline.datamodel.AtividadeDataModel;
import com.example.AndroidBoletimOnline.datamodel.AtribuicaoNotaDataModel;
import com.example.AndroidBoletimOnline.datamodel.TurmaDataModel;
import com.example.AndroidBoletimOnline.datamodel.UsuarioDataModel;
import com.example.AndroidBoletimOnline.model.AtribuicaoNota;
import com.example.AndroidBoletimOnline.model.Usuario;

public class AppDataBase  extends SQLiteOpenHelper {
    public static final String DB_NOME = "boletim.sqlite"; //Nome do BD
    public static final int DB_VERSION = 1; //Versão

    Cursor cursor; //Posição
    SQLiteDatabase db; //Cria objeto
    Context context; //Recebe contexto

    public  AppDataBase (@Nullable Context context){
        super(context, DB_NOME, null, DB_VERSION);
        db = getWritableDatabase(); //Ler e escrever no DB
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Criando as tabelas
        try {
            db.execSQL(AlunoDataModel.CriarTabela());
            db.execSQL(AtividadeDataModel.CriarTabela());
            db.execSQL(AtribuicaoNotaDataModel.CriarTabela());
            db.execSQL(TurmaDataModel.CriarTabela());
            db.execSQL(UsuarioDataModel.CriarTabela());

        } catch (SQLException e) {

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
