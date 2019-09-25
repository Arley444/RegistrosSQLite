package com.example.hp.registrossql;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrarUsuario extends Activity implements View.OnClickListener {
    // ---------------------------------------------------------------------------------------------
    // Atributos.
    // ---------------------------------------------------------------------------------------------
    private Button btnRegistrarUsuario, btnCancelarRegistrarUsuario;

    private EditText txtUsernameRegistrarse, txtContraseñaRegistrarse, txtConfirmarContraseñaRegistrarse, txtNombreCompletoRegistrarse;

    com.example.hp.registrossql.daoUsuario daoUsuario;


    // ---------------------------------------------------------------------------------------------
    // Constructor.
    // ---------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);

        btnRegistrarUsuario = (Button) findViewById(R.id.btnRegistrarUsuario);
        btnCancelarRegistrarUsuario = (Button) findViewById(R.id.btnCancelarRegistrarUsuario);
        txtUsernameRegistrarse = (EditText) findViewById(R.id.txtUsernameRegistrarse);
        txtContraseñaRegistrarse = (EditText) findViewById(R.id.txtContraseñaRegistrarse);
        txtConfirmarContraseñaRegistrarse = (EditText) findViewById(R.id.txtConfirmarContraseñaRegistrarse);
        txtNombreCompletoRegistrarse = (EditText) findViewById(R.id.txtNombreCompletoRegistrarse);

        btnRegistrarUsuario.setOnClickListener(this);
        btnCancelarRegistrarUsuario.setOnClickListener(this);
        daoUsuario = new daoUsuario(this);
    }


    // ---------------------------------------------------------------------------------------------
    // Eventos.
    // ---------------------------------------------------------------------------------------------
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRegistrarUsuario:

                if (txtUsernameRegistrarse.getText().toString().isEmpty() | txtNombreCompletoRegistrarse.getText().toString().isEmpty()
                        | txtContraseñaRegistrarse.getText().toString().isEmpty() | txtConfirmarContraseñaRegistrarse.getText().toString().isEmpty()){

                    AlertDialog.Builder alerta = new AlertDialog.Builder(RegistrarUsuario.this);
                    alerta.setMessage("¡Por favor complete los campos!")
                            .setCancelable(false)
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener(){
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog titulo = alerta.create();
                    titulo.setTitle("FALTAN DATOS.");
                    titulo.show();

                } else {
                    if (txtContraseñaRegistrarse.getText().toString().equals(txtConfirmarContraseñaRegistrarse.getText().toString())){
                        Usuario usuario = new Usuario();
                        usuario.setNombreCompleto(txtNombreCompletoRegistrarse.getText().toString());
                        usuario.setUsername(txtUsernameRegistrarse.getText().toString());
                        usuario.setContraseña(txtContraseñaRegistrarse.getText().toString());

                        if (!usuario.isNull()){
                            Toast.makeText(this, "ERROR: Campos vacios.", Toast.LENGTH_LONG).show();
                        }else if (daoUsuario.insertarUsuario(usuario)){
                            Toast.makeText(this, "Registro Exitoso!!!", Toast.LENGTH_LONG).show();
                            Intent intent2 = new Intent(RegistrarUsuario.this, Login.class);
                            startActivity(intent2);
                            finish();
                        }else {
                            Toast.makeText(this, "Usuario ya exixte!!!", Toast.LENGTH_LONG).show();
                        }
                    }else {
                        AlertDialog.Builder alerta = new AlertDialog.Builder(RegistrarUsuario.this);
                        alerta.setMessage("¡La contraseña no coincide.!")
                                .setCancelable(false)
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener(){
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                        AlertDialog titulo = alerta.create();
                        titulo.setTitle("NO COINCIDEN.");
                        titulo.show();
                    }
                }
                break;

            case R.id.btnCancelarRegistrarUsuario:
                Intent intent1 = new Intent(RegistrarUsuario.this, MainActivity.class);
                startActivity(intent1);
                finish();
                break;
        }
    }
}
