package com.example.calculatorjavafxinterpreter

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.*
import org.junit.jupiter.api.*
import java.util.EmptyStackException


class EvaluatorTest {
    @Test
    fun evaluateTest() {
        assertEquals(0.0, Evaluator.evaluate(""))
        assertEquals(2.0, Evaluator.evaluate("2"))
        assertEquals(5.0, Evaluator.evaluate("3+2"))
        assertEquals(4.0, Evaluator.evaluate("-3+2+5"))
        assertEquals(10.0,Evaluator.evaluate("20-15+10-5"))
        assertEquals(14.875,Evaluator.evaluate("2*7-9/8+7-5"))
        assertEquals(-6984.0,Evaluator.evaluate("758+528-300*9/10-8000"))
        assertEquals(414.3733437650029,Evaluator.evaluate("8+5.8*85.4-89+3/56.239"))
        assertEquals(2.3454535245454336E52,
            Evaluator.evaluate(
                "23454535245454334534532354543245432345234523454543235+5432452452452435"))

        assertEquals(Double.POSITIVE_INFINITY, Evaluator.evaluate("3/0"))

        assertThrows<EmptyStackException>(
            { "EmptyStackException not found!!" },
            { Evaluator.evaluate("-+") }
        )
        //assertFails("EmptyStackException not found!!") { Evaluator.evaluate("w") }




    }
}