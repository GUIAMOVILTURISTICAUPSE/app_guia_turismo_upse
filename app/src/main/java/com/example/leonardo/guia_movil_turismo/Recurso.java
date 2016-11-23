package com.example.leonardo.guia_movil_turismo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;

import data_strategy.CouchbaseManager;
import pojos.Recursos;

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
    }

    public void cargarRecurso(String key)
    {
        try{

            Recursos recurso = dbaRecurso.get(key);

            //llenando los campos

            int ranking = Math.round(recurso.getRanking());
            rtbCalificacionRecurso.setNumStars(ranking);
            tvNombreRecurso.setText(recurso.getNombre());

            //TODO Falta conversion de imagen a Recurso con funcionalidad de leonardo

            //Llenar ScrollView con descripcion del Recurso

            tvDescripcionRecurso.setText(recurso.getDescripcion());
            tvDescripcionRecurso.setLayoutParams(new ScrollView.LayoutParams(ScrollView.LayoutParams.MATCH_PARENT,ScrollView.LayoutParams.MATCH_PARENT));

            //LinearLayout layout = new LinearLayout(this);
            //layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            //layout.addView(tvDescripcionRecurso);

            scrollDescripcionRecurso.setFillViewport(true);
            scrollDescripcionRecurso.addView(tvDescripcionRecurso);

            System.out.println(recurso);
            System.out.println(recurso.getDireccion());
            tvDireccion.setText(recurso.getDireccion());

        }catch(Exception e)
        {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        //TODO Insertar Comentarios aqui. Falta Pojo de Evelyn

    }

    public void guardarRecursoPrueba()
    {
        String key = "r1";

        Recursos recurso = new Recursos();
        recurso.set_id(key);
        recurso.setNombre("La Chocolatera");
        recurso.setDescripcion("La chocolatera es un lugar pedregoso donde te caes y te haces mierda.");
        //TODO Hay que cambiar el modelo de Datos, para tener por recurso una locacion exacta que contega Coordenadas
        //parroquia, canton y provincia.
        recurso.setDireccion("Base Naval de Salinas, Salinas, Ecuador");
        recurso.setRanking((float)4.23);


        dbaRecurso.save(recurso);

    }
}
