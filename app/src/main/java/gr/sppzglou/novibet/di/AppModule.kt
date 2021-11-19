package gr.sppzglou.novibet.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import gr.sppzglou.novibet.di.connectivity.ConnectivityLiveData
import gr.sppzglou.novibet.utils.NOVIBET_PREFS
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(NOVIBET_PREFS, Context.MODE_PRIVATE)

    @Singleton
    @Provides
    fun provideContext(@ApplicationContext applicationContext: Context): Context =
        applicationContext

    @Provides
    fun provideConnectivityManager(application: Application): ConnectivityManager =
        application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    @Provides
    fun provideConnectivityLiveData(
        application: Application,
        connectivityManager: ConnectivityManager
    ): ConnectivityLiveData =
        ConnectivityLiveData(application, connectivityManager)
}