package com.mycompany.filestreamscaracteres;

import com.mycompany.filestreamcaracteres.modelo.LecturaEscritura;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class ControlLecturaEscrituraFXML {

    @FXML
    private TextArea txtContenido;

    private LecturaEscritura modelo;

    @FXML
    public void initialize() {
        this.modelo = new LecturaEscritura("");
    }

    @FXML
    private void elegirArchivo(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Archivo de Texto");
        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Archivos de Texto (*.txt)", "*.txt")
        );

        Stage stage = (Stage) txtContenido.getScene().getWindow();
        File archivoSeleccionado = fileChooser.showOpenDialog(stage);

        if (archivoSeleccionado != null) {
            modelo.setRutayNombreArchivo(archivoSeleccionado.getAbsolutePath());
            String contenido = modelo.leerCaracteresBufferReader();
            txtContenido.setText(contenido);
        }
    }

    @FXML
    private void guardarArchivo(ActionEvent event) {
        if (modelo.getRutayNombreArchivo() == null || modelo.getRutayNombreArchivo().getPath().isEmpty()) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Guardar Archivo Como...");
            fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Archivos de Texto (*.txt)", "*.txt")
            );

            Stage stage = (Stage) txtContenido.getScene().getWindow();
            File archivoGuardar = fileChooser.showSaveDialog(stage);

            if (archivoGuardar != null) {
                modelo.setRutayNombreArchivo(archivoGuardar.getAbsolutePath());
            } else {
                return;
            }
        }

        String textoAGuardar = txtContenido.getText();
        modelo.escribirStreamBufferedPrintCaracteres(textoAGuardar, false);
    }
}

