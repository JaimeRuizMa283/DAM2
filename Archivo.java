/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.break4learning.clasefile.modelo;

import java.io.File;
import java.io.IOException;

public class Archivo {

    private String ruta;
    private String archivo;

    public Archivo(String ruta, String archivo) {
        this.ruta = ruta;
        this.archivo = archivo;
    }

    public Archivo() {
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public File getFileDeRuta() {
        return new File(ruta);
    }

    /**
     * Crea un archivo físico basado en los atributos de la instancia actual
     * @throws IOException Si ocurre un error de creación
     */
    public boolean crear() throws IOException {
        File file = new File(ruta, archivo);
        File carpetaPadre = file.getParentFile();

        if (carpetaPadre != null && !carpetaPadre.exists()) {
            carpetaPadre.mkdirs();
        }

        return file.createNewFile();
    }
}
