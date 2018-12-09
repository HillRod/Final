package com.example.abraham.afinal;

import android.app.Notification;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class SignedIn extends AppCompatActivity {

    UserSessionManager session;

    Button btnLogout,btnnoti;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signed_in);
        session = new UserSessionManager(getApplicationContext());

        btnLogout = findViewById(R.id.btnLogout);
        btnnoti= findViewById(R.id.bntNotif);



        if (session.checkLogin())
           finish();


            btnLogout.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {

                    // Clear the User session data
                    // and redirect user to LoginActivity
                    session.logoutUser();
                }
            });
       btnnoti.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View arg0) {
              Intent i = new Intent(getApplicationContext(), NotificacionL.class);
               startActivity(i);

           }
       });
    }




}

