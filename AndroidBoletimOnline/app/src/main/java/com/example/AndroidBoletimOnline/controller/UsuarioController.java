package com.example.AndroidBoletimOnline.controller;

import android.content.ContentValues;
import android.content.Context;

import androidx.annotation.Nullable;

import com.example.AndroidBoletimOnline.API.AppDataBase;
import com.example.AndroidBoletimOnline.DataModel.UsuarioDataModel;
import com.example.AndroidBoletimOnline.model.Usuario;

import java.util.List;

public class UsuarioController extends AppDataBase {

        public UsuarioController(@Nullable Context context) {super(context);}
        private static final String TABELA = UsuarioDataModel.TABELA;
        private ContentValues dados;


        public boolean InserirUsuario(Usuario obj){
            dados=new ContentValues();//O BD não é relacional então converter para ContentValues
            dados.put(UsuarioDataModel.ID_USUARIO,obj.getId());//Incluido o Id para alterar
            dados.put(UsuarioDataModel.EMAIL,obj.getEmail());
            dados.put(UsuarioDataModel.SENHA,obj.getSenha());

            return insert(TABELA,dados);
        }
   /* public List listarLogin(String usuario){
        return login(TABELA,usuario);
        }
    public int obterUltimoRegistro(){
        return getLastPK(TABELA); }

    public Usuario getClienteById(String id){
        return getClienteById(UsuarioDataModel.TABELA,id);
        }*/
}
