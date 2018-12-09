package com.example.abraham.afinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AdapterNot extends BaseAdapter {
    Context context;
    List<notificacion> Notificaciones;

    public AdapterNot(Context c,List<notificacion> Notificacion) {
        this.context = c;
        this.Notificaciones = Notificacion;
    }

    @Override
    public int getCount() {
        return Notificaciones.size();
    }

    @Override
    public Object getItem(int position) {
        return Notificaciones.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vista = convertView;
        LayoutInflater inflate = LayoutInflater.from(context);
        vista = inflate.inflate(R.layout.notificacion, null);
        TextView asunto = (TextView) vista.findViewById(R.id.asunto);
        TextView mensaje= (TextView) vista.findViewById(R.id.mensaje);
        TextView fecha= (TextView) vista.findViewById(R.id.fecha_hora);
        asunto.setText(Notificaciones.get(position).getAsunto().toString());
        mensaje.setText(Notificaciones.get(position).getMensaje().toString());
        fecha.setText(Notificaciones.get(position).getFecha().toString());
        return vista;
    }
}