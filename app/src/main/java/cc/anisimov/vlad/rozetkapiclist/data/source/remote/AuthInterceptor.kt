package cc.anisimov.vlad.rozetkapiclist.data.source.remote

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthInterceptor : Interceptor {
    companion object {
        const val API_ACCESS_KEY = "A_EZ1V9bRfQCRXwEmDK_0gVs6nRwXIbrxc8Rd7OB3N0"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest: Request = chain.request().newBuilder()
            .addHeader("Authorization", "Client-ID $API_ACCESS_KEY")
            .build()
        return chain.proceed(newRequest)
    }
}