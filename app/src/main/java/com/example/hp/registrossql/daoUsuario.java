package com.example.hp.registrossql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by HP on 24/09/2019.
 */

public class daoUsuario {
    // ---------------------------------------------------------------------------------------------
    // Atributos.
    // ---------------------------------------------------------------------------------------------
    private Context context;

    private Usuario usuario;

    private ArrayList<Usuario> listaUsuarios;

    private SQLiteDatabase sql;

    private String db = "DBRegistrosLlamadas";

    private String sqlCreateTablaUsers = "create table if not exists usuarios(id integer primary key autoincrement, name text, username text, password text)";


    // ---------------------------------------------------------------------------------------------
    // Constructor.
    // ---------------------------------------------------------------------------------------------
    public daoUsuario(Context pContext){
        this.context = pContext;
        sql = context.openOrCreateDatabase(db, context.MODE_PRIVATE, null);
        sql.execSQL(sqlCreateTablaUsers);
        usuario = new Usuario();
    }


    // ---------------------------------------------------------------------------------------------
    // Metodos.
    // ---------------------------------------------------------------------------------------------
    public boolean insertarUsuario(Usuario pUsuario){
        if (buscarUsuario(pUsuario.getUsername()) == 0){
            ContentValues contentValues = new ContentValues();
            contentValues.put("username", pUsuario.getUsername());
            contentValues.put("password", pUsuario.getContraseña());
            contentValues.put("name", pUsuario.getNombreCompleto());
            return (sql.insert("usuarios", null, contentValues)>0);
        }else {
            return false;
        }
    }

    public int buscarUsuario(String pUsername){
         int x = 0;
        listaUsuarios = selectUsuarios();
        for (Usuario usuario:listaUsuarios){
            if (usuario.getUsername().equals(pUsername)){
                x++;
            }
        }
        return x;
    }

    public ArrayList<Usuario> selectUsuarios()
    {
        ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
        listaUsuarios.clear();
        Cursor cursor = sql.rawQuery("select * from usuarios", null);
        if (cursor != null && cursor.moveToFirst()){
            do {
                Usuario usuario = new Usuario();
                usuario.setId(cursor.getInt(0));
                usuario.setNombreCompleto(cursor.getString(1));
                usuario.setUsername(cursor.getString(2));
                usuario.setContraseña(cursor.getString(3));
                listaUsuarios.add(usuario);
            }while(cursor.moveToNext());
        }

        return listaUsuarios;
    }

    public int login(String pUsername, String pPassword){
        int a = 0;
        Cursor cursor = sql.rawQuery("select * from usuarios", null);
        if (cursor!=null && cursor.moveToFirst()){
            do {

                if (cursor.getString(2).equals(pUsername) && cursor.getString(3).equals(pPassword)){
                    a++;
                }

            }while (cursor.moveToNext());
        }

        return a;
    }

    public Usuario getUsuario(String pUsername, String pPassword){
        listaUsuarios = selectUsuarios();
        for (Usuario usuario:listaUsuarios){
            if (usuario.getUsername().equals(pUsername) && usuario.getContraseña().equals(pPassword)){
                return usuario;
            }
        }
        return null;
    }

    public Usuario getUsuarioById(int pId){
        listaUsuarios = selectUsuarios();
        for (Usuario usuario:listaUsuarios){
            if (usuario.getId() == pId){
                return usuario;
            }
        }
        return null;
    }
}


















