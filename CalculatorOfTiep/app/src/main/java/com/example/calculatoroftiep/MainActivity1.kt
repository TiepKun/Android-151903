package com.example.calculatoroftiep

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import java.text.DecimalFormat

class MainActivity1 : AppCompatActivity() {

    private val ADDITION = '+'
    private val SUBTRACTION = '-'
    private val MULTIPLICATION = '*'
    private val DIVISION = '/'
    private val NONE = '0'

    private var currentSymbol = NONE
    private var firstValue = Double.NaN
    private var secondValue = 0.0
    private var lastResult = 0.0

    private lateinit var inputDisplay: TextView
    private lateinit var outputDisplay: TextView
    private lateinit var decimalFormat: DecimalFormat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        decimalFormat = DecimalFormat("#.##########")

        inputDisplay = findViewById(R.id.input)
        outputDisplay = findViewById(R.id.output)

        // Number buttons
        val numberButtons = listOf(
            findViewById<MaterialButton>(R.id.btn0), findViewById(R.id.btn1), findViewById(R.id.btn2),
            findViewById(R.id.btn3), findViewById(R.id.btn4), findViewById(R.id.btn5),
            findViewById(R.id.btn6), findViewById(R.id.btn7), findViewById(R.id.btn8),
            findViewById(R.id.btn9)
        )

        numberButtons.forEachIndexed { index, button ->
            button.setOnClickListener {
                inputDisplay.append(index.toString())
            }
        }

        // Operator buttons
        findViewById<MaterialButton>(R.id.add).setOnClickListener { handleOperator(ADDITION) }
        findViewById<MaterialButton>(R.id.subtract).setOnClickListener { handleOperator(SUBTRACTION) }
        findViewById<MaterialButton>(R.id.multiply).setOnClickListener { handleOperator(MULTIPLICATION) }
        findViewById<MaterialButton>(R.id.division).setOnClickListener { handleOperator(DIVISION) }

        // Clear and BE buttons
        findViewById<MaterialButton>(R.id.clear).setOnClickListener { clear() }
        findViewById<MaterialButton>(R.id.be).setOnClickListener { backspace() }

        // Equal button
        findViewById<MaterialButton>(R.id.equal).setOnClickListener {
            calculateResult()
            currentSymbol = NONE
        }

        // Plus/Minus button
        findViewById<MaterialButton>(R.id.btnNegative).setOnClickListener {
            val currentValue = inputDisplay.text.toString()
            if (currentValue.isNotEmpty()) {
                if (currentValue.startsWith("-")) {
                    inputDisplay.text = currentValue.substring(1)
                } else {
                    inputDisplay.text = "-$currentValue"
                }
            }
        }

        // Dot button
        findViewById<MaterialButton>(R.id.btnPoint).setOnClickListener {
            if (!inputDisplay.text.contains(".")) {
                inputDisplay.append(".")
            }
        }
    }

    private fun handleOperator(operator: Char) {
        if (!inputDisplay.text.isNullOrEmpty()) {
            if (!firstValue.isNaN()) {
                calculateResult()  // Perform previous calculation if any
            } else {
                firstValue = inputDisplay.text.toString().toDouble()
            }
            currentSymbol = operator
            inputDisplay.text = null
            outputDisplay.text = decimalFormat.format(firstValue) + operator
        } else if (!firstValue.isNaN()) {
            currentSymbol = operator
            outputDisplay.text = decimalFormat.format(firstValue) + operator
        }
    }

    private fun calculateResult() {
        if (currentSymbol != NONE) {
            secondValue = if (inputDisplay.text.isNullOrEmpty()) 0.0 else inputDisplay.text.toString().toDouble()

            firstValue = when (currentSymbol) {
                ADDITION -> firstValue + secondValue
                SUBTRACTION -> firstValue - secondValue
                MULTIPLICATION -> firstValue * secondValue
                DIVISION -> firstValue / secondValue
                else -> firstValue
            }

            lastResult = firstValue
            outputDisplay.text = decimalFormat.format(firstValue)
            inputDisplay.text = null
        }
    }

    private fun clear() {
        firstValue = Double.NaN
        secondValue = 0.0
        currentSymbol = NONE
        inputDisplay.text = ""
        outputDisplay.text = ""
    }

    private fun backspace() {
        if (inputDisplay.text.isNotEmpty()) {
            inputDisplay.text = inputDisplay.text.dropLast(1)
        }
    }
}
