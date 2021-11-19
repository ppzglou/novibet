package gr.sppzglou.novibet.framework

import android.content.SharedPreferences
import javax.inject.Inject


class Repository @Inject constructor(
    private val api: NovibetApi,
    private val sharedPref: SharedPreferences
) {

    suspend fun token() {

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
