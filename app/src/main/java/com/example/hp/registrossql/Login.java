package com.example.hp.registrossql;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity implements View.OnClickListener {
    // ---------------------------------------------------------------------------------------------
    // Atributos.
    // ---------------------------------------------------------------------------------------------
    private Button btnLoginIniciarSesion, btnLoginCancelar, btnLoginRegistrarse;

    private EditText txtLoginUsername, txtLoginContraseña;

    daoUsuario daoUsuario;


    // ---------------------------------------------------------------------------------------------
    // Constructor.
    // ---------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLoginIniciarSesion = (Button) findViewById(R.id.btnLoginIniciarSesion);
        btnLoginRegistrarse = (Button) findViewById(R.id.btnLoginRegistrarse);
        btnLoginCancelar = (Button) findViewById(R.id.btnLoginCancelar);
        txtLoginUsername = (EditText) findViewById(R.id.txtLoginUsername);
        txtLoginContraseña = (EditText) findViewById(R.id.txtLoginContraseña);

        btnLoginIniciarSesion.setOnClickListener(this);
        btnLoginRegistrarse.setOnClickListener(this);
        btnLoginCancelar.setOnClickListener(this);

        daoUsuario = new daoUsuario(this);
    }


    // ---------------------------------------------------------------------------------------------
    // Eventos.
    // ---------------------------------------------------------------------------------------------
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLoginIniciarSesion:

                String username = txtLoginUsername.getText().toString();
                String password = txtLoginContraseña.getText().toString();

                if (username.equals("") | password.equals("")){

                    AlertDialog.Builder alerta = new AlertDialog.Builder(Login.this);
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

                } else if (daoUsuario.login(username, password) == 1){

                    Usuario usuario = daoUsuario.getUsuario(username, password);
                    Toast.makeText(this, "Se inicio sesion.", Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(Login.this, Inicio.class);
                    intent1.putExtra("Id", usuario.getId());
                    startActivity(intent1);
                    finish();
                } else  if (daoUsuario.login(username, password) < 1){

                    AlertDialog.Builder alerta = new AlertDialog.Builder(Login.this);
                    alerta.setMessage("¡El usuario no existe o la contraseña es incorrecta.!")
                            .setCancelable(false)
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener(){
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog titulo = alerta.create();
                    titulo.setTitle("INTENTE DE NUEVO.");
                    titulo.show();

                }

                break;

            case R.id.btnLoginRegistrarse:

                Intent intent = new Intent(Login.this, RegistrarUsuario.class);
                startActivity(intent);
                finish();

                break;

            case R.id.btnLoginCancelar:

                Intent intent1 = new Intent(Login.this, MainActivity.class);
                startActivity(intent1);
                finish();

                break;
        }
    }
}












