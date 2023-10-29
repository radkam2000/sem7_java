module com.example.lab3_javafx {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.lab3_javafx to javafx.fxml;
    exports com.example.lab3_javafx;
}