package gr.sppzglou.novibet.ui

import android.content.SharedPreferences
import android.net.ConnectivityManager
import androidx.lifecycle.LiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import gr.sppzglou.novibet.base.BaseViewModel
import gr.sppzglou.novibet.data.GameItem
import gr.sppzglou.novibet.data.HeadlineItem
import gr.sppzglou.novibet.data.LoginRequest
import gr.sppzglou.novibet.di.connectivity.ConnectivityLiveData
import gr.sppzglou.novibet.framework.Repository
import gr.sppzglou.novibet.utils.BEARER
import gr.sppzglou.single.utils.SingleLiveEvent
import gr.sppzglou.single.utils.set
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel
@Inject constructor(
    connectivityLiveData: ConnectivityLiveData,
    connectivityManager: ConnectivityManager,
    private val repo: Repository,
    private val sharedPref: SharedPreferences,
) : BaseViewModel(connectivityLiveData, connectivityManager) {

    private val _successHeadlines = SingleLiveEvent<HeadlineItem>()
    val successHeadlines: LiveData<HeadlineItem> = _successHeadlines

    private val _successGames = SingleLiveEvent<GameItem>()
    val successGames: LiveData<GameItem> = _successGames

    private val _successUpdateHeadlines = SingleLiveEvent<HeadlineItem>()
    val successUpdateHeadlines: LiveData<HeadlineItem> = _successUpdateHeadlines

    private val _successUpdateGames = SingleLiveEvent<GameItem>()
    val successUpdateGames: LiveData<GameItem> = _successUpdateGames

    private val _page = SingleLiveEvent<Boolean>()
    val page: LiveData<Boolean> = _page

    fun clearToken() {
        sharedPref[BEARER] = null
    }

    fun fetchData() {
        launch {
            repo.token(LoginRequest("Symeon", "Papazoglou"))

            coroutineScope {
                async {
                    val headlines = repo.headlines()
                    if (headlines != null) _successHeadlines.value = headlines
                }
                async {
                    val games = repo.games()
                    if (games != null) _successGames.value = games
                }
            }
            update()
        }
    }

    fun update() {
        launch(2000) {
            withContext(Dispatchers.IO) {
                val headlines = repo.updatedHeadlines()
                if (headlines != null) _successUpdateHeadlines.postValue(headlines)

                val games = repo.updatedGames()
                if (games != null) _successUpdateGames.postValue(games)
                update()
            }
        }
    }

    fun pager() {
        launch(5000) {
            _page.value = true
            pager()
        }
    }
}
