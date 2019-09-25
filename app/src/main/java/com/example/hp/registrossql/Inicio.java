package com.example.hp.registrossql;

import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Inicio extends AppCompatActivity {
    // ---------------------------------------------------------------------------------------------
    // Atributos.
    // ---------------------------------------------------------------------------------------------
    private Button btnInicioCrearCliente, btnInicioAgendarLlamada;

    private TextView lblInicioNombre;

    private int id = 0;

    private Usuario usuario;

    private daoUsuario daoUsuario;


    // ---------------------------------------------------------------------------------------------
    // Constructor.
    // ---------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        btnInicioCrearCliente = (Button) findViewById(R.id.btnInicioCrearCliente);
        btnInicioAgendarLlamada = (Button) findViewById(R.id.btnInicioAgendarLlamada);
        lblInicioNombre = (TextView) findViewById(R.id.lblInicioNombre);

        Bundle b = getIntent().getExtras();
        id = b.getInt("Id");
        daoUsuario = new daoUsuario(this);
        usuario = daoUsuario.getUsuarioById(id);
        lblInicioNombre.setText(usuario.getNombreCompleto());
    }
}
