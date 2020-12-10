package org.kmebin.Seminar27th.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SoptServiceImpl {
    private const val BASE_URL = "http://15.164.83.210:3000"
    private const val DUMMY_URL = "https://reqres.in"
    private const val KAKAO_URL = "https://dapi.kakao.com"

    private val baseRetrofit : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val baseService : SoptService = baseRetrofit.create(SoptService::class.java)

    private val dummyRetrofit : Retrofit = Retrofit.Builder()
        .baseUrl(DUMMY_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val dummyService : SoptService = dummyRetrofit.create(SoptService::class.java)

    private val kakaoRetrofit : Retrofit = Retrofit.Builder()
            .baseUrl(DUMMY_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val kakaoService : SoptService = kakaoRetrofit.create(SoptService::class.java)
}