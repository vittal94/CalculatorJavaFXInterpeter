module com.example.calculatorjavafxinterpreter {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;


    opens com.example.calculatorjavafxinterpreter to javafx.fxml;
    exports com.example.calculatorjavafxinterpreter;
}