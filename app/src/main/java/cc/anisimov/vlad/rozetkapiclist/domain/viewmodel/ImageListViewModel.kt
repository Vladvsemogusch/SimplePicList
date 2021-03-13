package cc.anisimov.vlad.rozetkapiclist.domain.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import cc.anisimov.vlad.rozetkapiclist.data.repository.ImageRepo

class ImageListViewModel @ViewModelInject constructor(private val imageRepo: ImageRepo) :
    ViewModel() {

}