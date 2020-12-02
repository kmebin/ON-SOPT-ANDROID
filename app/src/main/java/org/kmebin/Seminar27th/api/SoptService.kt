package org.kmebin.Seminar27th.api

import org.kmebin.Seminar27th.data.RequestLoginData
import org.kmebin.Seminar27th.data.RequestSignUpData
import org.kmebin.Seminar27th.data.ResponseLoginData
import org.kmebin.Seminar27th.data.ResponseSignUpData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SoptService {
    // 회원가입
    @Headers("Content-Type: application/json")
    @POST("/users/signup")
    fun postSignUp(
            @Body body: RequestSignUpData
    ) : Call<ResponseSignUpData>

    // 로그인
    @Headers("Content-Type: application/json")
    @POST("/users/signin")
    fun postLogin(
        @Body body : RequestLoginData
    ) : Call<ResponseLoginData>
}