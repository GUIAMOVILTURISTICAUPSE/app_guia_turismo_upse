package com.example.leonardo.guia_movil_turismo;

import android.app.Application;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import data_strategy.CouchbaseManager;
import pojos.Usuario;

/**
 * Created by BYRONALEXIS on 11/18/2016.
 */

public class guia_movil_app extends Application {
    @Override
    public void onCreate() {
        //recursos de facebook
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
    }
}
