package com.example.AndroidBoletimOnline.model;

public class Usuario {
    private String email, senha;
    private int id;

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public int getId() {
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
}
