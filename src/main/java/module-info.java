module org.example.ej2examenpspt3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.ej2examenpspt3 to javafx.fxml;
    exports org.example.ej2examenpspt3;
}