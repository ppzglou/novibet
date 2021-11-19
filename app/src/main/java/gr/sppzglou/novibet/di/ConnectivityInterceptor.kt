package gr.sppzglou.novibet.di

import gr.sppzglou.novibet.framework.exception.NoInternetException
import okhttp3.Interceptor
import okhttp3.Response

class ConnectivityInterceptor(private val connectivityHelper: ConnectivityHelper) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!connectivityHelper.checkInternetConnection()) {
            throw NoInternetException()
        }
        return chain.proceed(chain.request())
    }
}