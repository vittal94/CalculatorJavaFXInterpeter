package com.example.calculatorjavafxinterpreter

fun isDouble(value: Any) = value is Double

fun isDouble(value: String): Boolean {
   return try {
        value.toDouble()
       true
    } catch (e: NumberFormatException) {
        false
    }
}

fun isInt(value: String): Boolean {
    return try {
        value.toInt()
        true
    } catch (e: NumberFormatException) {
        false
    }
}

fun isOperation(value: String): Boolean {
    return value in listOf("+", "-", "*", "/", "%")
}

fun isOperation(value: Any):Boolean {
    return try {
        isOperation(value as String)
    } catch (e: ClassCastException) {
        false
    }

}