package com.example.leonardo.guia_movil_turismo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Sendero extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendero);

    }


    public void comentario(View view){
        Intent intent=new Intent(this,Comentarios.class);
        startActivity(intent);
    }
    public void recursosAnimados(View view){
        Intent intent=new Intent(this,RecursosAnimados.class);
        startActivity(intent);
    }

    public void galeria(View view){
        Intent intent=new Intent(this,Galeria.class);
        startActivity(intent);
    }
}
