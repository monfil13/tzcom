package com.example.tzcom;
public class Resena {
    private String nombreLocal;
    private String contenido;
    private String fecha;
    private String usuario;

    // Constructor vacío necesario para Firestore
    public Resena() {}

    public Resena(String nombreLocal, String contenido, String fecha, String usuario) {
        this.nombreLocal = nombreLocal;
        this.contenido = contenido;
        this.fecha = fecha;
        this.usuario = usuario;
    }

    public String getNombreLocal() {
        return nombreLocal;
    }

    public void setNombreLocal(String nombreLocal) {
        this.nombreLocal = nombreLocal;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
