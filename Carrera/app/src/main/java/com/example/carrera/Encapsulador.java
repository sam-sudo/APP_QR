package com.example.carrera;

public class Encapsulador {
    private int imagen;
    private String titulo;
    private String texto;


    public Encapsulador(int IdImagen, String textoTitulo, String textoContenido) {
        this.imagen = IdImagen;
        this.titulo = textoTitulo;
        this.texto = textoContenido;

    }

    public int get_IdImagen() {
        return imagen;
    }

    public String get_textoTitulo() {
        return titulo;
    }

    public String get_textoContenido() {
        return texto;
    }

}