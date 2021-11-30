package com.example.demo.model;

import java.io.ByteArrayInputStream;

public class ReporteAlumnosDTO {
    private String nombreArchivo;
    private ByteArrayInputStream stream;
    private int longitud;


    public String getNombreArchivo() {
        return nombreArchivo;
    }
    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public ByteArrayInputStream getStream() {
        return stream;
    }
    public void setStream(ByteArrayInputStream stream) {
        this.stream = stream;
    }

    public int getLongitud() {
        return longitud;
    }
    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }
}
