module com.mycompany.filestreamscaracteres {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.filestreamscaracteres to javafx.fxml;
    exports com.mycompany.filestreamscaracteres;
}
