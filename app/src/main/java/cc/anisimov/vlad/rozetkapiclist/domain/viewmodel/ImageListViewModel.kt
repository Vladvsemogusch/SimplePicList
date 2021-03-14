package cc.anisimov.vlad.rozetkapiclist.domain.viewmodel

import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cc.anisimov.vlad.rozetkapiclist.R
import cc.anisimov.vlad.rozetkapiclist.data.model.RequestResult
import cc.anisimov.vlad.rozetkapiclist.data.repository.ImageRepo
import cc.anisimov.vlad.rozetkapiclist.domain.model.Image
import cc.anisimov.vlad.rozetkapiclist.domain.utility.SingleLiveEvent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch

class ImageListViewModel @ViewModelInject constructor(
    private val imageRepo: ImageRepo,
    @ApplicationContext val context: Context
) :
    ViewModel() {
    val oImageList = MutableLiveData<List<Image>?>()
    val oError = SingleLiveEvent<String?>()
    val oLoading = MutableLiveData(false)
    fun start() {
        viewModelScope.launch {
            val result = imageRepo.getImageListPage(1)
            when (result) {
                is RequestResult.Success -> oImageList.value = result.data
                is RequestResult.Error -> oError.value = result.exception?.message
                    ?: context.getString(R.string.unknown_error)
            }
        }
    }
}