package com.example.leonardo.guia_movil_turismo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class PerfilUsuario extends AppCompatActivity {

    private EditText et_nombreUsuario,et_nombres,et_apellidos,et_email,et_acercaDe;
    private ImageView iv_FotoUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_perfil_usuario);
        this.et_nombreUsuario = (EditText)findViewById(R.id.et_nombre_usuario);
        this.et_nombreUsuario.setFocusable(false);
        this.et_nombres = (EditText)findViewById(R.id.et_nombre);
        this.et_nombres.setFocusable(false);
        this.et_apellidos = (EditText)findViewById(R.id.et_apellidos);
        this.et_apellidos.setFocusable(false);
        this.et_email = (EditText)findViewById(R.id.et_email);
        this.et_email.setFocusable(false);
        this.et_acercaDe = (EditText)findViewById(R.id.et_acercaDeTi);
        this.et_acercaDe.setFocusable(false);
        this.iv_FotoUsuario = (ImageView)findViewById((R.id.iv_FotoPerfil));
    }
    public void editarDatos(View view){
        Intent i=new Intent(this,DatosPersonales.class);
        String usuario = et_nombreUsuario.getText().toString();
        String nombre = et_nombres.getText().toString();
        String apellido = et_apellidos.getText().toString();
        String email = et_email.getText().toString();

        i.putExtra("Usuario",usuario);
        i.putExtra("Nombre",nombre);
        i.putExtra("Apellido",apellido);
        i.putExtra("Email",email);
        startActivity(i);
    }
    public void editarAcercaDeTi(View view){
        Intent i=new Intent(this,AcercaDeTi.class);
        startActivity(i);
    }
    public void cambiarFoto(View view){
        Intent i=new Intent(this,FotoUsuario.class);

        iv_FotoUsuario.setDrawingCacheEnabled(true);
        iv_FotoUsuario.buildDrawingCache();
        Bitmap bm = iv_FotoUsuario.getDrawingCache();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] arrayImagen = stream.toByteArray();

        i.putExtra("Foto",arrayImagen);
        startActivity(i);
    }
}
