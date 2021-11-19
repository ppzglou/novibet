package gr.sppzglou.novibet.data

import com.google.gson.annotations.SerializedName

data class Token(
    @SerializedName("access_token")
    val token: String,
    @SerializedName("token_type")
    val type: String
)