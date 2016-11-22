package com.example.leonardo.guia_movil_turismo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class FotoUsuario extends AppCompatActivity {

    public ImageView img_Usuario;
    /*public ImageView getImagen (){
        return img;
    }
    public void setImagen (ImageView im){
        this.img = im;
    }*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto_usuario);
        img_Usuario = (ImageView)findViewById(R.id.iv_fotoDePerfil);
        Bundle bundle = getIntent().getExtras();
        byte[] imagen = bundle.getByteArray("Foto");
        Bitmap bmp = BitmapFactory.decodeByteArray(imagen,0,imagen.length);
        img_Usuario.setImageBitmap(bmp);
    }
    public void aceptar(View view){
        Intent i=new Intent(this,PerfilUsuario.class);
        startActivity(i);
    }
}
