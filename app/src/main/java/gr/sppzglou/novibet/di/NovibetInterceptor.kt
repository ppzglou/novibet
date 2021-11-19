package gr.sppzglou.novibet.di

import android.content.SharedPreferences
import gr.sppzglou.novibet.utils.BEARER
import gr.sppzglou.single.utils.get
import okhttp3.Interceptor
import okhttp3.Response


class NovibetInterceptor(private val sharedPref: SharedPreferences) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val requestBuilder = original.newBuilder()

        sharedPref[BEARER, ""]?.let {
            if (it.isNotEmpty())
                requestBuilder.header(BEARER, it)
        }

        val request = requestBuilder.build()
        return chain.proceed(request)
    }


}