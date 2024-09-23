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
        if(currentNumber.isNotBlank()) {
            if(currentNumber.last() in listOf('-', '+', '*', '/', '%')) {
               currentNumber = currentNumber.removeRange(
                   currentNumber.length-1 until currentNumber.length)
            }
            currentNumber += "+"
            labelOut.text = currentNumber
        }
    }

    @FXML
    private fun onMinusButtonClick() {
        currentNumber += "-"
        labelOut.text = currentNumber
    }

    @FXML
    private fun onDivideButtonClick() {
        if(currentNumber.isNotBlank()) {
            currentNumber += "/"
            labelOut.text = currentNumber
        }
    }

    @FXML
    private fun onMultiplyButtonClick() {
        if(currentNumber.isNotBlank()) {
            currentNumber += "*"
            labelOut.text = currentNumber
        }
    }

    @FXML
    private fun onEqualsButtonClick() {
        if(currentNumber.isNotBlank()) {
            if(!currentNumber.last().isDigit()) {
                currentNumber = currentNumber.removeRange(
                    currentNumber.length-1 until currentNumber.length
                )
            }
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
            expression += operator
        }
        return expression
    }


    private fun calculate(expression: String): String {
        val list = mutableListOf<String>()
        var acc = ""

        for(i in expression.indices) {
            val c = expression[i]
            if (expression.first() == '-' && i == 0) acc += c
            else if(c.isDigit() || c == '.') {
                acc += c
                if(expression.lastIndex == i) list.add(acc)
            }
            else
            {
                acc.let { if (it.isNotBlank())list.add(it) }
                acc = ""
                list.add(c.toString())
            }
        }
        //  println(list)
        list.reverse()

        for(i in list.size-1 downTo 0) {
            val str = list[i]
            if(str in listOf("/", "*")) {
                val result = str.let {
                    if(it == "/")
                        list[i+1].toDouble() / list[i-1].toDouble()
                    else
                        list[i+1].toDouble() * list[i-1].toDouble()
                }

                list.removeAt(i+1)
                list.removeAt(i)
                list.add(i,result.toString())
                list.removeAt(i-1)
            }
        }

        // println(list)

        for(i in list.size-1 downTo 0) {
            val str = list[i]
            if(str in listOf("-", "+")) {
                val result = str.let {
                    if(it == "+")
                        list[i+1].toDouble() + list[i-1].toDouble()
                    else
                        list[i+1].toDouble() - list[i-1].toDouble()
                }
                list.removeAt(i+1)
                list.removeAt(i)
                list.add(i,result.toString())
                list.removeAt(i-1)
            }
        }
        return if(list.isNotEmpty()) removeLastDotAndZero(list.first()) else ""
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