package cc.anisimov.vlad.rozetkapiclist.domain.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cc.anisimov.vlad.rozetkapiclist.data.repository.ImageRepo
import cc.anisimov.vlad.rozetkapiclist.domain.utility.SingleLiveEvent
import kotlinx.coroutines.launch

class ImageListViewModel @ViewModelInject constructor(private val imageRepo: ImageRepo) :
    ViewModel() {
    val oError = SingleLiveEvent<String?>()
    val oLoading = MutableLiveData(false)
    fun start() {
        viewModelScope.launch {
            val result = imageRepo.getImageListPage(1)
        }
    }
}