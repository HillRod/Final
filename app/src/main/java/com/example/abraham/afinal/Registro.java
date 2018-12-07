package com.example.abraham.afinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;

public class Registro extends AppCompatActivity {

    Button btnAceptar;
    EditText Username,Pass;
    Intent a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
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
}
