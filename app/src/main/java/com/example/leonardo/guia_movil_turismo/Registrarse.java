package com.example.leonardo.guia_movil_turismo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import data_strategy.CouchbaseManager;
import pojos.Usuario;

public class Registrarse extends AppCompatActivity {
    CouchbaseManager<String, Usuario> dbaUsuario;

    private EditText etUsuario;
    private EditText etPassword;
    private EditText etPasswordConfirmation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        dbaUsuario = new CouchbaseManager<String, Usuario>(this, Usuario.class);
    }

    public void listo(View view){

        etUsuario = (EditText)findViewById(R.id.et_usuario);
        String username = etUsuario.getText().toString();
        etPassword = (EditText) findViewById(R.id.et_contraseña);
        String password = etPassword.getText().toString();
        etPasswordConfirmation = (EditText) findViewById(R.id.et_confirmar_psw);
        String passwordConfirmation = etPasswordConfirmation.getText().toString();
        password = password.trim();
        passwordConfirmation = password.trim();
        if(dbaUsuario.get(username)==null)
        {
            if(password.equals(passwordConfirmation))
            {
                Usuario usuarioIngresado = new Usuario(username, password);
                //dbaUsuario.put(username,usuarioIngresado);
                dbaUsuario.save(usuarioIngresado);
                Toast.makeText(this,"Usuario Resgitrado correctamente", Toast.LENGTH_LONG).show();

                Intent intent=new Intent(this,Bienvenido.class);
                startActivity(intent);
            }else{
                Toast.makeText(this,"Passwords No coinciden", Toast.LENGTH_LONG).show();
                Log.v("Auth Error", "Passwords No coinciden");
            }
        }else{
            Toast.makeText(this,"El usuario ya existe", Toast.LENGTH_LONG).show();
            Log.v("Auth Error", "El usuario ya existe");
        }
        finish();
    }
}
