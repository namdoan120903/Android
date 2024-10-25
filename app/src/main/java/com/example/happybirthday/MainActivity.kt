package com.example.happybirthday

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var editTextAmount: EditText
    private lateinit var spinnerFromCurrency: Spinner
    private lateinit var spinnerToCurrency: Spinner
    private lateinit var editTextResult: EditText
    private lateinit var textViewEquals: TextView

    // Tỷ giá của từng loại tiền theo VND
    private val currencyRates = mapOf(
        "USD" to 25167.0,
        "EUR" to 26766.0,
        "JPY" to 161.1,
        "GBP" to 32076.7,
        "VND" to 1.0
    )

    private val currencies = currencyRates.keys.toTypedArray() // Lấy danh sách các loại tiền

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Khởi tạo các view bằng findViewById
        editTextAmount = findViewById(R.id.editTextAmount)
        spinnerFromCurrency = findViewById(R.id.spinnerFromCurrency)
        spinnerToCurrency = findViewById(R.id.spinnerToCurrency)
        editTextResult = findViewById(R.id.editTextResult)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencies)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerFromCurrency.adapter = adapter
        spinnerToCurrency.adapter = adapter

        editTextAmount.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                updateResult()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        spinnerFromCurrency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                updateResult()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        spinnerToCurrency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                updateResult()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    private fun updateResult() {
        val amountStr = editTextAmount.text.toString()
        if (amountStr.isEmpty()) {
            editTextResult.setText("")
            return
        }

        val amount = amountStr.toDouble()
        val fromCurrency = spinnerFromCurrency.selectedItem.toString()
        val toCurrency = spinnerToCurrency.selectedItem.toString()

        // Chuyển đổi số tiền theo tỷ giá
        if (fromCurrency != toCurrency) {
            val fromRate = currencyRates[fromCurrency] ?: 1.0
            val toRate = currencyRates[toCurrency] ?: 1.0
            val convertedAmount = (amount * fromRate) / toRate
            editTextResult.setText(convertedAmount.toString())
        } else {
            editTextResult.setText(amountStr)
        }
    }
}