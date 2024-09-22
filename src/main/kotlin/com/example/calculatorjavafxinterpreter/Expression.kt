package com.example.calculatorjavafxinterpreter

interface Expression {
    fun evaluate(): Double

    class Plus(private val left: Expression, private val right: Expression) : Expression {
        override fun evaluate(): Double {
            return left.evaluate() + right.evaluate()
        }
    }

    class Minus(private val left: Expression, private val right: Expression) : Expression {
        override fun evaluate(): Double {
            return left.evaluate() - right.evaluate()
        }
    }

    class Multiply(private val left: Expression, private val right: Expression) : Expression {
        override fun evaluate(): Double {
            return left.evaluate() * right.evaluate()
        }
    }

    class Divide(private val left: Expression, private val right: Expression) : Expression {
        override fun evaluate(): Double {
            return left.evaluate() / right.evaluate()
        }
    }

    class Module(private val left: Expression, private val right: Expression) : Expression {
        override fun evaluate(): Double {
            return left.evaluate() % right.evaluate()
        }
    }

    class Number<T>(private val value: T) : Expression {
        override fun evaluate(): Double {
            return value as Double
        }
    }


}