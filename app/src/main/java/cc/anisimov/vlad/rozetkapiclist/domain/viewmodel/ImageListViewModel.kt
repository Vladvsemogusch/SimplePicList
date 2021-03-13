package cc.anisimov.vlad.rozetkapiclist.domain.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cc.anisimov.vlad.rozetkapiclist.data.repository.ImageRepo
import kotlinx.coroutines.launch

class ImageListViewModel @ViewModelInject constructor(private val imageRepo: ImageRepo) :
    ViewModel() {

    fun start() {
        viewModelScope.launch {
            val result = imageRepo.getImageListPage(1)
        }
    }
}