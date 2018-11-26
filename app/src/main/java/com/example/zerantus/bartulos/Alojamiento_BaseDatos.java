package com.example.zerantus.bartulos;

public class Alojamiento_BaseDatos {
    String nombre;
    String altitud;
    String latitud;
    String img1;
    String telef;
    String descripcion;

    public Alojamiento_BaseDatos() {

    }

    public Alojamiento_BaseDatos(String nombre, String altitud, String latitud, String img1, String telef, String descripcion) {
        this.nombre = nombre;
        this.altitud = altitud;
        this.latitud = latitud;
        this.img1 = img1;
        this.telef = telef;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getAltitud() {
        return altitud;
    }

    public String getLatitud() {
        return latitud;
    }

    public String getImg1() {
        return img1;
    }

    public String getTelef() {return telef; }

    public String getDescripcion() {return descripcion; }

}
