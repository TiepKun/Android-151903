package com.example.moneyconverterapp.Retrofit

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitInterface {
    @GET("v6/535d4decf9459fef29f31322/latest/{base_currency}")
    fun getExchangeCurrency(@Path("base_currency") baseCurrency: String): Call<JsonObject>
}
