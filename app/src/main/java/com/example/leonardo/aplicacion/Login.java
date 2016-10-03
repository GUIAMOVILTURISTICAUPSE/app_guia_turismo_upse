package com.example.leonardo.aplicacion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void registrar(View view){
        Intent i=new Intent(this,Registrarse.class);
        startActivity(i);
    }

    public void recuperarPsw(View view){
        Intent i=new Intent(this,RecuperarPsw.class);
        startActivity(i);
    }
    public void entrar(View view){
        Intent intent=new Intent(this,Bienvenido.class);
        startActivity(intent);
    }
}
