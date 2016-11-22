package com.example.leonardo.guia_movil_turismo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AcercaDeTi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca_de_ti);
    }
    public void aceptar(View view){
        Intent i=new Intent(this,PerfilUsuario.class);
        startActivity(i);
    }
}
