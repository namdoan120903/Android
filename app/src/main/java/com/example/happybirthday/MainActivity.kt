package com.example.happybirthday

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var currentNumber: String = ""
    private var previousNumber: String = ""
    private var displayText: String = ""
    private var operation: String = ""
    private var isOperationClicked: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btn1).setOnClickListener { onNumberClick(it) }
        findViewById<Button>(R.id.btn0).setOnClickListener { onNumberClick(it) }
        findViewById<Button>(R.id.btn1).setOnClickListener { onNumberClick(it) }
        findViewById<Button>(R.id.btn2).setOnClickListener { onNumberClick(it) }
        findViewById<Button>(R.id.btn3).setOnClickListener { onNumberClick(it) }
        findViewById<Button>(R.id.btn4).setOnClickListener { onNumberClick(it) }
        findViewById<Button>(R.id.btn5).setOnClickListener { onNumberClick(it) }
        findViewById<Button>(R.id.btn6).setOnClickListener { onNumberClick(it) }
        findViewById<Button>(R.id.btn7).setOnClickListener { onNumberClick(it) }
        findViewById<Button>(R.id.btn8).setOnClickListener { onNumberClick(it) }
        findViewById<Button>(R.id.btn9).setOnClickListener { onNumberClick(it) }
        findViewById<Button>(R.id.btnAdd).setOnClickListener { onOperationClick(it) }
        findViewById<Button>(R.id.btnSub).setOnClickListener { onOperationClick(it) }
        findViewById<Button>(R.id.btnMulti).setOnClickListener { onOperationClick(it) }
        findViewById<Button>(R.id.btnDivide).setOnClickListener { onOperationClick(it) }
        findViewById<Button>(R.id.btnEqual).setOnClickListener { onEqualClick(it) }
        findViewById<Button>(R.id.btnClear).setOnClickListener { onClearClick(it) }        // Nút C
        findViewById<Button>(R.id.btnClearEntry).setOnClickListener { onClearEntryClick(it) }  // Nút CE
        findViewById<Button>(R.id.btnBackSpace).setOnClickListener { onBackspaceClick(it) }    // Nút BS
        findViewById<Button>(R.id.btnPlusMinus).setOnClickListener { onPlusMinusClick(it) }
        findViewById<Button>(R.id.btnDot).setOnClickListener { onDotClick(it) }
// Nút +/-

    }

    fun onNumberClick(view: View) {
        if (isOperationClicked) {
            currentNumber = ""
            isOperationClicked = false
        }

        val button = view as Button
        currentNumber += button.text
        displayText += button.text;
        findViewById<TextView>(R.id.text_result).text = displayText
    }
    fun onOperationClick(view: View) {
        val button = view as Button
        operation = button.text.toString()
        operation = button.text.toString()
        displayText += "$operation"
        findViewById<TextView>(R.id.text_result).text = displayText
        previousNumber = currentNumber
        isOperationClicked = true
    }
    fun onEqualClick(view: View) {

        val result: Double? = when (operation) {
            "+" -> previousNumber.toDouble() + currentNumber.toDouble()
            "-" -> previousNumber.toDouble() - currentNumber.toDouble()
            "x" -> previousNumber.toDouble() * currentNumber.toDouble()
            "/" -> previousNumber.toDouble() / currentNumber.toDouble()
            else -> currentNumber.toDouble()
        }

        if (result != null && result == result.toInt().toDouble()) {
            displayText = result.toInt().toString() // Hiển thị số nguyên nếu kết quả là dạng .0
        } else {
            displayText = result?.let { String.format("%.3f", it) } ?: "Error"
        }

        findViewById<TextView>(R.id.text_result).text = displayText
        currentNumber = result?.toString() ?: ""
        previousNumber = 0.toString()
        operation = ""

    }

    fun onClearEntryClick(view: View) {
        currentNumber = ""
        displayText = ""
        findViewById<TextView>(R.id.text_result).text = "0"
    }

    fun onClearClick(view: View) {
        currentNumber = ""
        previousNumber = ""
        operation = ""
        displayText = ""
        findViewById<TextView>(R.id.text_result).text = "0"

    }

    fun onBackspaceClick(view: View) {
        if (currentNumber.isNotEmpty()) {
            currentNumber = currentNumber.dropLast(1)
            displayText = currentNumber
            findViewById<TextView>(R.id.text_result).text = displayText
        }
    }

    fun onDotClick(view: View) {
        if (!currentNumber.contains(".")) {
            currentNumber += "."
            displayText += "."
            findViewById<TextView>(R.id.text_result).text = displayText
        }
    }

    fun onPlusMinusClick(view: View) {
        if (currentNumber.isNotEmpty()) {
            currentNumber = if (currentNumber.startsWith("-")) {
                currentNumber.drop(1)
            } else {
                "-$currentNumber"
            }
            displayText = currentNumber.toDouble().let { String.format("%.3f", it) } ?: "Error"
            findViewById<TextView>(R.id.text_result).text = displayText
        }
    }
}