/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.filestreamcaracteres.modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class LecturaEscritura {
    private File rutayNombreArchivo;

    public LecturaEscritura(String rutayNombreArchivo) {
        this.rutayNombreArchivo = new File(rutayNombreArchivo);
    }

    public File getRutayNombreArchivo() {
        return rutayNombreArchivo;
    }

    public void setRutayNombreArchivo(String rutayNombreArchivo) {
        this.rutayNombreArchivo = new File(rutayNombreArchivo);
    }

    public void escribirStreamBufferedPrintCaracteres(String cadena, boolean append) {
        try (FileWriter ficheroOut = new FileWriter(getRutayNombreArchivo(), append);
             PrintWriter bufferFicheroOut = new PrintWriter(ficheroOut)) {

            bufferFicheroOut.print(cadena);
            bufferFicheroOut.flush();

        } catch (IOException ex) {
            System.getLogger(LecturaEscritura.class.getName()).log(System.Logger.Level.ERROR, "Error al escribir archivo", ex);
        }
    }

    public String leerCaracteresBufferReader() {
        StringBuilder texto = new StringBuilder();

        try (FileReader ficheroIn = new FileReader(getRutayNombreArchivo());
             BufferedReader bufferficheroIn = new BufferedReader(ficheroIn)) {

            String linea;
            while ((linea = bufferficheroIn.readLine()) != null) {
                texto.append(linea).append(System.lineSeparator());
            }
        } catch (FileNotFoundException ex) {
            System.getLogger(LecturaEscritura.class.getName()).log(System.Logger.Level.ERROR, "Archivo no encontrado", ex);
        } catch (IOException ex) {
            System.getLogger(LecturaEscritura.class.getName()).log(System.Logger.Level.ERROR, "Error al leer archivo", ex);
        }

        return texto.toString();
    }
}
