package br.com.vuxx.appnoticias.util

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {

    private var retro: Retrofit? = null

    constructor() {
        this.retro = Retrofit.Builder().baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    fun newsService(): NewsService {return retro!!.create(NewsService::class.java)}

}