package com.example.abraham.afinal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NotificacionL extends AppCompatActivity {
    ListView lista;
    AdapterNot NotifAdapter;
    ArrayList<notificacion> Notificaciones;
    RequestQueue colaSolicitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notificaciones);
        Notificaciones = new ArrayList<>();
        colaSolicitud = Volley.newRequestQueue(this);
        getNotification("dnXzU4XEyCU:APA91bHDEjn_Gfv5B-wlw9mVBGTr7_SBGptIDhdx4_Ph8BVCj3ZgfbreJ5KATVhrTB6jAVWSf09HVoJBGkNvwEJkMtyUneGynpv1b1JeMzJK0Eogax1lkO9QexN1f5mMoQQMcZk1CbHp");

        lista =  findViewById(R.id.list);
        NotifAdapter = new AdapterNot(getApplicationContext(), Notificaciones);
        lista.setAdapter(NotifAdapter);
    }

    public void addNotificacion(String titulo, String mensaje , String hora){
        this.Notificaciones.add(new notificacion(titulo, mensaje, hora) );}

    public void getNotification(String token) {

        String URL = "http://192.168.43.238/ws/getNot.php?token=" +token;
        JsonObjectRequest getSolicitudUsuario = new JsonObjectRequest(
                Request.Method.POST,
                URL,
                null,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        JSONObject respuestaUsuario = response;
                        try {
                            String titulo= respuestaUsuario.getString("Titulo");
                            String mensaje = respuestaUsuario.getString("Message");
                            String hora = respuestaUsuario.getString("Hora");
                            addNotificacion(titulo,mensaje,hora);
                            Toast.makeText(getApplicationContext(), titulo+mensaje+hora, Toast.LENGTH_SHORT).show();

                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                        }
                    }

                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "ERROR: " + error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        colaSolicitud.add(getSolicitudUsuario);
    }
}
