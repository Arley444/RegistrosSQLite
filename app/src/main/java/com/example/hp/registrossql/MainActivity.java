package com.example.hp.registrossql;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    // ---------------------------------------------------------------------------------------------
    //  Atributos.
    // ---------------------------------------------------------------------------------------------
    private Button btnIrIniciarSesion, btnIrRegistrarse, btnSalirAplicacion;

    // ---------------------------------------------------------------------------------------------
    // Constructor.
    // ---------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnIrIniciarSesion = (Button) findViewById(R.id.btnIrIniciarSesion);
        btnIrRegistrarse = (Button) findViewById(R.id.btnIrRegistrarse);
        btnSalirAplicacion = (Button) findViewById(R.id.btnSalirAplicacion);

        btnIrIniciarSesion.setOnClickListener(this);
        btnIrRegistrarse.setOnClickListener(this);
        btnSalirAplicacion.setOnClickListener(this);
    }

    // ---------------------------------------------------------------------------------------------
    // Eventos
    // ---------------------------------------------------------------------------------------------
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnIrIniciarSesion:
                Intent intent1 = new Intent(this, Login.class);
                startActivity(intent1);
                break;

            case R.id.btnIrRegistrarse:
                Intent intent2 = new Intent(this, RegistrarUsuario.class);
                startActivity(intent2);
                break;

            case R.id.btnSalirAplicacion:
                System.exit(0);
                break;
        }
    }
}
