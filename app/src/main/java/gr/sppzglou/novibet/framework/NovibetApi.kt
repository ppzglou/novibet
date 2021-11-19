package gr.sppzglou.novibet.framework

import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query


interface NovibetApi {

    @POST("login")
    suspend fun login(@Query("user") user: String, @Query("pass") pass: String): Response<Boolean>
}