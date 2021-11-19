package gr.sppzglou.novibet.framework

import android.content.SharedPreferences
import gr.sppzglou.novibet.data.LoginRequest
import gr.sppzglou.novibet.utils.BEARER
import gr.sppzglou.single.utils.set
import javax.inject.Inject


class Repository @Inject constructor(
    private val api: NovibetApi,
    private val sharedPref: SharedPreferences
) {

    suspend fun token() {
        val res = api.token(LoginRequest("Symeon", "Papazoglou"))
        sharedPref[BEARER] = res.token
    }

    suspend fun games() {

    }

    suspend fun headlines() {

    }

    suspend fun updatedGames() {

    }

    suspend fun updatedHeadlines() {

    }
}
