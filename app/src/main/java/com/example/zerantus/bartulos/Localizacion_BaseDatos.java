package com.example.zerantus.bartulos;

public class Localizacion_BaseDatos {
    String nombre;
    String tipo;
    String altitud;
    String latitud;
    String img1,img2;
    String img1desc,img2desc;
    String audio;

    public Localizacion_BaseDatos() {

    }

    public Localizacion_BaseDatos(String nombre, String tipo, String altitud, String latitud,String img, String img2,String img1desc, String img2desc,String audio) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.altitud = altitud;
        this.latitud = latitud;
        this.img1 = img1;
        this.img2 = img2;
        this.img1desc = img1desc;
        this.img2desc = img2desc;
        this.audio = audio;
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

    public String getImg1() {
        return img1;
    }

    public String getImg2() {return img2; }

    public String getImg1desc() {return img1desc; }

    public String getImg2desc() {return img2desc; }

    public String getAudio() {return audio; }


}
