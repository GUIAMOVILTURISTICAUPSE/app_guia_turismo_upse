package com.example.leonardo.guia_movil_turismo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.ByteArrayOutputStream;

import data_strategy.CouchbaseManager;
import pojos.Recursos;

import static android.graphics.BitmapFactory.*;

public class Recurso extends AppCompatActivity {
    CouchbaseManager<String, Recursos> dbaRecurso;

    //Campos del Layout
    RatingBar rtbCalificacionRecurso;
    TextView tvNombreRecurso;
    ImageView imgPrincipalRecurso;
    ScrollView scrollDescripcionRecurso;
    TextView tvDireccion;
    ScrollView scrollComentarios;
    TextView tvDescripcionRecurso;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recurso);
        dbaRecurso = new CouchbaseManager<String, Recursos>(this, Recursos.class);

        //Instanciar Campos del Layout
        rtbCalificacionRecurso = (RatingBar) findViewById(R.id.rtb_calificacion);
        tvNombreRecurso = (TextView) findViewById(R.id.tv_nombre_Recurso);
        imgPrincipalRecurso = (ImageView) findViewById(R.id.imgPrincipalRecurso);
        scrollDescripcionRecurso = (ScrollView) findViewById(R.id.scroll_descripcion);
        tvDireccion = (TextView) findViewById(R.id.tv_detalle_direccion);
        scrollComentarios = (ScrollView) findViewById(R.id.scroll_mejoresComentarios);
        tvDescripcionRecurso = (TextView) findViewById(R.id.tv_descrip_recurso);
        //Este metodo es solo para probar, luego se debe borrar
        guardarRecursoPrueba();


        //TODO Obtener la llave desde otro lado. (esta llave debe venir de otra vista
        String key = "r1";
        cargarRecurso(key);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void cargarRecurso(String key) {
        try {

            Recursos recurso = dbaRecurso.get(key);

            //llenando los campos

            int ranking = Math.round(recurso.getRanking());
            rtbCalificacionRecurso.setNumStars(ranking);
            tvNombreRecurso.setText(recurso.getNombre());

            //TODO Falta conversion de imagen a Recurso con funcionalidad de leonardo

            //Llenar ScrollView con descripcion del Recurso

            tvDescripcionRecurso.setText(recurso.getDescripcion());
            tvDescripcionRecurso.setLayoutParams(new ScrollView.LayoutParams(ScrollView.LayoutParams.MATCH_PARENT, ScrollView.LayoutParams.MATCH_PARENT));

            //LinearLayout layout = new LinearLayout(this);
            //layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            //layout.addView(tvDescripcionRecurso);

            scrollDescripcionRecurso.setFillViewport(true);
            scrollDescripcionRecurso.addView(tvDescripcionRecurso);

            System.out.println(recurso);
            System.out.println(recurso.getDireccion());
            tvDireccion.setText(recurso.getDireccion());

        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        //TODO Insertar Comentarios aqui. Falta Pojo de Evelyn

    }

    public void base(View view){
        Intent i = new Intent(this,Base64.class);
        startActivity(i);
    }

    public void guardarRecursoPrueba() {
        String key = "r1";

        Recursos recurso = new Recursos();
        recurso.set_id(key);
        recurso.setNombre("La Chocolatera");
        recurso.setDescripcion("La chocolatera es un lugar pedregoso donde te caes y te haces mierda.");
        //TODO Hay que cambiar el modelo de Datos, para tener por recurso una locacion exacta que contega Coordenadas
        //parroquia, canton y provincia.
        recurso.setDireccion("Base Naval de Salinas, Salinas, Ecuador");
        recurso.setRanking((float) 4.23);


        dbaRecurso.save(recurso);

    }

    //public void galeria(View view){
    //     Intent intent = new Intent(this,Galeria.class);
    //  startActivity(intent);
    //}
    public void irg(View view) {
        Intent i = new Intent(this, Galeria.class);
        startActivity(i);
    }


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Recurso Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
