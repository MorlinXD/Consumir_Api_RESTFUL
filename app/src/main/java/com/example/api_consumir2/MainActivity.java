package com.example.api_consumir2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import Servicios.Asynchtask;
import Servicios.WebService;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url = "https://uealecpeterson.net/turismo/categoria/getlistadoCB";
        Bundle bundle = this.getIntent().getExtras();
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService(
                url, datos, MainActivity.this, MainActivity.this);
        ws.execute("GET");


    }

    @Override
    public void processFinish(String result) throws JSONException {
        TextView txtBancos = (TextView)findViewById(R.id.txtResultado);

        String lstBancos="";

        JSONArray JSONlista =  new JSONArray(result);
        for(int i=0; i< JSONlista.length();i++){
            JSONObject banco=  JSONlista.getJSONObject(i);
            lstBancos = lstBancos + "\n" + 	banco.getString("id").toString() + banco.get("descripcion");
        }

        txtBancos.setText("Respuesta WS Lista Categorias " +  lstBancos);

    }


    public void btEnviarSubcategoria(View view){
        //Creamos el Intent
        Intent intent = new Intent(MainActivity.this, Subcategorias.class);
        EditText txtselec = (EditText)findViewById(R.id.editTextNumber);
        //Creamos la información a pasar entre actividades - Pares Key-Value
        Bundle b = new Bundle();
        b.putString("dato", txtselec.getText().toString());
        //Añadimos la información al intent
        intent.putExtras(b);
        // Iniciamos la nueva actividad
        startActivity(intent);
    }
    }

