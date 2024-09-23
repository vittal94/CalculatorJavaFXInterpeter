package com.example.calculatorjavafxinterpreter

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage

class CalculatorApplication : Application() {
    override fun start(stage: Stage) {
        val fxmlLoader = FXMLLoader(
            CalculatorApplication::class.java.getResource("main-view.fxml")
        ).apply { setController(CalculatorController()) }

        val scene = Scene(fxmlLoader.load(), 450.0, 400.0)
        stage.title = "Calculator"
        stage.scene = scene
        stage.show()
    }
}

fun main() {
    Application.launch(CalculatorApplication::class.java)
}