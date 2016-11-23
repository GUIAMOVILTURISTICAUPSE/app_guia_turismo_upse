package com.example.leonardo.guia_movil_turismo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.facebook.login.LoginManager;

public class Bienvenido extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenido);

        //TODO Corregir este codigo de Byron porque solo permite el login via facebook, no el login del app
        //Si el usuario se logea via el metodo del app, el codigo de abajo lo regresa al login y eso es un error
        /*if(AccessToken.getCurrentAccessToken() == null){
            loginActivity();
        }*/
        try {
            Thread.sleep(1000);
            ir_recurso();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void loginActivity() {
        Intent intent = new Intent(this, Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    public void logout(View view) {
        LoginManager.getInstance().logOut();
        loginActivity();
    }
    public void salir(View view){
        finish();
    }

    public void ir_recurso(){
        Intent i=new Intent(this,Recurso.class);
        startActivity(i);
    }

}
