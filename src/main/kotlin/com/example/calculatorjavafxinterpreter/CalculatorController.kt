package com.example.calculatorjavafxinterpreter

import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.control.ListView
import javafx.scene.layout.Pane
import java.util.*

class CalculatorController {
    @FXML
    private lateinit var labelOut: Label
    @FXML
    private lateinit var historyPane: Pane
    @FXML
    private lateinit var historyListView: ListView<String>
    private var showHistory = false
    private var number = ""
    private var currentNumber = ""
    private val historyLog = Stack<String>()

    @FXML
    private fun onHistoryButtonClick() {
        showHistory = !showHistory
        historyPane.isVisible = showHistory
    }

    @FXML
    private fun onOneButtonClick() {
        currentNumber += "1"
        labelOut.text = currentNumber
    }

    @FXML
    private fun onTwoButtonClick() {
        currentNumber += "2"
        labelOut.text = currentNumber
    }

    @FXML
    private fun onThreeButtonClick() {
        currentNumber += "3"
        labelOut.text = currentNumber
    }

    @FXML
    private fun onFourButtonClick() {
        currentNumber += "4"
        labelOut.text = currentNumber
    }

    @FXML
    private fun onFiveButtonClick() {
        currentNumber += "5"
        labelOut.text = currentNumber
    }

    @FXML
    private fun onSixButtonClick() {
        currentNumber += "6"
        labelOut.text = currentNumber
    }

    @FXML
    private fun onSevenButtonClick() {
        currentNumber += "7"
        labelOut.text = currentNumber
    }

    @FXML
    private fun onEightButtonClick() {
        currentNumber += "8"
        labelOut.text = currentNumber
    }

    @FXML
    private fun onNineButtonClick() {
        currentNumber += "9"
        labelOut.text = currentNumber
    }

    @FXML
    private fun onZeroButtonClick() {
        currentNumber += "0"
        labelOut.text = currentNumber
    }

    @FXML
    private fun onPlusButtonClick() {
        currentNumber = validateExpression(currentNumber, "+")
        labelOut.text = currentNumber
    }

    @FXML
    private fun onMinusButtonClick() {
        currentNumber = validateExpression(currentNumber, "-")
        labelOut.text = currentNumber
    }

    @FXML
    private fun onDivideButtonClick() {
        currentNumber = validateExpression(currentNumber,"/")
        labelOut.text = currentNumber
    }

    @FXML
    private fun onMultiplyButtonClick() {
        currentNumber = validateExpression(currentNumber,"*")
        labelOut.text = currentNumber
    }

    @FXML
    private fun onEqualsButtonClick() {
       currentNumber = validateExpression(currentNumber)

        if(currentNumber.isNotBlank()) {
            historyListView.items.add(currentNumber)
            currentNumber = calculate(currentNumber)
            historyListView.items.add(currentNumber)
            labelOut.text = currentNumber
        }
        println(historyLog)
    }


    @FXML
    private fun onCButtonClick() {
        number = ""
        currentNumber = ""
        labelOut.text = currentNumber
    }

    @FXML
    private fun onDotButtonClick() {
        currentNumber = validateExpression(currentNumber, ".")
        labelOut.text = currentNumber


    }

    private fun validateExpression(expr: String, operator: String = ""): String {
        var expression = expr

        if (expression.isBlank() && operator == "-")
            expression += operator
        else if (expression.isNotBlank()) {
            if(expression.last() in listOf('-', '+', '*', '/', '%', '.')) {
                expression = expression.removeRange(expr.length-1 until expr.length)
            }
            //check double dots in number
            if(operator == "." && isDoubleDots(expression))
                return expression

               expression += operator
        }
        return expression
    }

    private fun isDoubleDots(expr: String): Boolean {
        for(i in expr.length-1 downTo 0) {
            val ch = expr[i]
            if (ch == '.') return true
            if(ch in listOf('-', '+', '*', '/','%')) return false
        }
        return false
    }
    private fun calculate(expression: String): String {
        val result = Evaluator.evaluate(expression).toString()
        return if(result.isNotBlank()) removeLastDotAndZero(result) else ""
    }

    private fun removeLastDotAndZero(str: String): String {
        return str.run {
            if (last() == '0' && this[length-2] == '.')
                removeRange(length-2 until length)
            else this
        }

    }

    @FXML
    private fun onClearButtonClick() {
        historyListView.items.clear()
    }


}