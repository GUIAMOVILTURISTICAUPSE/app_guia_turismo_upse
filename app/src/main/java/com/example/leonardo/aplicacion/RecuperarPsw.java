package com.example.leonardo.aplicacion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class RecuperarPsw extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_psw);
    }
    public void cancelar(View view){
        finish();
    }
    public void recuperar(View view){
        Toast toast=Toast.makeText(this,"Se a enviado un mensaje a su direccion " +
                "de correo ingresado..!! Suerte..!! ",Toast.LENGTH_LONG);
        toast.show();
    }
}
