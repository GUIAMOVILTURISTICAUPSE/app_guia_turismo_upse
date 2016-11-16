package com.example.leonardo.guia_movil_turismo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Bienvenido extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenido);
    }

    public void salir(View view){
        finish();
    }
}
