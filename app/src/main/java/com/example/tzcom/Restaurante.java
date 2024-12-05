package com.example.tzcom;

public class Restaurante {
    private String nombre;
    private String direccion;
    private String telefono;
    private String horario;
    private String dias;
    private int imagen;

    public Restaurante(String nombre, String direccion, String telefono, String horario, String dias, int imagen) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.horario = horario;
        this.dias = dias;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getHorario() {
        return horario;
    }

    public String getDias() {
        return dias;
    }

    public int getImagen() {
        return imagen;
    }

    public String getTipoComida() {
        return dias;
    }
}
