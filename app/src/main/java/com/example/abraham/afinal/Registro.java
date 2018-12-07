package com.example.abraham.afinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.api.Response;

import org.json.JSONException;
import org.json.JSONObject;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
//import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Registro extends AppCompatActivity {

    Button btnAceptar;
    EditText Username,Pass;
    RequestQueue colaSolicitud;

    getToken tok = new getToken();
    Intent a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        colaSolicitud = Volley.newRequestQueue(this);
        btnAceptar = findViewById(R.id.uptudate);
        Username = findViewById(R.id.usuario);
        Pass = findViewById(R.id.pass);

        a = new Intent(this,MainActivity.class);
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre=Username.getText().toString();
                String password=Pass.getText().toString();

                if(validarStrings(nombre)  && validarStrings(password)){

                    agregarUsuario(nombre,password,tok.get());
                    //  Modificar preferencias
                    PreferenciasFragment.setString(getApplicationContext(), PreferenciasFragment.getKey_nombre(), nombre);
                    PreferenciasFragment.setString(getApplicationContext(), PreferenciasFragment.getKey_pass(), password);

                    PreferenciasFragment.showUserSettings(getApplicationContext());

                    startActivity(a);
                    Toast.makeText(getApplicationContext(), "Registro exitoso", Toast.LENGTH_SHORT).show();

                }else{
                    //Toast.makeText(getApplicationContext(), "Datos incorrectos", Toast.LENGTH_SHORT).show();
                }





            }
        });

    }
    Boolean validarStrings (String texto){

        /// Como una validación simple
        /// Si el Texto es diferente de NULL
        ///Y quitandole los espacios espacios su longitud es Mayor  a cero
        return texto!=null && texto.trim().length()>0;

        //Devuelve verdadero si eso se cumple

    }

    public void agregarUsuario(String nombre,String password,String token){

        String URL = "http://192.168.1.78/WS/addUsuario.php?username="+nombre+"&pass="+password+"&token="+token;
        JsonObjectRequest getSolicitudUsuario = new JsonObjectRequest(
                Request.Method.GET,
                URL,
                null,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONObject respuestaUsuario = response;
                        try {
                            int estado = respuestaUsuario.getInt("estado");
                            String mensaje = respuestaUsuario.getString("mensaje");
                            if(estado==1) {
                                Toast.makeText(getApplicationContext(),"Se agrego correctamente",Toast.LENGTH_SHORT).show();
                            } else{
                                Toast.makeText(getApplicationContext(),"No se agrego",Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e){
                            Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"ERROR: "+error.toString(),Toast.LENGTH_LONG).show();
                    }
                }
        );
        colaSolicitud.add(getSolicitudUsuario);
    }
}
