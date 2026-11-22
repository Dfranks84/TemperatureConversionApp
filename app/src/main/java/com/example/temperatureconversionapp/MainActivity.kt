package com.example.temperatureconversionapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

// Dennis Franks Jr.
// 2.4 Performance Assessment
// Temperature Conversion App
// November 21, 2026

class MainActivity : AppCompatActivity() {

    private lateinit var edtTemperature: EditText
    private lateinit var btnFahrenheitToCelsius: Button
    private lateinit var btnCelsiusToFahrenheit: Button
    private lateinit var txtResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtTemperature = findViewById(R.id.edtTemperature)
        btnFahrenheitToCelsius =
            findViewById(R.id.btnFahrenheitToCelsius)
        btnCelsiusToFahrenheit =
            findViewById(R.id.btnCelsiusToFahrenheit)
        txtResult = findViewById(R.id.txtResult)

        btnFahrenheitToCelsius.setOnClickListener {
            convertFahrenheitToCelsius()
        }

        btnCelsiusToFahrenheit.setOnClickListener {
            convertCelsiusToFahrenheit()
        }
    }

    private fun getTemperature(): Double? {
        val input = edtTemperature.text.toString().trim()

        if (input.isEmpty()) {
            Toast.makeText(
                this,
                R.string.error_empty,
                Toast.LENGTH_SHORT
            ).show()
            return null
        }

        val temperature = input.toDoubleOrNull()

        if (temperature == null || !temperature.isFinite()) {
            Toast.makeText(
                this,
                R.string.error_invalid,
                Toast.LENGTH_SHORT
            ).show()
            return null
        }

        return temperature
    }

    private fun convertFahrenheitToCelsius() {
        val fahrenheit = getTemperature() ?: return

        if (fahrenheit < -100 || fahrenheit > 250) {
            Toast.makeText(
                this,
                R.string.error_f_range,
                Toast.LENGTH_LONG
            ).show()
            return
        }

        val celsius = (fahrenheit - 32) * 5 / 9

        txtResult.text = getString(
            R.string.result_f_to_c,
            fahrenheit,
            celsius
        )
    }

    private fun convertCelsiusToFahrenheit() {
        val celsius = getTemperature() ?: return

        if (celsius < -75 || celsius > 125) {
            Toast.makeText(
                this,
                R.string.error_c_range,
                Toast.LENGTH_LONG
            ).show()
            return
        }

        val fahrenheit = (celsius * 9 / 5) + 32

        txtResult.text = getString(
            R.string.result_c_to_f,
            celsius,
            fahrenheit
        )
    }
}
