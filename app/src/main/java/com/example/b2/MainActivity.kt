package com.example.b2

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextNumber: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var buttonShow: Button
    private lateinit var listViewNumbers: ListView
    private lateinit var textViewError: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextNumber = findViewById(R.id.editTextNumber)
        radioGroup = findViewById(R.id.radioGroup)
        buttonShow = findViewById(R.id.buttonShow)
        listViewNumbers = findViewById(R.id.listViewNumbers)
        textViewError = findViewById(R.id.textViewError)

        buttonShow.setOnClickListener { showNumbers() }
    }

    private fun showNumbers() {
        textViewError.text = ""

        val input = editTextNumber.text.toString()
        if (input.isEmpty()) {
            textViewError.text = "Vui lòng nhập số nguyên dương."
            return
        }

        val n = input.toIntOrNull()
        if (n == null || n < 0) {
            textViewError.text = "Số phải là số nguyên dương."
            return
        }

        val numbers = mutableListOf<Int>()

        when (radioGroup.checkedRadioButtonId) {
            R.id.radioEven -> {
                for (i in 0..n step 2) {
                    numbers.add(i)
                }
            }
            R.id.radioOdd -> {
                for (i in 1..n step 2) {
                    numbers.add(i)
                }
            }
            R.id.radioPerfectSquare -> {
                for (i in 0..n) {
                    val square = i * i
                    if (square <= n) {
                        numbers.add(square)
                    }
                }
            }
            else -> {
                textViewError.text = "Vui lòng chọn loại số."
                return
            }
        }

        // Cập nhật ListView
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, numbers)
        listViewNumbers.adapter = adapter
    }
}