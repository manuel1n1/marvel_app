package com.manuel1n1.apps.network

import com.manuel1n1.apps.BuildConfig
import com.manuel1n1.apps.data.CharacterDataWrapper
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.math.BigInteger
import java.security.MessageDigest
import java.util.*

interface ApiService {

    companion object {
        const val PRIV_KEY = BuildConfig.PRIV_KEY
        const val PUBLIC_KEY = BuildConfig.PUBLIC_KEY
        const val BASE_URL = BuildConfig.BASE_URL
        val TIMESTAMP = Date().time.toString()
        val HASH = md5(TIMESTAMP + PRIV_KEY + PUBLIC_KEY)

        fun md5(input:String):String {
            val md= MessageDigest.getInstance("MD5")
            return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
        }

        fun create():ApiService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }

    @GET("characters")
    suspend fun getCharacters(
        @Query("apikey") apiKey:String,
        @Query("ts") ts:String,
        @Query("hash")hash:String,
        @Query("orderBy")orderBy:String
    ) : Response<CharacterDataWrapper>
}