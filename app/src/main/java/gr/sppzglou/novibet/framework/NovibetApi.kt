package gr.sppzglou.novibet.framework

import gr.sppzglou.novibet.data.GameItem
import gr.sppzglou.novibet.data.HeadlineItem
import gr.sppzglou.novibet.data.LoginRequest
import gr.sppzglou.novibet.data.Token
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface NovibetApi {

    @POST("5d8e4bd9310000a2612b5448")
    suspend fun token(@Body loginRequest: LoginRequest): Token

    @GET("5d7113513300000b2177973a")
    suspend fun games(): MutableList<GameItem>

    @GET("5d7113ef3300000e00779746")
    suspend fun headlines(): MutableList<HeadlineItem>

    @GET("5d7114b2330000112177974d")
    suspend fun updatedGames(): MutableList<GameItem>

    @GET("5d711461330000d135779748")
    suspend fun updatedHeadlines(): MutableList<HeadlineItem>
}