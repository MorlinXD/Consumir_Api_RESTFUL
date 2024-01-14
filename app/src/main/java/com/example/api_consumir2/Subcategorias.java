package com.example.api_consumir2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import Servicios.Asynchtask;
import Servicios.WebService;

public class Subcategorias extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subcategorias);
        String eleccion = getIntent().getStringExtra("dato");

        String url = "https://uealecpeterson.net/turismo/subcategoria/getlistadoCB/" + eleccion;
        Bundle bundle = this.getIntent().getExtras();
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService(
                url, datos, Subcategorias.this, Subcategorias.this);
        ws.execute("GET");
    }

    @Override
    public void processFinish(String result) throws JSONException {
        TextView txtBancos = (TextView)findViewById(R.id.txtSubcategoria);

        String lstBancos="";

        JSONArray JSONlista =  new JSONArray(result);
        for(int i=0; i< JSONlista.length();i++){
            JSONObject banco=  JSONlista.getJSONObject(i);
            lstBancos = lstBancos + "\n" + 	banco.getString("id").toString() + banco.get("descripcion");
        }

        txtBancos.setText("Respuesta WS Lista SubCategorias " +  lstBancos);
    }
}