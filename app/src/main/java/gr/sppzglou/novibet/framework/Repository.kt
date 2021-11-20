package gr.sppzglou.novibet.framework

import android.content.SharedPreferences
import gr.sppzglou.novibet.data.GameItem
import gr.sppzglou.novibet.data.HeadlineItem
import gr.sppzglou.novibet.data.LoginRequest
import gr.sppzglou.novibet.utils.BEARER
import gr.sppzglou.single.utils.set
import javax.inject.Inject


class Repository @Inject constructor(
    private val api: NovibetApi,
    private val sharedPref: SharedPreferences
) {

    suspend fun token(request: LoginRequest) {
        val res = api.token(request)
        sharedPref[BEARER] = res.token
    }

    suspend fun games(): GameItem? {
        val res = api.games()
        return if (res.isNotEmpty()) res[0]
        else null
    }

    suspend fun headlines(): HeadlineItem? {
        val res = api.headlines()
        return if (res.isNotEmpty()) res[0]
        else null
    }

    suspend fun updatedGames(): GameItem? {
        val res = api.updatedGames()
        return if (res.isNotEmpty()) res[0]
        else null
    }

    suspend fun updatedHeadlines(): HeadlineItem? {
        val res = api.updatedHeadlines()
        return if (res.isNotEmpty()) res[0]
        else null
    }
}
