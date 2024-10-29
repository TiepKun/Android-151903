package com.example.moneyconverterapp

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.moneyconverterapp.RetrofitBuilder
import com.example.moneyconverterapp.Retrofit.RetrofitInterface
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var currencyToConvert: EditText
    private lateinit var convertedFromSpinner: Spinner
    private lateinit var convertedToSpinner: Spinner
    private lateinit var resultView: EditText
    private lateinit var convertButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        currencyToConvert = findViewById(R.id.currency_to_be_converted)
        convertedFromSpinner = findViewById(R.id.converted_from)
        convertedToSpinner = findViewById(R.id.converted_to)
        resultView = findViewById(R.id.currency_converted)
        convertButton = findViewById(R.id.button)

        // Define currency options
        val currencyOptions = listOf("USD", "AFN", "VND", "EUR", "GBP")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencyOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        convertedFromSpinner.adapter = adapter
        convertedToSpinner.adapter = adapter

        // Set up button click listener
        convertButton.setOnClickListener {
            val amount = currencyToConvert.text.toString().toDoubleOrNull()
            val fromCurrency = convertedFromSpinner.selectedItem.toString()
            val toCurrency = convertedToSpinner.selectedItem.toString()

            if (amount != null) {
                fetchExchangeRate(amount, fromCurrency, toCurrency)
            } else {
                Toast.makeText(this, "Please enter a valid amount", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun fetchExchangeRate(amount: Double, fromCurrency: String, toCurrency: String) {
        val retrofit = RetrofitBuilder.getRetrofitInstance().create(RetrofitInterface::class.java)
        val call = retrofit.getExchangeCurrency(fromCurrency)

        call.enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (response.isSuccessful) {
                    val exchangeRates = response.body()?.getAsJsonObject("conversion_rates")
                    val rate = exchangeRates?.get(toCurrency)?.asDouble

                    if (rate != null) {
                        val convertedAmount = amount * rate
                        resultView.setText(convertedAmount.toString())
                    } else {
                        resultView.setText("Conversion rate for $toCurrency not available")
                    }
                } else {
                    Toast.makeText(this@MainActivity, "Failed to get response", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
