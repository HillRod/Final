package com.example.abraham.afinal;

public class notificacion{
    private String asunto,mensaje,fecha;

    public notificacion(String asunto, String mensaje, String fecha) {
        this.asunto = asunto;
        this.mensaje = mensaje;
        this.fecha = fecha;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }



}