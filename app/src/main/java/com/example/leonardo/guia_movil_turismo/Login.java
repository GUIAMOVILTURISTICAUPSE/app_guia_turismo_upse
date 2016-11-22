package com.example.leonardo.guia_movil_turismo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import java.util.Arrays;

import data_strategy.CouchbaseManager;
import pojos.Usuario;

public class Login extends AppCompatActivity {
    CouchbaseManager<String, Usuario> dbaUsuario;

    private EditText etUsuario;
    private EditText etPassword;
    private ImageButton btn_facebook;
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //PAra la base de Datos
        dbaUsuario = new CouchbaseManager<String, Usuario>(this, Usuario.class);

        //Login Con Facebook
        //FacebookSdk.sdkInitialize(this.getApplicationContext());
        //FacebookSdk.sdkInitialize(this.getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

       // btn_facebook = (ImageButton) findViewById(R.id.ib_facebook);
       // btn_facebook = new(com.facebook.login.widget.LoginButton)();
        loginButton = (LoginButton)findViewById(R.id.loginButtonFacebook);
        //loginButton.setReadPermissions(Arrays.asList("email"));

        if(AccessToken.getCurrentAccessToken() == null){
            loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    pantallaPrincipal();
                }

                @Override
                public void onCancel() {
                    Toast.makeText(getApplicationContext(), R.string.cancel_login, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onError(FacebookException error) {
                    Toast.makeText(getApplicationContext(), R.string.error_login, Toast.LENGTH_SHORT).show();
                }
            });
        }
        else
        {
            Intent intent = new Intent(this, Bienvenido.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    private void pantallaPrincipal(){
        Intent intent = new Intent(this, Bienvenido.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
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
