package com.example.calculatorjavafxinterpreter

import java.util.*
import kotlin.collections.HashMap

object Evaluator {
    private val operators = HashMap<String, String>()

    init {
        operators["+"] = "1"
        operators["-"] = "1"
        operators["/"] = "2"
        operators["*"] = "2"
        operators["%"] = "3"
    }
    fun evaluate(expression: String): Double? {
        if (expression.isBlank()) return 0.0

        val pfExp = infixToPostfix(expression)

        val root = buildTree(pfExp)

        return root?.evaluate()
    }

    private fun getOperatorExpression(
        operation: String, left: Expression, right: Expression): Expression? {
        return when(operation) {
            "+" -> Expression.Plus(left, right)
            "-" -> Expression.Minus(left,right)
            "*" -> Expression.Multiply(left, right)
            "/" -> Expression.Divide(left, right)
            "%" -> Expression.Module(left, right)
            else -> null
        }
    }

    private fun buildTree(expr: Stack<Any>): Expression? {
        val stack = Stack<Expression>()

        while(expr.isNotEmpty()) {
            val currAny = expr.pop()
            if (!isOperation(currAny)) stack.push(Expression.Number(currAny))
            else {
                val right = stack.pop() as Expression
                val left = stack.pop()  as Expression
                val n = getOperatorExpression(currAny as String, left,right)
                stack.push(n)
            }
        }
        return stack.pop()
    }

    private fun infixToPostfix(expr: String): Stack<Any> {
        val stack = Stack<String>()
        val pfExpr = Stack<Any>()

        var tempStr = ""
        var i = 0

        while(i < expr.length) {
            val ch = expr.substring(i,i+1)

            //extracting number from the expression
            if(isInt(ch)) {
                var number = 0.0
                var indexShift = 0

                for(j in i+1..expr.length) {
                    if(isDouble(expr.substring(i,j))) {
                        number = expr.substring(i,j).toDouble()
                        indexShift = j - i
                    }
                }
                pfExpr.push(number)
                i += indexShift - 1
            }
            //extracting operation from the expression
            else if (isOperation(ch)) {
                if(stack.isEmpty()) {
                    stack.push(ch)
                    if(ch == "-" && i == 0) pfExpr.push(0.0)
                }
                else {
                    tempStr = stack.pop() as String
                    var val1 = operators[tempStr]!!.toInt()

                    val val2 = operators[ch]!!.toInt()

                    while (val1 >= val2) {
                        pfExpr.push(tempStr)
                        val1 = -100

                        if(stack.isNotEmpty()) {
                            tempStr = stack.pop() as String
                            val1 = operators[tempStr]!!.toInt()
                        }
                    }
                    if (val1 != -100) stack.push(tempStr)

                    stack.push(ch)
                }
            }
            i++
        }
        while (stack.isNotEmpty()) pfExpr.push(stack.pop())

        pfExpr.reverse()

        return pfExpr
    }

}