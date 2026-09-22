/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.break4learning.clasefile.modelo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import javax.swing.JFileChooser;

/**
 * Clase encargada de la lógica de la aplicación
 * 
 * @author Break4Learning by Javier García-Retamero Redondo
 * @version 1.0
 * Created on 10 sept 2024
 */


public class Carpeta {

    private String ruta;

    /**
     * Constructor con ruta
     * @param ruta Ruta de la carpeta
     */
    public Carpeta(String ruta) {
        this.ruta = ruta;
    }

    /**
     * Constructor vacío
     */
    public Carpeta() {
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getRuta() {
        return ruta;
    }

    /**
     * Obtiene el objeto File a partir de la ruta almacenada
     * @return Objeto File
     */
    public File getFileDeRuta() {
        return new File(ruta);
    }

    /**
     * Devuelve el contenido de la carpeta actual
     * @return Array de archivos/directorios
     */
    public File[] getContenidoDirectorio() {
        File directorio = getFileDeRuta();
        return directorio.listFiles();
    }

    /**
     * Crea un directorio en la ruta actual
     */
    public void crearCarpeta() {
        File directorioNuevo = new File(ruta);
        directorioNuevo.mkdir();
    }

    /**
     * Crea un subdirectorio en la ruta actual
     * @param nombreDirectorio Nombre de la nueva carpeta
     */
    public void crearCarpeta(String nombreDirectorio) {
        File directorioNuevo = new File(ruta, nombreDirectorio);
        directorioNuevo.mkdir();
    }

    /**
     * Crea un directorio especificando el padre
     * @param directorioRaiz Directorio padre
     * @param nombreDirectorio Nombre de la nueva carpeta
     */
    public void crearCarpeta(File directorioRaiz, String nombreDirectorio) {
        File directorioNuevo = new File(directorioRaiz, nombreDirectorio);
        directorioNuevo.mkdir();
    }

    /**
     * Crea un archivo dentro del directorio especificado
     */
    public void crearArchivo(File directorioRaiz, String nombreDirectorio, String nombreArchivo) {
        File directorio = new File(directorioRaiz, nombreDirectorio);

        if (!directorio.exists()) {
            directorio.mkdir();
        }

        File archivoNuevo = new File(directorio, nombreArchivo);

        try {
            archivoNuevo.createNewFile();
        } catch (IOException e) {
            System.out.println("No se ha podido crear el archivo.");
            e.printStackTrace();
        }
    }

    /**
     * Muestra la lista detallada de los elementos en un directorio
     */
    public void contenidoDirectorio(File directorioRaiz, String nombreDirectorio) {
        File directorio = new File(directorioRaiz, nombreDirectorio);

        if (!directorio.exists()) {
            System.out.println("La ruta de directorio no existe");
            return;
        }
        if (!directorio.isDirectory()) {
            System.out.println("La ruta indicada no es un directorio");
            return;
        }

        File[] contenido = directorio.listFiles();

        if (contenido == null || contenido.length == 0) {
            System.out.println("El directorio está vacío");
            return;
        }

        System.out.println("Contenido del directorio: " + directorio.getName() + ":");
        for (File elemento : contenido) {
            if (elemento.isDirectory()) {
                System.out.println("[DIRECTORIO] " + elemento.getName());
            } else if (elemento.isFile()) {
                System.out.println("[ARCHIVO] " + elemento.getName());
                System.out.println("   Tamaño: " + elemento.length() + " bytes");
                System.out.println("   Ruta: " + elemento.getAbsolutePath());
            }
        }
    }

    /**
     * Abre un selector gráfico para elegir un directorio
     * @return Ruta del directorio seleccionado o null si se cancela
     */
    public static String seleccionaDirectorio() {
        JFileChooser f = new JFileChooser();
        f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int seleccion = f.showOpenDialog(null);

        if (seleccion == JFileChooser.APPROVE_OPTION && f.getSelectedFile() != null) {
            return f.getSelectedFile().getAbsolutePath();
        }
        return null;
    }
    public File[] obtenerContenidoOrdenado(String ruta) {
        File[] elementos=new File(ruta).listFiles();
        if (elementos==null) {
            return new File[0];
        }
        Arrays.sort(elementos, Comparator.comparing(File::isFile));
        return elementos;
    }
    public File [] listado() {
        ArrayList<File> directorio=new ArrayList<>();
        ArrayList<File> archivos=new ArrayList<>();
        
        File nuevo=new File(ruta);
        File [] lista=nuevo.listFiles();
        
        for (File elemento: lista) {
            if (elemento.isDirectory()) {
                directorio.add(elemento);
            } else {
                archivos.add(elemento);
            }
        }
        directorio.addAll(archivos);
        File [] arrayFin=directorio.toArray(new File[directorio.size()]);
        return arrayFin;
    }
}
