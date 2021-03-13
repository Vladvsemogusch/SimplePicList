package cc.anisimov.vlad.rozetkapiclist.data.repository

import cc.anisimov.vlad.rozetkapiclist.data.model.RequestResult
import cc.anisimov.vlad.rozetkapiclist.data.source.remote.UnsplashApi
import cc.anisimov.vlad.rozetkapiclist.domain.model.Image
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageRepo @Inject constructor(
    private val remoteApi: UnsplashApi
) {
    companion object {
        const val DEFAULT_IMAGES_PER_PAGE = 5
    }

    suspend fun getImageListPage(page: Int): RequestResult<List<Image>> = withContext(
        Dispatchers.IO
    ) {
        val result =
            kotlin.runCatching { remoteApi.getPhotos(page, DEFAULT_IMAGES_PER_PAGE) }
        if (result.isFailure) {
            return@withContext RequestResult.Error(result.exceptionOrNull())
        }
        val response = result.getOrNull()
            ?: return@withContext RequestResult.Error(Exception("Null response"))
        val images = response.body()
            ?: return@withContext RequestResult.Error(Exception("Null response body"))
        val imageList = images.map { Image(it.id, it.urls.thumb) }
        RequestResult.Success(imageList)
    }
}