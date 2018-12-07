package com.example.abraham.afinal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    //UserSessionManager session;
    private EditText etNombre, etPass;  //
    private Button btnEntrar,btnRegistro;
    Context contexto;
    //getToken a;
    UserSessionManager session;
    PreferenciasFragment xd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        session = new UserSessionManager(getApplicationContext());
        if(session.isUserLoggedIn()==true){
            Intent i = new Intent(getApplicationContext(), SignedIn.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        }
        btnEntrar = findViewById(R.id.btnEntrar);
        btnRegistro = findViewById(R.id.btnRegistro);
        contexto=this;
        final SharedPreferences shaPref = PreferenceManager.getDefaultSharedPreferences(contexto);
        //a.onTokenRefresh();

        //Añadir elementos layout
        addView(); /// Lo primero siempre dentro del ONCREATE DEBE DE SER AÑADIR LAS VISTAS
        comprobarPreferencas();
        //Toast.makeText(getApplicationContext(),"User Login Status: " + session.isUserLoggedIn(),Toast.LENGTH_LONG).show();

        btnEntrar.setOnClickListener(new View.OnClickListener() {  /// Cuando se de click se valida nombre  y contraseña
            @Override
            public void onClick(View v) {
                ///

                String nombre=etNombre.getText().toString();
                String password=etPass.getText().toString();

                ///Se validan  el nombre y la contraseña
                /*if(validarStrings(nombre)  && validarStrings(password)){
                    //  Modificar preferencias
                    PreferenciasFragment.setString(contexto, PreferenciasFragment.getKey_nombre(), nombre);
                    PreferenciasFragment.setString(contexto, PreferenciasFragment.getKey_pass(), password);

                    PreferenciasFragment.showUserSettings(contexto);

                }else{
                    //Toast.makeText(getApplicationContext(), "Datos incorrectos", Toast.LENGTH_SHORT).show();
                }*/

                String username = etNombre.getText().toString();
                String pass = etPass.getText().toString();
                //Toast.makeText(getApplicationContext(), , Toast.LENGTH_SHORT).show();

                // Validate if username, password is filled
                if(username.trim().length() > 0 && password.trim().length() > 0){

                    // For testing puspose username, password is checked with static data
                    // username = admin
                    // password = admin  PreferenciasFragment.getKey_nombre();
                    if(username.equals(shaPref.getString("nombreTexto","")) && pass.equals(shaPref.getString("passTexto",""))){

                        // Creating user login session
                        // Statically storing name="Android Example"
                        // and email="androidexample84@gmail.com"
                        session.createUserLoginSession("User Session ", shaPref.getString("nombreTexto",""));

                        // Starting MainActivity
                        Intent i = new Intent(getApplicationContext(), SignedIn.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                        // Add new Flag to start new Activity
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);

                        finish();

                    }else{

                        // username / password doesn't match
                        Toast.makeText(getApplicationContext(), "Username/Password is incorrect", Toast.LENGTH_LONG).show();

                    }
                }else{

                    // user didn't entered username or password
                    Toast.makeText(getApplicationContext(), "Please enter username and password", Toast.LENGTH_LONG).show();

                }

            }


        });

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getApplicationContext(),Registro.class);
                startActivity(a);
            }
        });




    }

    private void addView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        etNombre =(EditText)findViewById(R.id.etNombre);
        etPass= (EditText)findViewById(R.id.etPass);
        btnEntrar=(Button)findViewById(R.id.btnEntrar);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, PreferenciasActivity.class)); //El botón setting habre la activity de preferencias que arroja el fragmen

            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    public void comprobarPreferencas(){

        if(PreferenciasFragment.getBoolean(contexto, PreferenciasFragment.getKey_chkNombre(), false))
        {
            etNombre.setText(PreferenciasFragment.getString(contexto,PreferenciasFragment.getKey_nombre()));
        }
        if(PreferenciasFragment.getBoolean(contexto, PreferenciasFragment.getKey_chkPass(), false))
        {
            etPass.setText(PreferenciasFragment.getString(contexto,PreferenciasFragment.getKey_pass()));
        }
    }


    Boolean validarStrings (String texto){

        /// Como una validación simple
        /// Si el Texto es diferente de NULL
        ///Y quitandole los espacios espacios su longitud es Mayor  a cero
        return texto!=null && texto.trim().length()>0;

        //Devuelve verdadero si eso se cumple

    }
}


