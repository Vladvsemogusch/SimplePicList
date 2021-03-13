package cc.anisimov.vlad.rozetkapiclist.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import cc.anisimov.vlad.rozetkapiclist.R
import cc.anisimov.vlad.rozetkapiclist.domain.viewmodel.ImageListViewModel
import cc.anisimov.vlad.rozetkapiclist.ui.common.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.toolbar.*

@AndroidEntryPoint
class ImageListFragment : BaseFragment() {
    private val viewModel: ImageListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_image_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar(toolbar, getString(R.string.gallery))
        viewModel.start()
        setupErrorHandling(viewModel.oError)
    }


}