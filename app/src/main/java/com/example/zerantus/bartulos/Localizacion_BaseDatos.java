package com.example.zerantus.bartulos;

public class Localizacion_BaseDatos {
    String nombre;
    String tipo;
    String altitud;
    String latitud;

    public Localizacion_BaseDatos() {

    }

    public Localizacion_BaseDatos(String nombre, String tipo, String altitud, String latitud) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.altitud = altitud;
        this.latitud = latitud;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public String getAltitud() {
        return altitud;
    }

    public String getLatitud() {
        return latitud;
    }

}
