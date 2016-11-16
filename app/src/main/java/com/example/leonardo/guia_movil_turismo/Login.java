package com.example.leonardo.guia_movil_turismo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import data_strategy.CouchbaseManager;
import pojos.Usuario;

public class Login extends AppCompatActivity {
    CouchbaseManager<String, Usuario> dbaUsuario;

    private EditText etUsuario;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //PAra la base de Datos
        dbaUsuario = new CouchbaseManager<String, Usuario>(this, Usuario.class);
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

        //Verificar Usuario
        try {
            etUsuario = (EditText) findViewById(R.id.et_usuario);
            String username = etUsuario.getText().toString();
            etPassword = (EditText) findViewById(R.id.et_contraseña);
            String password = etPassword.getText().toString();
            Usuario usuarioIngresado = new Usuario(username);
            usuarioIngresado.setPasswordHash(password);
            Usuario usuarioAlmacenado = dbaUsuario.get(usuarioIngresado.getUserName());

            if(usuarioAlmacenado!=null)
            {
                if (usuarioAlmacenado.verificarPassword(usuarioIngresado.getPasswordHash())) {
                    Intent intent = new Intent(this, Bienvenido.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Usuario o Password Incorrecto", Toast.LENGTH_LONG).show();
                    Log.v("Auth Error", "Usuario o Password Incorrecto");
                }
            }else{
                Toast.makeText(this, "Usuario No Registrado", Toast.LENGTH_LONG).show();
                Log.v("Auth Error", "Usuario No Registrado");
            }


        }catch (Exception e)
        {
            e.printStackTrace();
            Log.i("Error Login", "Error No Esperado!: "+ e.getMessage());

        }
    }

    public void sendero(View view){
        Intent intent=new Intent(this,Sendero.class);
        startActivity(intent);
    }
}
