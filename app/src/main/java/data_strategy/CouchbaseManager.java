package data_strategy;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.Document;
import com.couchbase.lite.Manager;
import com.couchbase.lite.android.AndroidContext;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//import com.couchbase.client.CouchbaseClient;

/**
 * Created by ivans on 14-Nov-16.
 * Taken from StackOverflow... the place that holds the answers of life, the universe and everything
 * User: user1697575
 * http://stackoverflow.com/questions/25898023/couchbase-lite-on-android-general-architecture
 */

public class CouchbaseManager<K, V>
{
    private final Class<V> valueTypeParameterClass;
    final ObjectMapper mapper = new ObjectMapper();
    //Debe ser cambiado por un cliente para android
    //@Inject
    //private CouchbaseClient cbClient;

    private final String dataBaseName = "guia_turistica_upse";
    Manager couchBaseLiteManager;
    Database dbCouchbase;
    Activity act; //La actividad que llama a la clase

    //@Inject
    private Gson gson;

    public CouchbaseManager(Activity act, final Class<V> valueClass)
    {
        this.act = act;
        AndroidContext context = new AndroidContext(act.getApplicationContext());
        this.valueTypeParameterClass = valueClass;


        try {

            couchBaseLiteManager = new Manager(context, Manager.DEFAULT_OPTIONS);
            dbCouchbase = couchBaseLiteManager.getDatabase(dataBaseName);

        } catch (IOException e) {
            e.printStackTrace();
            Log.e("ErrorCouchbase", "Error en iniciar couchbase manager: " +
                    e.getMessage(), e);
        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
            Log.e("ErrorCouchbase", "Error de libreria Couchbase lite: " +
                    e.getMessage(), e);
        }
    }

    public V get(K key)
    {
        //Convertir la Llave a String...
        // Por alguna razon el codigo original asume que puede haber otro tipo de llaves
        // pero los metodos oficiales de CouchBase no soportan eso
        String llave = key.toString();

        V res = null;
        if (key != null)
        {
            Document doc = dbCouchbase.getExistingDocument(llave);

            if (doc != null)
            {
                /*
                Map m = doc.getProperties();
                HashMap<K,V> hMap =  (m instanceof HashMap)
                        ? (HashMap) m
                        : new HashMap<K,V>(m);
                res = (V)hMap;
                */
                try {
                    Map<String, Object> map = doc.getProperties();
                    HashMap<String, Object> copy = new HashMap<String, Object>(map);
                    //List<V> pojos = mapper.convertValue(map, new TypeReference<List<V>>() {  });
                    //res = pojos.get(0);
                    res = mapper.convertValue(map, valueTypeParameterClass);
                }catch (Exception e)
                {
                    e.printStackTrace();
                    Log.e("ErrorCouchbase", "Error de conversion de Map: " +
                            e.getMessage(), e);
                }

                //res = (V)doc.getProperties();
                //res = mapper.convertValue(doc.getProperties(),valueTypeParameterClass);
                //res = gson.fromJson(doc.toString(), valueTypeParameterClass);
            }
        }
        return res;
    }

    public void put(K key, V value)
    {
        int ttl = 0;
        String llave = key.toString();
        Map<String, Object> docContent = new HashMap<String, Object>();
        docContent.put("objeto", value);
        Log.i("CouchDB-Save", "docContent: " + String.valueOf(docContent));

        Document doc = new Document(getDbCouchbase(), llave);
        try {
            doc.putProperties(docContent);
            Log.i("CouchDB-Save", "Documento Guardado con Exito");
            Toast.makeText(act, "Exito al grabar a CouchBaseLite", Toast.LENGTH_SHORT).show();
        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
            Log.i("CouchDB-Save", "Failed to write document to Couchbase database!: "+ e.getMessage());
        }
        //cbClient.set(key.toString(), ttl, gson.toJson(value,valueTypeParameterClass));
        //cacheMan.set(key, ttl, gson.toJson(value, valueTypeParameterClass));
    }

    public void save(Object o)
    {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        Map<String, Object> props = mapper.convertValue(o, Map.class);
        String id = (String) props.get("_id");

        Document document;
        if(id == null)
        {
            document = getDbCouchbase().createDocument();
        }else{
            document = getDbCouchbase().getExistingDocument(id);

            if(document==null)
            {
                document = getDbCouchbase().getDocument(id);
            }else{
                props.put("_rev",document.getProperty("_rev"));
            }

        }

        try{
            document.putProperties(props);
        }catch (CouchbaseLiteException e)
        {
            e.printStackTrace();
            Log.i("CouchDB-Save", "Failed to write document to Couchbase database!: "+ e.getMessage());
        }
    }

    public Manager getCouchBaseLiteManager() {
        return couchBaseLiteManager;
    }

    public Database getDbCouchbase() {
        return dbCouchbase;
    }
}
