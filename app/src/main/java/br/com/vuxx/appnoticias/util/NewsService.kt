package br.com.vuxx.appnoticias.util

import br.com.vuxx.appnoticias.model.News
import br.com.vuxx.appnoticias.model.RequestNews
import retrofit2.Call
import retrofit2.http.GET

interface NewsService {

    @GET("top-headlines?apiKey=02904593708e4b5e838bcb586a5e2081&sources=google-news-br")
    fun list():Call<RequestNews>
}