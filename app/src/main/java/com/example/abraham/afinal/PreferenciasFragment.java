package com.example.abraham.afinal;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.widget.Toast;

import javax.xml.parsers.SAXParser;

public class PreferenciasFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener{
    // IMPLEMENTAMOS LA INTERFAZ PARA PONER UTILIZAR UN LISTENER y para poder detenerlo


    //Creamos algunas variables finales de las KEY del archivo
    // preferencias( en el directorio XML

    private static final String key_nombre="nombreTexto";
    private static final String key_pass="passTexto";
    private static final String key_chkNombre="recordarNombre";
    private static final String key_chkPass="recordarPass";
    private static final String key_listIdiomas="listaIdiomas";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//CARGAR EL LAYOUT
        addPreferencesFromResource(R.xml.preferencias);
    }


    ///Modifcar las preferenciazs string
    // PARAMETROS : contexto y key
    public static String getString(Context contexto, final String key ){
        SharedPreferences shaPref = PreferenceManager.getDefaultSharedPreferences(contexto);
        return shaPref.getString(key,"");

    }

    public static void setString(Context contexto, final String key, final String value ){
        SharedPreferences shaPref = PreferenceManager.getDefaultSharedPreferences(contexto);
        SharedPreferences.Editor  editor=shaPref.edit();
        editor.putString(key,value);
        editor.commit();


    }

    ///Modifcar las preferencias BOOLEANAS
    // PARAMETROS : contexto, key y valor por defalt

    public static boolean getBoolean(Context contexto, final String key, final boolean defaultValue  ){
        SharedPreferences shaPref = PreferenceManager.getDefaultSharedPreferences(contexto);
        return shaPref.getBoolean(key, defaultValue);

    }

    public static void setBoolean(Context contexto, final String key, final boolean valor ){
        SharedPreferences shaPref = PreferenceManager.getDefaultSharedPreferences(contexto);
        SharedPreferences.Editor  editor=shaPref.edit();
        editor.putBoolean(key, valor);
        editor.commit();
    }

    ///MOSTRAR LAS PREFERENCIAS
    public static void showUserSettings(Context contexto){
        SharedPreferences shaPrefer = PreferenceManager.getDefaultSharedPreferences(contexto);

        StringBuilder builder = new StringBuilder();


        builder.append("\n Nombre:"+shaPrefer.getString(key_nombre, "No ha puesto un nombre"));
        builder.append("\n Recordar Nombre:"+shaPrefer.getBoolean(key_chkNombre, false));

        builder.append("\n Contraseña:"+shaPrefer.getString(key_pass, "No tiene contraseña"));
        builder.append("\n Recordar Contraseña:"+shaPrefer.getBoolean(key_chkPass, false));


        builder.append("\n Lista Idiomas:"+shaPrefer.getString(key_listIdiomas, "Idioma no seleccionado"));


        //Toast.makeText(contexto, builder.toString(),Toast.LENGTH_LONG).show();

    }



    /**
     *  Creamos los getters y setter
     */

    public static String getKey_nombre() {
        return key_nombre;
    }

    public static String getKey_pass() {
        return key_pass;
    }

    public static String getKey_chkNombre() {
        return key_chkNombre;
    }

    public static String getKey_chkPass() {
        return key_chkPass;
    }

    public static String getKey_listIdiomas() {
        return key_listIdiomas;
    }


    @Override
    public void onResume() {
        super.onResume();

        //enciende el LISTENER de eventos por si alguna clave de preferencia cambia
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        //Apagar el escuchador de preferencas
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    ///CAMBIAR ALGO DESDE EL MENU DE PREFERENCIAS
    @Override
    public void onSharedPreferenceChanged(SharedPreferences shaPref, String key) {
        shaPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        switch (key)
        {
            case key_nombre:
                String valor_nombre_et =shaPref.getString(key_nombre, "No hay nombre");
                Toast.makeText(getActivity().getApplicationContext(), "Valor nombre Texto: "+valor_nombre_et, Toast.LENGTH_LONG);
                break;

            case key_pass:
                String valor_pass_et =shaPref.getString(key_pass, "No hay contraseña");
                Toast.makeText(getActivity().getApplicationContext(), "Valor contraseña Texto: "+valor_pass_et, Toast.LENGTH_LONG);
                break;

            case key_chkNombre:
                Boolean valor_nombre_chk =shaPref.getBoolean(key_chkNombre, false);
                Toast.makeText(getActivity().getApplicationContext(), " Guardar nombre: "+valor_nombre_chk, Toast.LENGTH_LONG);
                break;

            case key_chkPass:
                Boolean valor_pass_chk =shaPref.getBoolean(key_chkPass, false);
                Toast.makeText(getActivity().getApplicationContext(), "Guardar contraseña: "+valor_pass_chk, Toast.LENGTH_LONG);
                break;

            case key_listIdiomas:
                String valor_Idiomas =shaPref.getString(key_listIdiomas, "No hay contraseña");
                Toast.makeText(getActivity().getApplicationContext(), "Valor nombre Texto: "+valor_Idiomas, Toast.LENGTH_LONG);
                break;


        }
    }
}
