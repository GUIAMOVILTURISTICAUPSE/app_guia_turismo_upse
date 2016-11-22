package com.example.leonardo.guia_movil_turismo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class DatosPersonales extends AppCompatActivity {

    private EditText et_Usuario,et_Nombres,et_Apellidos,et_Email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_personales);
        et_Usuario = (EditText)findViewById(R.id.et_usuario);
        et_Nombres = (EditText)findViewById(R.id.et_nombres);
        et_Apellidos = (EditText)findViewById(R.id.et_apellidos);
        et_Email = (EditText)findViewById(R.id.et_email);
        Bundle bundle = getIntent().getExtras();
        mostrarDatos(bundle);
    }
    private void mostrarDatos(Bundle bundle){
        et_Usuario.setText(bundle.getString("Usuario"));
        et_Nombres.setText(bundle.getString("Nombre"));
        et_Apellidos.setText(bundle.getString("Apellido"));
        et_Email.setText(bundle.getString("Email"));
    }
    public void aceptar(View view){
        Intent i=new Intent(this,PerfilUsuario.class);
        startActivity(i);
    }
}
