package cc.anisimov.vlad.rozetkapiclist.data.source.remote

import cc.anisimov.vlad.rozetkapiclist.data.model.ImageRemote
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashApi {
    @GET("photos")
    suspend fun getPhotos(@Query("page") page: Int, @Query("per_page") perPage: Int): Response<List<ImageRemote>>
}