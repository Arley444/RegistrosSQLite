package com.example.hp.registrossql;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by HP on 24/09/2019.
 */

public class Usuario {
    // ---------------------------------------------------------------------------------------------
    // Atributos.
    // ---------------------------------------------------------------------------------------------
    private int id;

    private String nombreCompleto;

    private String username;

    private String contraseña;


    // ---------------------------------------------------------------------------------------------
    // Constructor.
    // ---------------------------------------------------------------------------------------------
    public Usuario(){

    }

    public Usuario(String nombreCompleto, String username, String contraseña) {
        this.nombreCompleto = nombreCompleto;
        this.username = username;
        this.contraseña = contraseña;
    }

    // ---------------------------------------------------------------------------------------------
    // Métodos.
    // ---------------------------------------------------------------------------------------------
    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", username='" + username + '\'' +
                ", contraseña='" + contraseña + '\'' +
                '}';
    }

    public boolean isNull(){
        if (nombreCompleto.equals("") && username.equals("") && contraseña.equals("")){
            return false;
        }else {
            return true;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}
