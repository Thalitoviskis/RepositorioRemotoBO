package com.example.AndroidBoletimOnline.API;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.AndroidBoletimOnline.DataModel.UsuarioDataModel;
import com.example.AndroidBoletimOnline.model.Usuario;
import com.example.AndroidBoletimOnline.view.UsarMetodos;

import java.util.ArrayList;
import java.util.List;

public class AppDataBase  extends SQLiteOpenHelper {

    public static final String DB_NOME = "usuario.sqlite";//Nome do BD
    public static final int DB_VERSION = 1; //Versão
    Cursor cursor; //Posição
    SQLiteDatabase db; //Cria objeto
    Context context; //Recebe o contexto

    public AppDataBase(@Nullable Context context) {
        super(context, DB_NOME, null, DB_VERSION);
        db = getWritableDatabase(); //Ler e escrever no BD

        this.context = context; //Obtem o context para usar no Toast
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Criar as tabelas
            try {
                db.execSQL(UsuarioDataModel.CriarTabela());

            } catch (SQLException e) {
        //TODO Ver o Toast
                UsarMetodos.alert("Erro Tabela não criada", context);
        //Log.e("Error Gerado", "DB===>MSG" + e.getMessage());
            }
        }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql="DROP TABLE IF EXISTS usuario";
        db.execSQL(sql);
        onCreate(db);
    }
    public boolean insert(String tabela, ContentValues values) {
        boolean ok = true;
        try {
            ok = db.insert(tabela, null, values) > 0;
        } catch (SQLException e) {
            e.getMessage();
        }
        return ok;
    }

    public boolean update(String tabela, ContentValues dados) {
        boolean ok = true;
        //TODO Update
        try {
            int id = dados.getAsInteger("id_usuario");
            ok = db.update(tabela, dados, "id_usuario=?", new String[]{Integer.toString(id)}) >
            0;
        } catch (SQLException e) {
            e.getMessage();
//Biblioteca.alertCustom(context,"======>>>"+e.getMessage());
        }
        return ok;
    }
    public List<Usuario> login(String tabela, String usuario) {
        List<Usuario> list = new ArrayList<>();
        Usuario usuarioSenha;

        String sql = "SELECT email,senha FROM  tb_usuario" + tabela +
                " WHERE email ='" + usuario + "'";

        try {
            cursor = db.rawQuery(sql, null); //O null para usar todas as colunas
            if (cursor.moveToFirst()) { //Se conseguir ir para o primeiro tem registro
                do {
                    usuarioSenha = new Usuario();
                    usuarioSenha.setEmail(cursor.getString(cursor.getColumnIndex(UsuarioDataModel.EMAIL)));
                    usuarioSenha.setSenha(cursor.getString(cursor.getColumnIndex(UsuarioDataModel.SENHA)));

                    list.add(usuarioSenha); //Na lista colocar o objeto

                } while (cursor.moveToNext());
            }
        } catch (SQLException e) {
        }
        return list;
    }
    public boolean isUserValid(Usuario usuario) {
        String sql = "select * from usuarios where user=? and senha=?";

        return  true;
    }
}
