package com.example.leonardo.guia_movil_turismo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.facebook.login.LoginManager;
import com.facebook.AccessToken;

public class Bienvenido extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenido);
        if(AccessToken.getCurrentAccessToken() == null){
            loginActivity();
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
}
