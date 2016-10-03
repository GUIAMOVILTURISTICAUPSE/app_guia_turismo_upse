package com.example.leonardo.aplicacion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Registrarse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);
    }

    public void listo(View view){
        finish();
    }
}
