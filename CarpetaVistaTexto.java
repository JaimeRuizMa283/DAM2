/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.break4learning.clasefile.vista;

import com.break4learning.clasefile.controlador.ControlCarpeta;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.File;

public class CarpetaVistaTexto implements InterfazVista {

    private ControlCarpeta controlador;
    private final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    
    public void setControlador(ActionListener c) {
        this.controlador = (ControlCarpeta) c;
    }

    @Override
    public void arranca() {
        boolean activo = true;
        while (activo) {
            solicitaOperacion();
            int operacion = leeOpcion();
            activo = procesaNuevaOperacion(operacion);
        }
    }

    @Override
    public String getRuta() {
        System.out.print("Introduce la ruta base/directorio: ");
        return leeString();        
    }

    @Override
    public String getNombre() {
        System.out.print("Introduce el nombre (carpeta/archivo): ");
        return leeString();        
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        System.out.println(">> " + mensaje);
    }

    /**
     * Muestra la lista de archivos recibida del modelo (ya ordenada por listado())
     */
    @Override
    public void mostrarListado(File[] elementos) {
        if (elementos == null || elementos.length == 0) {
            System.out.println("El directorio está vacío o no existe.");
            return;
        }

        System.out.println("\n--- CONTENIDO DEL DIRECTORIO (CARPETAS PRIMERO) ---");
        for (File elem : elementos) {
            if (elem.isDirectory()) {
                System.out.println("[DIRECTORIO] " + elem.getName());
            } else if (elem.isFile()) {
                System.out.println("[ARCHIVO]    " + elem.getName() + " (" + elem.length() + " bytes)");
            }
        }
        System.out.println("---------------------------------------------------\n");
    }

    private int leeOpcion() {
        try {
            String s = in.readLine();
            return Integer.parseInt(s);
        } catch (IOException | NumberFormatException e) {
            return -1;
        }
    }

    private String leeString() {
        try {
            return in.readLine();
        } catch (IOException e) {
            return "";
        }
    }

    private void solicitaOperacion() {
        System.out.println("\nSelecciona una opción:");
        System.out.println("1: Crear carpeta (ruta completa)");
        System.out.println("2: Crear subcarpeta (ruta padre + nombre)");
        System.out.println("3: Crear subcarpeta (File padre + nombre)");
        System.out.println("4: Crear archivo");
        System.out.println("5: Listar contenido (ordenado por listado())");
        System.out.println("0: Salir");
        System.out.print("Opción: ");
    }

    private boolean procesaNuevaOperacion(int operacion) {
        switch (operacion) {
            case 0 -> {
                System.out.println("Hasta luego.");
                return false;
            }
            case 1 -> controlador.actionPerformed(new ActionEvent(this, operacion, CREARCARPETACONRUTACOMPLETA));
            case 2 -> controlador.actionPerformed(new ActionEvent(this, operacion, CREARCARPETACONRUTAPADREYNOMBRE));
            case 3 -> controlador.actionPerformed(new ActionEvent(this, operacion, CREARCARPETACONFILERUTAPADREYNOMBRE));
            case 4 -> controlador.actionPerformed(new ActionEvent(this, operacion, CREARARCHIVO));
            case 5 -> controlador.actionPerformed(new ActionEvent(this, operacion, LISTARCONTENIDODIRECTORIO));
            default -> System.out.println("Opción no válida.");
        }
        return true;
    }
}
